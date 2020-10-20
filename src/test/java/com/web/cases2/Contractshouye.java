package com.web.cases2;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.web.util.AssertionUtil;
import com.web.cases.Base;

public class Contractshouye extends Base{
	@Test(priority = 0)
	public void shouyeTest() throws Exception{
		totesturl("http://sitxqy.bndxqc.com");
		String shouyeActual = getElement(By.xpath("//div[text()='领先的信息运维机制严守数据安全;数据传输军用级别加密防止信息泄露']")).getText();
		System.out.println(shouyeActual);
		AssertionUtil.assertTextEquals(shouyeActual,"领先的信息运维机制严守数据安全;数据传输军用级别加密防止信息泄露");
		click(getElement("首页页", "首页"));
		click(getElement("首页页", "产品"));
		String chanpinActual = getElement(By.xpath("//span[text()='流程介绍']")).getText();
		AssertionUtil.assertTextEquals(chanpinActual,"流程介绍");
		click(getElement("首页页", "解决方案"));
		String fanganActual = getElement(By.xpath("//span[text()='解决方案']")).getText();
		AssertionUtil.assertTextEquals(fanganActual,"解决方案555");	
	}
	@Test(priority = 1)
	public void loginTest(){
		totesturl("http://sitxqy.bndxqc.com");
		click(getElement("首页页", "登录"));
		String forgetPw = getElement("登录页", "忘记密码").getText();
		AssertionUtil.assertTextEquals(forgetPw, "忘记密码");
		
		back();
		click(getElement("首页页", "免费注册"));
		String register = getElement("注册页", "会员注册").getText();
		AssertionUtil.assertTextEquals(register, "会员注册");
		back();
		String title = driver.getTitle();
		AssertionUtil.assertTextEquals(title, "薪签约");
	}
}
