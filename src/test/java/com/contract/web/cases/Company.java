package com.contract.web.cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.util.ExcelUtil;

public class Company extends Base{
	@Test(dataProvider="loginsuc")
	public void succescase(String mobilename,String pwd,String expected) throws Exception{
		driver.navigate().to("http://gs.bndxqc.com/login.html");
		driver.findElement(By.id("username")).sendKeys(mobilename);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		List<WebElement> testelements = driver.findElements(By.className("layui-field-box"));
//		String actual = testelements.get(1).getText();
//		Assert.assertEquals(actual, expected);
		testelements.get(1).click();
		Thread.sleep(3000);
		getElement(By.id("25")).findElement(By.className("l-btn-text")).click();
		
}
	@DataProvider(name="loginsuc")
	public Object [][] failcasedatas(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/login.xlsx","DL-2",cellNames);
		return datas;
}
}