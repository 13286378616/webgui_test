package com.contract.web.cases;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class Base3 {
	private Logger logger = Logger.getLogger(Base3.class);
	public static WebDriver driver;
	
	@BeforeSuite
	@Parameters(value={"browserType","driverPath","loginusername","loginpassword"})
	public void init(String browserType,String driverPath,String loginusername,String loginpassword){
		logger.info("配置信息：浏览器类型：【"+browserType+"】，驱动文件路径：【"+driverPath+"】");
		if("ie".equalsIgnoreCase(browserType)){//如果是ie
			System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
			//创建DesiredCapabilitys对象，在对象中保存浏览器的设置信息
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//忽略浏览器的安全设置
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//忽略浏览器的缩放设置
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			//创建驱动对象，打开IE浏览器
			 driver = new InternetExplorerDriver(capabilities);
			 logger.info("*************创建了IE驱动对象，打开了IE浏览器，开始测试*****************");
		}else if("firefox".equalsIgnoreCase(browserType)){//如果是firefox
			//设置系统变量，指定驱动文件的路径,保证在创建驱动对象之前先设置好
			System.setProperty("webdriver.gecko.driver", driverPath);
			//打开浏览器
			 driver = new FirefoxDriver();
			 logger.info("*************创建了火狐驱动对象，打开了火狐浏览器，开始测试*****************");
		}else if("chrome".equalsIgnoreCase(browserType)){//如果是chrom
			System.setProperty("webdriver.chrome.driver", driverPath);
			//启动chrome浏览器
			 driver = new ChromeDriver();
			 logger.info("*************创建了chrome驱动对象，打开了chrome浏览器，开始测试*****************");
		}
		driver.manage().window().maximize();
		to("http://gs.bndxqc.com/login.html");
		//getElement("登录页", "用户名").sendKeys(loginusername);
		sendKeys(getElement("登录页", "用户名"), loginusername);
		//getElement("登录页", "密码").sendKeys(loginpassword);
		sendKeys(getElement("登录页", "密码"),loginpassword );
		//getElement("登录页", "登录").click();
		click(getElement("登录页", "登录"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击客户企业
//		List<WebElement> testelements = driver.findElements(By.className("layui-field-box"));
//		String actual = testelements.get(1).getText();
//		Assert.assertEquals(actual, expected);
//		testelements.get(1).click();
//		getElement(By.xpath("//div[text()='客户企业（测试）']")).click();;
		click(getElement("登录页", "客户企业（测试）"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String conpanyActual = getElement(By.id("companyName")).getText();
		AssertionUtil.assertTextEquals(conpanyActual, "运营企业");
		//点击企业
		//getElement(By.id("25")).findElement(By.className("l-btn-text")).click();
		//getElement("主页", "企业").findElement(By.className("l-btn-text")).click();
		click(getElement("主页", "企业"));
		
		
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
		WebDriverWait wait = new WebDriverWait(driver, 120);
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
	public void to(String url){
		logger.info("访问测试页面：【"+url+"】");
		driver.navigate().to(url);
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
