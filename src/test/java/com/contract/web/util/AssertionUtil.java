package com.contract.web.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionUtil {
	private static Logger logger = Logger.getLogger(AssertionUtil.class);
	/**断言两者一致情况
	 * @param actual
	 * @param expected
	 */
	public static void assertTextEquals(String actual,String expected){
		logger.info("断言比较两者的值是否一致，实际值为：【"+actual+"】，期望值为：【"+expected+"】");
		Assert.assertEquals(expected, actual,"两者不一致");
	}
	/*断言失败后继续执行
	 * 
	 * 
	 * */
	public static void assertTextEqualsNoInterruption(String actual,String expected){
		logger.info("断言比较两者的值是否一致，实际值为：【"+actual+"】，期望值为：【"+expected+"】");
		 Assertion.verifyEquals(expected, actual,"两者不一致");
	}
	/**断言为真的情况
	 * @param actual
	 */
	public static void assertTrue(boolean actual){
		logger.info("断言元素【"+actual+"】是否在页面显示");
		Assert.assertTrue(actual);
	}
	public static void assertE(){
		
	}
	/*判断元素是否存在
	 * 
	 * */
	public static boolean ElementExist(WebDriver driver,By webElement){
		logger.info("判断元素【"+webElement+"】是否存在");
	        try {
	            driver.findElement(webElement);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }

	    }
}
