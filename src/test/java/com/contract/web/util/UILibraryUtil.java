package com.contract.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.contract.web.pojo.Page;
import com.contract.web.pojo.UIElement;


/**解析ui库，提供页面元素的信息
 * @author Administrator
 *
 */
public class UILibraryUtil {
	//准备一个List集合保存所有的Page对象
	public static List<Page> pageList = new ArrayList<Page>();
	//因为静态代码块是最先执行的，所有我们可以通过静态代码块来提前准备所有的元素数据
	static{
		parse();
	}
	/**从pageList里面取出满足条件的页面元素返回
	 * @param pageKeyword
	 * @param elementKeyword
	 * @return
	 */
	public static UIElement getUIElement(String pageKeyword,String elementKeyword){
		for (Page page : pageList) {
			if (pageKeyword.equals(page.getKeyWord())) {
				List<UIElement> elements = page.getElements();
				for (UIElement uiElement : elements) {
					if (elementKeyword.equals(uiElement.getKeyword())) {
						return uiElement;
					}
				}
			}
			
		}
		return null;
	}
	private static void parse() {
		//UI库文件路径
		String filepath = "src/test/resources/UILibrary.xml";
		//创建解析器
		SAXReader reader = new SAXReader();
		try {
			//获取文档对象
			Document document = reader.read(new File(filepath));
			//获取根节点
			Element root = document.getRootElement();
			//获取根元素底下的所有的Page元素
			List<Element> pList = root.elements("Page");
			//循环处理每一个Page元素
			for (Element page : pList) {
				//获取page的关键字信息
				String keyword = page.attributeValue("keyword");
				//获取当前Page底下的所有UIElement
				List<Element> uiElements = page.elements("UIElement");
				List<UIElement> elements = new ArrayList<UIElement>(); 
				for (Element element : uiElements) {
					String elementKeyword = element.attributeValue("keyword");
					String elementBy = element.attributeValue("by");
					String elementValue = element.attributeValue("value");
					UIElement uiElement = new UIElement();
					uiElement.setKeyword(elementKeyword);
					uiElement.setBy(elementBy);
					uiElement.setValue(elementValue);
					elements.add(uiElement);
				}
				Page pg = new Page();
				pg.setKeyWord(keyword);
				pg.setElements(elements);
				//将解析出来的Page对象统一 保存到pageList集合
				pageList.add(pg);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//获取跟节点
		//通过根节点遍历子节点
	}
}
