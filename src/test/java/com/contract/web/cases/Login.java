package com.contract.web.cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.util.AssertionUtil;
import com.contract.web.util.ExcelUtil;

public class Login extends Base{
	@Test(dataProvider="faildCase")
	public void failcase(String mobilename,String pwd,String expected){
		totesturl("http://gs.bndxqc.com/login.html");
		getElement(By.id("username")).sendKeys(mobilename);
		getElement(By.id("password")).sendKeys(pwd);
		getElement(By.id("login")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actruel=getElement(By.id("errorShow")).getText();
		//Assert.assertEquals(actruel, expected);
		AssertionUtil.assertTextEquals(actruel, expected);
		
	}
	@Test(dataProvider="successCase")
	public void succescase(String mobilename,String pwd,String expected){
		totesturl("http://gs.bndxqc.com/login.html");
		getElement(By.id("username")).sendKeys(mobilename);
		getElement(By.id("password")).sendKeys(pwd);
		getElement(By.id("login")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> testelements = driver.findElements(By.className("layui-field-box"));
		String actual = testelements.get(1).getText();
		Assert.assertEquals(actual, expected);
	}
	@DataProvider(name="successCase")
	public Object [][] successCaseDatas(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/login.xlsx","DL-2",cellNames);
		return datas;
}
	@DataProvider(name="faildCase")
	public Object [][] failcasedatas(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/login.xlsx","DL-1",cellNames);
		return datas;
}
}
