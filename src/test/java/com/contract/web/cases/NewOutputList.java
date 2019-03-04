package com.contract.web.cases;

import java.sql.Driver;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.contract.web.util.AssertionUtil;

public class NewOutputList extends Base{
	private Logger logger = Logger.getLogger(NewOutputList.class);
	@Test
	public void test1() throws Exception{
		totesturl("http://gs.bndxqc.com/login.html");
		//getElement("登录页", "用户名").sendKeys(loginusername);
		sendKeys(getElement("登录页", "用户名"), "13286378616");
		//getElement("登录页", "密码").sendKeys(loginpassword);
		sendKeys(getElement("登录页", "密码"),"a222222" );
		//getElement("登录页", "登录").click();
		click(getElement("登录页", "登录"));
			Thread.sleep(5000);
		click(getElement("登录页", "客户企业（测试）"));
			Thread.sleep(5000);
			String conpanyActual = getElement(By.id("companyName")).getText();
			AssertionUtil.assertTextEquals(conpanyActual, "客户企业（测试）");
			click(getElement("主页", "企业"));
	}
		
//		List<WebElement> testElements = driver.findElements(By.className("panel-htop"));
//		testElements.get(5).findElement(By.className("panel-title")).click();
		//Thread.sleep(10000);
		//driver.findElement(By.xpath("//div[text()='收入管理']")).click();
		//getElement("收入管理页", "收入管理").click();
		
		
		
		/*click(getElement("收入管理页", "收入管理"));
		Thread.sleep(2000);
		//getElement("收入管理页", "收入发放V2").click();
		click(getElement("收入管理页", "收入发放V2"));
		Thread.sleep(2000);
		//getElement("上传费用单页", "上传费用单").click();
		click(getElement("上传费用单页", "上传费用单"));*/
		@Test(dependsOnMethods={"test1"})
		public void test2() throws Exception{
		logger.info("**********开始测试收入发放功能*************");
		click(getElement("收支分类页", "收支分类"));
		Thread.sleep(2000);
		click(getElement("收支分类页", "税前扣除专项管理"));
		Thread.sleep(2000);
		click(getElement("收支分类页", "员工导出"));
		Thread.sleep(15000);
		WebElement dElement = getElement(By.xpath("//div[@id='index_tabs']/div[2]/div[2]/div/iframe"));
		boolean dflag = dElement.isDisplayed();
		System.out.println("是否可显示"+dflag);
		String dclassAttribute = dElement.getAttribute("src");
		System.out.println(dclassAttribute);
	
		driver.switchTo().frame(dElement);
		Thread.sleep(2000);
		//Thread.sleep(2000);
		sendKeys(getElement("收支分类页", "请输入部门名称"), "123");
	}
}
