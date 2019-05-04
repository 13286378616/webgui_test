package com.contract.web.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ElectronTest {
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");// You can skip this if chromedriver is already included in the PATH.

		   ChromeOptions options = new ChromeOptions();
		   options.setBinary("F:\\软件包\\B2\\B2测试\\B2.exe");//设置二进制文件，一定用绝对路径，不要用/的写法
		   DesiredCapabilities capabilities = new DesiredCapabilities();//负责启动服务端时的参数设置
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);//将参数options添加到设置中
		   ChromeDriver driver = new ChromeDriver(capabilities);//欺骗chromdriver启动electron
		   // Now, your electron app would have been opened. 
		   // Now if you open the dev tools using CMD+ALT+I you would notice two dev tools and first one being for the electron shell. We need to switch to the second window handle. Let's do that.
		   Thread.sleep(5000);
		   for (String handle : driver.getWindowHandles())
	       {
	         driver.switchTo().window(handle); // Since there are two window handles this would switch to last one(which is second one). You can also explicitly provide the window number.
	       }
		     // If you inspect using the Dev Tools, you would notice the second window Dev Tools corresponds to actual page you have opened.
		     // From here you can write the usual selenium script and it will work.
		   	driver.findElement(By.xpath("//a[@ng-click='login()']")).click();
		   	Thread.sleep(5000);
		   	//跳转后，会生成新的窗口，所以要跳转到最后这一个窗口，才能找到元素
		   	for (String handle : driver.getWindowHandles())
		       {
		         driver.switchTo().window(handle); // Since there are two window handles this would switch to last one(which is second one). You can also explicitly provide the window number.
		       }
		   	Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='进货']")).click();
		   	Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='销售']")).click();
		   	Thread.sleep(3000);
		   	driver.findElement(By.xpath("//span[text()='库存']")).click();
		 }

	}
