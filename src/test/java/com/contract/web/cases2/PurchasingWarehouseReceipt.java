package com.contract.web.cases2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class PurchasingWarehouseReceipt extends BaseElectron{
	@Test(priority=0,enabled=false)
	public void successCase() throws Exception{
		//切换到最新窗口
		for (String  handle : driver.getWindowHandles()) {
			System.out.println(handle);
			driver.switchTo().window(handle);
		}
		click(getElement("首页页", "进货"));
		click(getElement("进货页", "采购进仓单"));
		click(getElement("进货页", "新单"));
		Thread.sleep(3000);
		
		//判断是否使用旧的单据，这里选择否
		By xpath;
		boolean cElement = AssertionUtil.ElementExist(driver,By.xpath("//p[text()='当前用户今天有一份空单未使用，调出来使用吗？']"));
		System.out.println("空单是否存在："+cElement);
		 if (cElement==true) {
			//存在空单，这里选择是
			click(getElement("进货页", "是"));
		} else {
			System.out.println("没有旧空白单据使用");
		}
		 Thread.sleep(3000);
		//搜索并选择供应商
		click(getElement("进货页", "供应商"));
		sendKeys(getElement("进货页","搜索"), "华北供应商_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "供应商搜索结果"));
		//搜索并选择机构
		click(getElement("采购进仓单页", "机构"));
		sendKeys(getElement("进货页", "搜索"), "华北机构_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "机构搜索结果"));
		//搜索并选择业务员
		click(getElement("进货页", "业务员"));
		sendKeys(getElement("进货页", "搜索"), "陆涛");
		Thread.sleep(1000);
		click(getElement("进货页", "业务员搜索结果"));
		//搜素并选择经手人
		click(getElement("进货页", "经手人"));
		sendKeys(getElement("进货页", "搜索"), "陆涛");
		Thread.sleep(1000);
		click(getElement("进货页", "经手人搜索结果"));
		//搜索并选择仓管员
		click(getElement("进货页", "仓管员"));
		sendKeys(getElement("进货页", "搜索"), "陆涛");
		Thread.sleep(1000);
		click(getElement("进货页", "仓管员搜索结果"));
		//备注测试
		sendKeys(getElement("进货页", "备注"), "自动化测试采购进仓单");
		//搜索并选择仓库
		click(getElement("进货页", "仓库"));
		sendKeys(getElement("进货页", "搜索"), "华北仓库1_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "仓管搜索结果"));
		//搜索商品
		 sendKeys(getElement("进货页", "品名"), "火腿测试专用");
		 Thread.sleep(3000);
		 //创建动作对象，并且指定操作的浏览器
		 WebElement goodsname=getElement("进货页", "商品名称");
		//双击商品，用到actions方法
		 Actions actions = new Actions(driver);
		 actions.doubleClick(goodsname).perform();
		//输入件数
		 getElement("进货页", "件数").clear();
		 sendKeys(getElement("进货页", "件数"), "10");
		 //输入件价
		 getElement("进货页", "件价").clear();
		 sendKeys(getElement("进货页", "件价"), "500");
		 //件数备注
		 sendKeys(getElement("进货页", "件数备注"),"自动化测试件数");
		 //按下确认键
		 actions.sendKeys(Keys.ENTER).perform();
		 //点击返回
		 click(getElement("进货页", "返回"));
		 Thread.sleep(2000);
		 //校验是否生成新的订货单
		 String suppliername = getElement("进货页", "采购进仓单校验供应商名称").getAttribute("textContent");
		 System.out.println("订单校验供应商名称:"+suppliername);
		 AssertionUtil assertionUtil = new AssertionUtil();
		 assertionUtil.assertTextEquals(suppliername, "华北供应商_陆涛测试专用");
	}
}
