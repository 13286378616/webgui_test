package com.contract.web.cases2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.cases.Base;
import com.contract.web.util.AssertionUtil;
import com.contract.web.util.ExcelUtil;

public class ContractLogin extends Base{
	@Test(dataProvider="faildCaseDatasmobile",priority=0)
	public void faildcasemobile(String mobile,String pwd,String expectedvalue){
		totesturl("http://sitxqy.bndxqc.com/#/login");
		sendKeys(getElement("登录页", "请输入用户名"),mobile);
		sendKeys(getElement("登录页", "请输入密码"), pwd);
		click(getElement("登录页", "登录"));
		String actualvalue= getText(getElement("登录页", "手机号码校验"));
		AssertionUtil.assertTextEquals(actualvalue, expectedvalue);
		
	}
	
	@Test(dataProvider="faildCaseDataspw",priority=1)
	public void faildcasepw(String mobile,String pwd,String expectedvalue){
		totesturl("http://sitxqy.bndxqc.com/#/login");
		sendKeys(getElement("登录页", "请输入用户名"),mobile);
		sendKeys(getElement("登录页", "请输入密码"), pwd);
		click(getElement("登录页", "登录"));
		String actualvalue= getText(getElement("登录页", "密码校验"));
		AssertionUtil.assertTextEquals(actualvalue, expectedvalue);
	}
	
	@Test(dataProvider="successCaseDatasmobile",priority=2)
	public void successcase(String mobile,String pwd,String expectedvalue){
		totesturl("http://sitxqy.bndxqc.com/#/login");
		sendKeys(getElement("登录页", "请输入用户名"),mobile);
		sendKeys(getElement("登录页", "请输入密码"), pwd);
		click(getElement("登录页", "登录"));
//		String actualvalue= getText(getElement("登录页", "密码校验"));
//		AssertionUtil.assertTextEquals(actualvalue, expectedvalue);
	}
	@DataProvider
	public Object [][] faildCaseDataspw(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/logincontract.xlsx","DL-3",cellNames);
		return datas;
	}
	@DataProvider
	public Object [][] faildCaseDatasmobile(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/logincontract.xlsx","DL-1",cellNames);
		return datas;
	}
	@DataProvider
	public Object [][] successCaseDatasmobile(){
		String [] cellNames = {"手机号","密码","期望值"}; 
		Object [][] datas= ExcelUtil.read2("src/test/resources/logincontract.xlsx","DL-2",cellNames);
		return datas;
	}
}
