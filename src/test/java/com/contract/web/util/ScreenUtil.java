package com.contract.web.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.contract.web.cases.Base;

public class ScreenUtil {

	public static File takeScreenshot(String basedir) {
		WebDriver driver = Base.driver;
		File screenImg = null;
		if(driver instanceof FirefoxDriver){
			//向下转型
			FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
			screenImg = firefoxDriver.getScreenshotAs(OutputType.FILE);
		}else if(driver instanceof ChromeDriver){
			ChromeDriver chromeDriver = (ChromeDriver) driver;
			screenImg = chromeDriver.getScreenshotAs(OutputType.FILE);
		}else if(driver instanceof InternetExplorerDriver){
			InternetExplorerDriver internetExplorerDriver = (InternetExplorerDriver) driver;
			screenImg = internetExplorerDriver.getScreenshotAs(OutputType.FILE);
		}
		Date date = new Date();
		long time = date.getTime();
		File destFile = new File(basedir+File.separator+time+".img");
		try {
			FileUtils.copyFile(screenImg, destFile);
		} catch (IOException e) {
			System.out.println("拷贝出错");
		}
		return destFile;
	}

}
