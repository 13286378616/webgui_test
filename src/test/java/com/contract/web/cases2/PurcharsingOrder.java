package com.contract.web.cases2;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;

@Listeners({ com.contract.web.util.AssertionListener.class })
public class PurcharsingOrder extends BaseElectron {
	@Test(priority = 0)
	public void successCase() throws Exception {
		// 切换到最新窗口
		for (String handle : driver.getWindowHandles()) {
			System.out.println(handle);
			driver.switchTo().window(handle); // Since there are two window
												// handles this would switch to
												// last one(which is second
												// one). You can also explicitly
												// provide the window number.
		}
		Thread.sleep(3000);

		click(getElement("首页页", "进货"));
		click(getElement("进货页", "采购订货单"));
		Thread.sleep(1000);
		for (String handle : driver.getWindowHandles()) {
			System.out.println(handle);
			driver.switchTo().window(handle);
		}
		click(getElement("进货页", "新单"));
		Thread.sleep(3000);
		// 判断是否使用旧的单据，这里选择是
		By xpath;
		boolean cElement = AssertionUtil.ElementExist(driver, By.xpath("//p[text()='当前用户今天有一份空单未使用，调出来使用吗？']"));
		System.out.println("空单是否存在：" + cElement);
		if (cElement == true) {
			// 存在空单，这里选择是
			click(getElement("进货页", "否"));
		} else {
			System.out.println("没有旧空白单据使用");
		}
		Thread.sleep(3000);
		// 搜索并选择供应商
		click(getElement("进货页", "供应商"));
		sendKeys(getElement("进货页", "搜索"), "华北供应商_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "供应商搜索结果"));
		// 搜素并选择机构
		Thread.sleep(1000);
		click(getElement("进货页", "机构"));
		sendKeys(getElement("进货页", "搜索"), "华北机构_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "机构搜索结果"));
		// 搜索并选择业务员
		click(getElement("进货页", "业务员"));
		sendKeys(getElement("进货页", "搜索"), "陆涛");
		Thread.sleep(1000);
		click(getElement("进货页", "业务员搜索结果"));
		// 搜索并选择经手人
		click(getElement("进货页", "经手人"));
		sendKeys(getElement("进货页", "搜索"), "陆涛");
		Thread.sleep(1000);
		click(getElement("进货页", "经手人搜索结果"));
		// 销售负责人输入
		sendKeys(getElement("进货页", "销售负责人"), "销售负责人测试");
		// 备注输入
		sendKeys(getElement("进货页", "备注"), "自动化测试");
		// 搜索并选择仓库
		click(getElement("进货页", "仓库"));
		sendKeys(getElement("进货页", "搜索"), "华北仓库1_陆涛测试专用");
		Thread.sleep(3000);
		click(getElement("进货页", "仓库搜索结果"));
		// 获取当前系统日期，并输入合同交货日期
		SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd ");
		String currentDate = dateFormat.format(new Date());
		sendKeys(getElement("进货页", "合同交货日期"), currentDate);
		// 搜索商品
		sendKeys(getElement("进货页", "品名"), "火腿测试专用");
		Thread.sleep(3000);
		// 创建动作对象，并且指定操作的浏览器
		WebElement goodsname = getElement("进货页", "商品名称");
		// 双击商品，用到actions方法
		Actions actions = new Actions(driver);
		actions.doubleClick(goodsname).perform();
		// 判断当前系统规格是否默认为散，如果为散则按F12修改为件
		Thread.sleep(1000);
		String Specifications = getText(getElement("销售页", "规格"));
		if (Specifications != "件") {
			sendKeysKeyboard(getElement("进货页", "备注"), Keys.F12);
		} else {
			System.out.println("规格为：件");
		}
		// 输入件数
		Thread.sleep(1000);
		getElement("进货页", "件数").clear();
		sendKeys(getElement("进货页", "件数"), "10");
		// 输入件价
		getElement("进货页", "件价").clear();
		sendKeys(getElement("进货页", "件价"), "500");
		// 件数备注
		sendKeys(getElement("进货页", "件数备注"), "自动化测试件数");
		// 按下确认键
		actions.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
		// 审核
		click(getElement("进货页", "审核"));
		Thread.sleep(3000);
		click(getElement("进货页", "确定"));
		Thread.sleep(2000);
		// 点击返回
		click(getElement("进货页", "返回"));
		Thread.sleep(2000);

		// 点击返回
		click(getElement("进货页", "返回"));
		Thread.sleep(2000);
		// 校验是否生成新的订货单
		/*
		 * String suppliername = getElement("进货页",
		 * "采购订货单校验供应商名称").getAttribute("textContent");
		 * System.out.println("采购订货单校验供应商名称:" + suppliername);
		 * AssertionUtil.assertTextEqualsNoInterruption(suppliername,
		 * "华北供应商_陆涛测试专用");
		 */
		// 校验是否自动返回首页，是否存在新单元素
		AssertionUtil.ElementExist(driver, By.xpath("//span[text()='新单N']"));
	}

	/*
	 * 导入商品明细
	 * 
	 */
	@Test(priority = 1)
	public void ExcelImportDetailSuccessCase() throws Exception {
		click(getElement("首页页", "进货"));
		click(getElement("进货页", "采购订货单"));
		click(getElement("进货页", "新单"));
		Thread.sleep(2000);
		// 判断是否使用旧的单据，这里选择是
		By xpath;
		boolean cElement = AssertionUtil.ElementExist(driver, By.xpath("//p[text()='当前用户今天有一份空单未使用，调出来使用吗？']"));
		System.out.println("空单是否存在：" + cElement);
		if (cElement == true) {
			// 存在空单，这里选择是
			click(getElement("进货页", "否"));
		} else {
			System.out.println("没有旧空白单据使用");
		}
		Thread.sleep(3000);
		click(getElement("进货页", "Excel表导入单据明细"));
		WebElement supplierNullPrompt = getElement("进货页", "提示信息");
		String nullPrompt = supplierNullPrompt.getText();
		AssertionUtil.assertTextEquals(nullPrompt, "供应商不能为空");
		// 搜索并选择供应商
		click(getElement("进货页", "供应商"));
		sendKeys(getElement("进货页", "搜索"), "华北供应商_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("进货页", "供应商搜索结果"));
		click(getElement("进货页", "Excel表导入单据明细"));
		Thread.sleep(3000);
		click(getElement("进货页", "浏览"));
		Thread.sleep(5000);
		// 调用autoit的脚本
		Runtime.getRuntime().exec("D:\\Desktop\\upfile.exe");
		Thread.sleep(5000);
		click(getElement("进货页", "导入"));
		Thread.sleep(2000);
		// excel表导入明细导入后，提示信息校验
		WebElement goodsDetailImport = getElement("进货页", "提示信息");
		String goodsImport = goodsDetailImport.getText();
		AssertionUtil.assertTextEqualsNoInterruption(goodsImport, "导入明细成功");
		Thread.sleep(3000);
		// 校验导入成功的行数是否为2

		String importLineNumber = getElement("进货页", "导入成功").getAttribute("textContent");

		AssertionUtil.assertTextEquals(importLineNumber, "2");
		System.out.println(
				driver.findElement(By.xpath("//*[@id='modal-body']/div[2]/div[2]")).getAttribute("textContent"));
		click(getElement("进货页", "关闭"));
		// 作废
		Thread.sleep(1000);
		click(getElement("进货页", "作废"));
		Thread.sleep(3000);
		click(getElement("进货页", "确认"));
		Thread.sleep(2000);
		// 校验是否自动返回首页，是否存在新单元素
		AssertionUtil.ElementExist(driver, By.xpath("//span[text()='新单N']"));

	}
}
