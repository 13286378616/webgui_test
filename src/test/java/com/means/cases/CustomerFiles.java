package com.means.cases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.contract.web.cases.BaseElectron;
import com.contract.web.util.AssertionUtil;

public class CustomerFiles extends BaseElectron {
	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-23。脚本修改时间：2019-5-23。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述：客户类别编号9527重复添加，验证重复增加类别，是否有正确提示
	 * 
	 * @throws Exception
	 */
	@Test(priority = 0, enabled = false)
	public void addCustomerSuccessCases() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "客户档案"));
		click(getElement("客户档案页", "增加"));
		Thread.sleep(3000);
		clear(getElement("客户档案页", "类型编号"));
		sendKeys(getElement("客户档案页", "类型编号"), "9527");
		sendKeys(getElement("客户档案页", "类型名称"), "客户类别陆涛测试专用");
		click(getElement("客户档案页", "确定"));
		Thread.sleep(1000);
		String addEmployee = getText(getElement("客户档案页", "提示信息"));
		AssertionUtil.assertTextEquals(addEmployee, "客户类型编号已存在，新增失败");
		click(getElement("客户档案页", "取消"));
	}

	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-23。脚本修改时间：2019-5-23。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述：在类别9527增加客户，验证增加客户功能是否正常
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void addCustomerSuccessCase() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "客户档案"));
		// 滚动条滚动到指定位置
		WebElement element9527 = getElement("客户档案页", "9527");
		getText(element9527);
		scrollbottom(element9527);
		// 点击客户类别为9527的数据
		Thread.sleep(1000);
		click(getElement("客户档案页", "9527"));
		click(getElement("客户档案页", "增加A"));
		for (int i = 0; i < 30; i++)
			sendKeys(getElement("客户档案页", "名称"), "自动化客户" + i);
		sendKeys(getElement("客户档案页", "联系人"), "奥巴马");
		sendKeys(getElement("客户档案页", "业务员"), "陆涛");
		click(getElement("客户档案页", "业务员搜索结果"));
		sendKeys(getElement("客户档案页", "电话"), "07608556956");
		sendKeys(getElement("客户档案页", "手机"), "17712345566");
		sendKeys(getElement("客户档案页", "传真"), "666999");
		sendKeys(getElement("客户档案页", "地址"), "身份进来就的烦死个人分隔号他如何给4545DF4￥#");
		sendKeys(getElement("客户档案页", "邮编"), "556684");
		click(getElement("客户档案页", "配送路线"));
		click(getElement("客户档案页", "石歧线"));
		sendKeys(getElement("客户档案页", "开户行"), "中国工商银行");
		sendKeys(getElement("客户档案页", "开户账号"), "688811225554478896422654445222");
		sendKeys(getElement("客户档案页", "信用额"), "10000000");
		click(getElement("客户档案页", "售价方式"));

		click(getElement("客户档案页", "价格策略"));
		click(getElement("客户档案页", "跟踪销售"));

		sendKeys(getElement("客户档案页", "收款上浮率"), "15");
		sendKeys(getElement("客户档案页", "累计积分"), "10");
		sendKeys(getElement("客户档案页", "网站"), "www.baidu.com");
		click(getElement("客户档案页", "标签"));
		sendKeys(getElement("客户档案页", "搜索"), "东城客户");
		click(getElement("客户档案页", "东城客户"));
		click(getElement("客户档案页", "确认"));
		sendKeys(getElement("客户档案页", "邮箱"), "133888666@163.com");
		click(getElement("客户档案页", "结业"));
		click(getElement("客户档案页", "关联供应商"));
		sendKeys(getElement("客户档案页", "搜索供应商"), "华北");
		click(getElement("客户档案页", "保存S"));
		Thread.sleep(1000);
		String addCustomer = getText(getElement("客户档案页", "提示信息"));
		AssertionUtil.assertTextEquals(addCustomer, "新增客户信息成功");

	}

	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-23。脚本修改时间：2019-5-23。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述：修改第一条数据，验证修改功能是否正常
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void modifyCustomerSuccessCase() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "客户档案"));
		// 滚动条滚动到指定位置
		WebElement element9527 = getElement("客户档案页", "9527");
		getText(element9527);
		scrollbottom(element9527);
		click(getElement("客户档案页", "9527"));
		click(getElement("客户档案页", "95270001"));
		click(getElement("客户档案页", "修改R"));
		sendKeys(getElement("客户档案页", "名称"), "修改客户自动化测试");
		click(getElement("客户档案页", "保存S"));
		String nameText = getText(getElement("客户档案页", "修改后名称"));
		AssertionUtil.assertTextEquals(nameText, "修改客户自动化测试");
	}

	/**
	 * 运行软件：electorn 。框架版本：selenium3.5 +JDK1.8+testng。项目名称：悦商云B2
	 * 脚本编写人：lutao。脚本创建时间：2019-5-23。脚本修改时间：2019-5-23。修改说明：无。输入参数：无。 输出参数：无。
	 * 脚本描述：删除第一条数据，验证修改功能是否正常
	 * 
	 * @throws Exception
	 * 
	 */
	@Test(priority = 3)
	public void deleteCustomerSuccessCase() throws Exception {
		click(getElement("首页页", "资料"));
		click(getElement("资料页", "客户档案"));
		// 滚动条滚动到指定位置
		WebElement element9527 = getElement("客户档案页", "9527");
		getText(element9527);
		scrollbottom(element9527);
		click(getElement("客户档案页", "9527"));
		click(getElement("客户档案页", "95270001"));
		click(getElement("客户档案页", "删除D"));
		click(getElement("客户档案页", "确认"));
		sendKeys(getElement("客户档案页", "客户编号"), "95270001");
		String deleteText = getText(getElement("客户档案页", "提示信息"));
		Thread.sleep(1000);
		AssertionUtil.assertTextEquals(deleteText, "暂无数据");

	}
}
