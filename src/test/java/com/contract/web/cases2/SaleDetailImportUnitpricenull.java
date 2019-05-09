package com.contract.web.cases2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;
import com.contract.web.util.ExcelUtil;

public class SaleDetailImportUnitpricenull extends BaseElectron {
	@Test(priority = 0, dataProvider = "salesslipNames")
	public void successCase(String salesslipname) throws Exception {
		click(getElement("首页页", "销售"));
		click(getElement("销售页", salesslipname));
		click(getElement("进货页", "新单"));
		Thread.sleep(3000);

		// 判断是否使用旧的单据，这里选择否
		boolean cElement = AssertionUtil.ElementExist(driver, By.xpath("//p[text()='当前用户今天有一份空单未使用，调出来使用吗？']"));
		System.out.println("空单是否存在：" + cElement);
		if (cElement == true) {
			// 存在空单，这里选择否
			click(getElement("进货页", "否"));
		} else {
			System.out.println("没有旧空白单据使用");
		}
		Thread.sleep(3000);
		// 搜索并选择客户
		click(getElement("销售页", "客户"));
		sendKeys(getElement("进货页", "搜索"), "华北客户_陆涛测试专用");
		Thread.sleep(1000);
		click(getElement("销售页", "客户搜索结果"));
		click(getElement("进货页", "Excel表导入单据明细"));
		Thread.sleep(3000);
		click(getElement("进货页", "浏览"));
		Thread.sleep(5000);
		// 调用autoit的脚本
		Runtime.getRuntime().exec("D:\\Desktop\\upfileUnitpricenull.exe");
		Thread.sleep(6000);
		click(getElement("进货页", "导入"));
		Thread.sleep(2000);
		// excel表导入明细导入后，提示信息校验
		WebElement goodsDetailImport = getElement("进货页", "提示信息");
		String goodsImport = goodsDetailImport.getText();
		AssertionUtil.assertTextEqualsNoInterruption(goodsImport, "导入明细成功");
		Thread.sleep(3000);
		// 校验导入成功的行数是否为3
		String importLineNumber = getElement("进货页", "导入成功").getAttribute("textContent");
		AssertionUtil.assertTextEquals(importLineNumber, "3");
		System.out.println(
				driver.findElement(By.xpath("//*[@id='modal-body']/div[2]/div[2]")).getAttribute("textContent"));
		click(getElement("进货页", "关闭"));
		Thread.sleep(1000);
		// 校验第一行数据是否为促销单价、促销散价
		String firstGetIp = getText(getElement("商品明细页", "第一行件价"));
		AssertionUtil.assertTextEquals(firstGetIp, "78");
		String firstGetSp = getText(getElement("商品明细页", "第一行散价"));
		AssertionUtil.assertTextEquals(firstGetSp, "4.8");
		// 校验第二行数据是否为历史单价、散价
		String secondGetIp = getText(getElement("商品明细页", "第二行件价"));
		AssertionUtil.assertTextEquals(secondGetIp, "888");
		String secondGetSp = getText(getElement("商品明细页", "第二行散价"));
		AssertionUtil.assertTextEquals(secondGetSp, "111");
		// 校验第三行数据是否为历史单价、散价
		String thirdGetIp = getText(getElement("商品明细页", "第三行件价"));
		AssertionUtil.assertTextEquals(thirdGetIp, "33");
		String thirdGetSp = getText(getElement("商品明细页", "第三行散价"));
		AssertionUtil.assertTextEquals(thirdGetSp, "6.6");

	}

	@DataProvider(name = "salesslipNames")
	public Object[][] salesslipNameDatas() {
		String[] cellNames = { "销售单名称" };
		Object[][] datas = ExcelUtil.read2("src/test/resources/salesslipName.xlsx", "DL-1", cellNames);
		return datas;

	}
}
