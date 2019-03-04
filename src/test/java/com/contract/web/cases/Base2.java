package com.contract.web.cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
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
import com.contract.web.util.UILibraryUtil;

public class Base2 {
	public static WebDriver driver;
	@BeforeSuite
	@Parameters(value={"browserType","driverPath","loginusername","loginpassword"})
	public void init(String browserType,String driverPath,String loginusername,String loginpassword){
		if ("ie".equalsIgnoreCase(browserType)) {          //如果是IE
			System.setProperty("webdriver.ie.driver", driverPath);
			//创建DesiredCapability对象，在对象中保存浏览器的设置信息
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//忽略浏览器的安全设置
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//忽略浏览器的缩放设置
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			//创建驱动对象，打卡IE浏览器
			driver = new InternetExplorerDriver(capabilities);
		}else if ("firefox".equalsIgnoreCase(browserType)) {   //如果是firefox
			//设置系统变量，指定驱动文件的路径，保证在创建驱动对象之前设置好
			System.setProperty("webdriver.gecko.driver",driverPath);
			//打开浏览器
			driver = new FirefoxDriver();
		}else if ("chrome".equalsIgnoreCase(browserType)) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			//启动chrome浏览器
			driver = new ChromeDriver();
		}
		Window window = driver.manage().window();
		window.maximize();
		driver.navigate().to("http://gs.bndxqc.com/login.html");
		getElement("登录页", "用户名").sendKeys(loginusername);
		getElement("登录页", "密码").sendKeys(loginpassword);
		getElement("登录页", "登录").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击客户企业
		List<WebElement> testelements = driver.findElements(By.className("layui-field-box"));
//		String actual = testelements.get(1).getText();
//		Assert.assertEquals(actual, expected);
		testelements.get(1).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击企业
		//getElement(By.id("25")).findElement(By.className("l-btn-text")).click();
		getElement("主页", "企业").findElement(By.className("l-btn-text")).click();
	}
	@AfterSuite
	public void tearDown() throws InterruptedException{
		Thread.sleep(5000);
		driver.quit();
	}
	public WebElement getElement(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return webElement;
		} catch (Exception e) {
			System.out.println("定位元素超时");
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
		//logger.info("根据{by:"+by+",value:"+value+"}来定位【"+pageKeyword+"】页面的【"+elementKeyword+"】元素");
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
}
