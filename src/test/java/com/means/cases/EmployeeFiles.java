package com.means.cases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;

public class EmployeeFiles extends BaseElectron {
	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-21。脚本修改时间：2019-5-21。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述：添加员工类别
	 * 
	 * @throws Exception
	 */
	@Test(priority = 0, enabled = false)
	public void addEnployeeTypeSuccessCase() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "员工档案"));
		click(getElement("员工档案页", "增加"));
		sendKeys(getElement("员工档案页", "类型名称"), "员工类别陆涛测试专用");
		click(getElement("员工档案页", "确定"));
		Thread.sleep(1000);
		String addEmployeeType = getText(getElement("员工档案页", "提示信息"));
		AssertionUtil.assertTextEquals(addEmployeeType, "新增员工类型成功");
		Thread.sleep(1000);
		// 滚动条滚动到指定位置
		WebElement elementNewAdd = getElement("员工档案页", "员工类别陆涛测试专用");
		getText(elementNewAdd);
		scrollbottom(elementNewAdd);
		Thread.sleep(1000);
		click(getElement("员工档案页", "员工类别陆涛测试专用"));
	}

	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-21。脚本修改时间：2019-5-21。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述： 类别编号重复添加
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void addEnployeeTypefaildCase() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "员工档案"));
		click(getElement("员工档案页", "增加"));
		Thread.sleep(3000);
		clear(getElement("员工档案页", "类型编号"));
		sendKeys(getElement("员工档案页", "类型编号"), "9527");
		sendKeys(getElement("员工档案页", "类型名称"), "员工类别陆涛测试专用");
		click(getElement("员工档案页", "确定"));
		Thread.sleep(1000);
		String addEmployee = getText(getElement("员工档案页", "提示信息"));
		AssertionUtil.assertTextEquals(addEmployee, "员工类型编号已存在，新增失败");
		click(getElement("员工档案页", "取消"));
	}

	/**
	 * 在类别9527加入员工
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void addEnployeeSuccessCase() throws Exception {
		// 滚动条滚动到指定位置
		WebElement element9527 = getElement("员工档案页", "9527");
		getText(element9527);
		scrollbottom(element9527);
		Thread.sleep(1000);
		// 点击员工类别为9527的数据
		click(getElement("员工档案页", "9527"));
		click(getElement("员工档案页", "增加A"));
		sendKeys(getElement("员工档案页", "姓名"), "自动化员工");
		sendKeys(getElement("员工档案页", "工号"), "123");
		sendKeys(getElement("员工档案页", "手机"), "13312345566");
		sendKeys(getElement("员工档案页", "电话"), "07608556956");
		sendKeys(getElement("员工档案页", "籍贯"), "阿姆斯特丹");
		sendKeys(getElement("员工档案页", "身份证"), "450312199905061568");
		sendKeys(getElement("员工档案页", "出生日期"), "2010-01-1");
		sendKeys(getElement("员工档案页", "入职日期"), "2019-05-16");
		sendKeys(getElement("员工档案页", "离职日期"), "2019-10-16");
		sendKeys(getElement("员工档案页", "职务"), "经理");
		sendKeys(getElement("员工档案页", "QQ"), "5566112233");
		click(getElement("员工档案页", "标签"));
		click(getElement("员工档案页", "员工标签陆涛测试专用"));
		click(getElement("员工档案页", "确认"));
		sendKeys(getElement("员工档案页", "邮箱"), "56564@163.com");
		sendKeys(getElement("员工档案页", "地址"), "广东中山市西区天悦城6666");
		click(getElement("员工档案页", "保存S"));
		Thread.sleep(1000);
		String addEmployee = getText(getElement("员工档案页", "提示信息"));
		AssertionUtil.assertTextEquals(addEmployee, "新增员工信息成功");

	}
}
