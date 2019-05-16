package com.purchase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;
import com.contract.web.util.ExcelUtil;

public class TotalnumberOfRecords extends BaseElectron {
	@Test(priority = 0, dataProvider = "pageNames")
	public void successCase(String menuname, String pagename) throws Exception {
		click(getElement("首页页", menuname));
		click(getElement("进货页", pagename));
		Thread.sleep(3000);
		// 创建动作对象，并且指定操作的浏览器
		// WebElement serialNumber = getElement("进货页", "序号1");
		// // 鼠标操作，用到actions方法
		// Actions actions = new Actions(driver);
		// // actions.doubleClick(serialNumber).perform();
		// // 右键序号1
		// actions.contextClick(serialNumber).perform();
		contextClick(getElement("进货页", "序号1"));
		Thread.sleep(2000);
		String totalnumberOfRecords = getText(getElement("进货页", "总记录数"));
		// 断言总记录数是否等于0，等于0即为失败
		AssertionUtil.assertTextnotEquals(totalnumberOfRecords, "总记录数:0");
		// 鼠标需要移动到页面标题，否则关闭按钮不显示，导致无法点击
		Thread.sleep(1000);
		// actions.moveToElement(getElement("首页页", pagename)).perform();
		closePage(getElement("首页页", pagename));
	}

	@DataProvider(name = "pageNames")
	public Object[][] pageNameDatas() {
		String[] cellNames = { "菜单名称", "菜单页面名称" };
		Object[][] datas = ExcelUtil.read2("src/test/resources/menuName.xlsx", "DL-1", cellNames);
		return datas;

	}
}
