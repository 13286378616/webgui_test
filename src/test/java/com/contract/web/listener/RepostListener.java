package com.contract.web.listener;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.contract.web.util.ScreenUtil;


/*通过继承TestListenerAdapter来自定义一个监听器
 * 
 * 
 * 
 * */


public class RepostListener extends TestListenerAdapter{
	/*当某一个用例（测试方法）执行失败了，就进入到此方法
	 * 
	 * 改写这个方法
	 * 
	 * */
	@Override
	public void onTestFailure(ITestResult tr) {
		String basedir = "target/surefire-reports/screenshot";  //定义目录路径
		String testname = tr.getTestContext().getCurrentXmlTest().getName(); //测试上下文，取正在执行的test集的name值
		//目录分隔符：File.separator,相当于\
		basedir+=(File.separator+testname);
		String dateString = DateFormatUtils.format( new Date(), "yyyy-MM-dd");
		basedir+=(File.separator+dateString);
		//在basedir目录下保存截图
		File destFile = ScreenUtil.takeScreenshot(basedir);
		String absolutePath = destFile.getAbsolutePath();//拿到图片的绝对路径
		String toBeReplaced = absolutePath.substring(0, absolutePath.indexOf("screenshot"));//截取需要替换的路径
		String accessPath = absolutePath.replace(toBeReplaced, "http://localhost/");//替换成这个字段
		Reporter.log("<img src='"+accessPath+"' width='150' height='100'><a href='"+accessPath+"' target='_blank'>点击查看大图</a></img>");
	}
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd")); //日期格式化类
		System.out.println(File.separator);
		String absolutePath = "E:/workspace_java_aa/contract/target/screenshot/register/2018-08-9/12156.img";
		String toBeReplaced = absolutePath.substring(0, absolutePath.indexOf("screenshot"));
		System.out.println(toBeReplaced);
		String accessPath = absolutePath.replace(toBeReplaced, "http://localhost/");
		System.out.println(accessPath);
		
		
	}
}
