package com.contract.web.cases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.contract.web.pojo.UIElement;
import com.contract.web.util.AssertionUtil;
import com.contract.web.util.UILibraryUtil;

public class BaseElectron {
	private Logger logger = Logger.getLogger(BaseElectron.class);
	public static WebDriver driver;
	
	@BeforeSuite
	@Parameters(value={"electronType","driverPath"})
	public void init(String electronType,String driverPath) throws Exception{
		logger.info("配置信息：ELectron版本：【"+electronType+"】，驱动文件路径：【"+driverPath+"】");
		   System.setProperty("webdriver.chrome.driver",driverPath);// You can skip this if chromedriver is already included in the PATH.
		   ChromeOptions options = new ChromeOptions();
		   options.setBinary(electronType);//设置二进制文件，一定用绝对路径，不要用/的写法
		   DesiredCapabilities capabilities = new DesiredCapabilities();//负责启动服务端时的参数设置
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);//将参数options添加到设置中
		   
		   logger.info("*************创建了chrome驱动对象，打开了Electron，开始测试*****************");
		   driver = new ChromeDriver(capabilities);//欺骗chromdriver启动electron
		   // Now, your electron app would have been opened. 
		   // Now if you open the dev tools using CMD+ALT+I you would notice two dev tools and first one being for the electron shell. We need to switch to the second window handle. Let's do that.
		   Thread.sleep(3000);
		   for (String handle : driver.getWindowHandles())
	       {
			   System.out.println(handle);
	         driver.switchTo().window(handle); // Since there are two window handles this would switch to last one(which is second one). You can also explicitly provide the window number.
	       }
		     // If you inspect using the Dev Tools, you would notice the second window Dev Tools corresponds to actual page you have opened.
		     // From here you can write the usual selenium script and it will work.
		   	driver.findElement(By.xpath("//a[@ng-click='login()']")).click();
		   	Thread.sleep(3000);
		   	//跳转后，会生成新的窗口，所以要跳转到最后这一个窗口，才能找到元素
		   	for (String handle : driver.getWindowHandles())
		       {
		   		System.out.println(handle);
		         driver.switchTo().window(handle); // Since there are two window handles this would switch to last one(which is second one). You can also explicitly provide the window number.
		       }
		   	/*Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='进货']")).click();
		   	Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='销售']")).click();
		   	Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='库存']")).click();*/
		   	//click(getElement("首页页", "进货"));
		   	Thread.sleep(6000);
		  //如果弹出悬浮框“是否打开上次打开状态？”，点击取消
			boolean stateElement = AssertionUtil.ElementExist(driver, By.xpath("//p[text()='是否打开上次打开状态？']"));
			System.out.println("是否弹出悬浮框恢复上次状态"+stateElement);
			if (stateElement==true) {
				//弹出悬浮框，点击否
				click(getElement("首页页", "取消"));
			} else {
				System.out.println("没有弹出恢复上次状态悬浮框");
			}
	}
	@AfterSuite
	public void tearDown() throws InterruptedException{
		Thread.sleep(3000);
		logger.info("************测试完成，关闭驱动对象***********");
		driver.quit();
	}
	/**显示等待（智能等待）
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info("元素定位成功");
			return webElement;
		} catch (Exception e) {
			logger.error("元素定位失败，超时了");
		}
		
		return null;
	}
	
	/**根据页面关键字+元素关键字来获取元素
	 * @param  pageKeyword 页面关键字
	 * @param elementKeyword 元素关键字
	 * @return
	 */
	public WebElement getElement(String pageKeyword,String elementKeyword){
		//先根据页面关键字和元素关键字拿到ui库当中的UIElement对象
		UIElement uiElement = UILibraryUtil.getUIElement(pageKeyword, elementKeyword);
		//通过拿到的UIElement对象，取出by和value属性值来判断通过什么方式来定位页面的元素
		String by = uiElement.getBy();
		String value = uiElement.getValue();
		logger.info("根据{by:"+by+",value:"+value+"}来定位【"+pageKeyword+"】页面的【"+elementKeyword+"】元素");
		By locator = null;
		//通过什么选择器来定位元素，取决于你配的by是什么信息
		if ("id".equals(by)) {
			locator = By.id(value);
		}else if ("name".equalsIgnoreCase(by)) {
			locator = By.name(value);
		}else if ("classname".equalsIgnoreCase(by)) {
			locator = By.className(value);
		}else if ("cssSelector".equalsIgnoreCase(by)) {
			locator = By.cssSelector(value);
		}else if ("linkText".equalsIgnoreCase(by)) {
			locator = By.linkText(value);
		}else if ("partialLinkText".equalsIgnoreCase(by)) {
			locator = By.partialLinkText(value);
		}else if ("tagName".equalsIgnoreCase(by)) {
			locator = By.tagName(value);
		}else if ("xpath".equalsIgnoreCase(by)) {
			locator = By.xpath(value);
		}
//		return driver.findElement(locator);
		return getElement(locator);
	}
	/**自己封装的方法（判断当前页面的URL路径包含了我们想要看到的信息，用了延时等待处理）
	 * @param part
	 * @return
	 */
	public boolean urlPresenceContent(String part){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			boolean flag= wait.until(ExpectedConditions.urlContains(part));
			return flag;
		} catch (Exception e) {
			System.out.println("超时了");
		
	}
	return false;
	}
	
	/**访问测试页面
	 * @param url 页面地址
	 */
	public void totesturl(String url){
		logger.info("访问测试页面：【"+url+"】");
		driver.navigate().to(url);
	}
	
	/**回退测试页面
	 * 
	 */
	public void back(){
		logger.info("回退测试页面");
		driver.navigate().back();
	}
	
	/**
	 * 往输入框写入数据
	 */
	public void sendKeys(WebElement element,String value){
		logger.info("写入数据：【"+value+"】");
		element.sendKeys(value);
	}

	/**点击
	 * @param element
	 */
	public void click(WebElement element){
		logger.info("完成元素的点击事件");
		element.click();
	}

	/**获取元素的文本值
	 * @param element
	 * @return
	 */
	public String  getText(WebElement element){
		String value = element.getText();
		logger.info("获取元素的文本值：【"+value+"】");
		return value;
	}
}
