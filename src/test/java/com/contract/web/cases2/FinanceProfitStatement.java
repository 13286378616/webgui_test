package com.contract.web.cases2;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;

@Listeners({com.contract.web.util.AssertionListener.class})
public class FinanceProfitStatement extends BaseElectron{
	@Test(priority=0)
	public void sucessCase() throws Exception{
		Thread.sleep(3000);
		click(getElement("首页页", "财务"));
		String name = getElement("财务页", "利润表").getAttribute("textContent");
		System.out.println("定位到的元素文本值为："+name);
		Thread.sleep(3000);
		click(getElement("财务页", "利润表"));
		Thread.sleep(3000);
		String firstEntryName = getElement("利润表页", "第一行项目名称").getAttribute("textContent");
		String secondEntryName = getElement("利润表页", "第二行项目名称").getAttribute("textContent");
		String thirdEntryName = getElement("利润表页", "第三行项目名称").getAttribute("textContent");
		String forthEntryName = getElement("利润表页", "第四行项目名称").getAttribute("textContent");
		String fifthEntryName = getElement("利润表页", "第五行项目名称").getAttribute("textContent");
		String sixthEntryName = getElement("利润表页", "第六行项目名称").getAttribute("textContent");
		String seventhEntryName = getElement("利润表页", "第七行项目名称").getAttribute("textContent");
		String eighthEntryName = getElement("利润表页", "第八行项目名称").getAttribute("textContent");
		String nigthEntryName = getElement("利润表页", "第九行项目名称").getAttribute("textContent");
		String tenthEntryName = getElement("利润表页", "第十行项目名称").getAttribute("textContent");
		String eleventhEntryName = getElement("利润表页", "第十一行项目名称").getAttribute("textContent");
		String twelfthName = getElement("利润表页", "第十二行项目名称").getAttribute("textContent");
		String thirteenthName = getElement("利润表页", "第十三行项目名称").getAttribute("textContent");
		String fourteenthName = getElement("利润表页", "第十四行项目名称").getAttribute("textContent");
		String fifteenthName = getElement("利润表页", "第十五行项目名称").getAttribute("textContent");
		String sixteenthName = getElement("利润表页", "第十六行项目名称").getAttribute("textContent");
		String seventeenthName = getElement("利润表页", "第十七行项目名称").getAttribute("textContent");
		String eighteenthName = getElement("利润表页", "第十八行项目名称").getAttribute("textContent");
		String nineteenthName = getElement("利润表页", "第十九行项目名称").getAttribute("textContent");
		//System.out.println(getElement("利润表页", "第十九行项目名称").getText());   //getText方法同getAttribute("textContent")
		AssertionUtil.assertTextEqualsNoInterruption(firstEntryName, "一、营业收入");
		AssertionUtil.assertTextEqualsNoInterruption(secondEntryName, "   减：营业成本");
		AssertionUtil.assertTextEqualsNoInterruption(thirdEntryName, "   税金及附加");
		AssertionUtil.assertTextEqualsNoInterruption(forthEntryName, "   销售费用");
		AssertionUtil.assertTextEqualsNoInterruption(fifthEntryName, "   管理费用");
		AssertionUtil.assertTextEqualsNoInterruption(sixthEntryName, "   财务费用");
		AssertionUtil.assertTextEqualsNoInterruption(seventhEntryName, "   资产减值损失");
		AssertionUtil.assertTextEqualsNoInterruption(eighthEntryName, "   加：公允价值变动净收益（损失以“-”号填列）");
		AssertionUtil.assertTextEqualsNoInterruption(nigthEntryName, "   投资收益(损失以“-”号填列)");
		AssertionUtil.assertTextEqualsNoInterruption(tenthEntryName, "二、营业利润(亏损以“-”号填列)");
		AssertionUtil.assertTextEqualsNoInterruption(eleventhEntryName, "   加：营业外收入");
		AssertionUtil.assertTextEqualsNoInterruption(twelfthName, "   减：营业外支出");
		AssertionUtil.assertTextEqualsNoInterruption(thirteenthName, "   其中：非流动资产处置净损失");
		AssertionUtil.assertTextEqualsNoInterruption(fourteenthName, "三、利润总额(亏损以“-”号填列)");
		AssertionUtil.assertTextEqualsNoInterruption(fifteenthName, "  减：所得税费用");
		AssertionUtil.assertTextEqualsNoInterruption(sixteenthName, "四、净利润(亏损以“-”号填列)");
		AssertionUtil.assertTextEqualsNoInterruption(seventeenthName, "五、每股收益：");
		AssertionUtil.assertTextEqualsNoInterruption(eighteenthName, "(一)基本每股收益");
		AssertionUtil.assertTextEqualsNoInterruption(nineteenthName, "(二)稀释每股收益");
	}
}
