package com.contract.web.cases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.util.AssertionUtil;
import com.contract.web.util.ExcelUtil;

public class RegisterCase2v2 extends Base{
	private Logger logger = Logger.getLogger(RegisterCase2v2.class);
	/**
	 * 测失败的用例：不输入用户名的情况
	 */
	@Test(dataProvider="failCaseDatas" ,enabled = false)
	public void failcase(String mobilephone,String pwd,String confirmpwd,String expected ){
		String url = "http://39.108.136.60:8085/lmcanon_web_auto/mng/register.html";
		totesturl(url);
		//找到用户名输入框，输入手机号 
		sendKeys(getElement("注册页", "用户名"),mobilephone);
		//找到密码输入框，输入密码
		sendKeys(getElement("注册页", "密码"),pwd);
		//找到重复密码输入框，输入重复密码
		sendKeys(getElement("注册页", "重复密码"),confirmpwd);
		//直接点击注册
		click(getElement("注册页", "注册按钮"));
		String actual = getText(getElement("注册页", "错误提示"));
		AssertionUtil.assertTextEquals(actual, expected);
	}
	@Test(dataProvider = "successCaseDatas")
	public void successCase(String mobilephone,String pwd,String confirmpwd){
		driver.navigate().to("http://gs.bndxqc.com/login.html");
		//找到用户名输入框，输入手机号 
		driver.findElement(By.id("username")).sendKeys(mobilephone);;
		//找到密码输入框，输入密码
		driver.findElement(By.id("password")).sendKeys(pwd);;
		//找到重复密码输入框，输入重复密码
		//driver.findElement(By.id("pwdconfirm")).sendKeys(confirmpwd);
		//直接点击注册
		//driver.findElement(By.id("signup-button")).click();
		//拿到跳转页面的url地址
//		String currentUrl = driver.getCurrentUrl();
		boolean flag = urlPresenceContent("login.html");
		Assert.assertTrue(flag);
	}
	@DataProvider
	public Object [][] failCaseDatas(){
		//定义一个数组，声明要取的列
		String [] cellNames = {"手机号","密码","重复密码","期望值"};
		Object [][] datas = ExcelUtil.read2("src/test/resources/register.xlsx","DL-1",cellNames);
		return datas;
	}
	@DataProvider
	public Object [][] successCaseDatas(){
		String [] cellNames = {"手机号","密码","重复密码"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/register.xlsx","DL-2",cellNames);
		return datas;
	}
	 /*public static void main(String[] args) {
		 String [] cellNames = {"手机号","密码","重复密码"}; 
		 Object [][] datas= ExcelUtil.read2("src/test/resources/register.xlsx", "DL-1", cellNames);
		 for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
		 System.out.println(datas);
	}*/
}
	
