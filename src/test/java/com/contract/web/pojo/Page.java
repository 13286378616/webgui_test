package com.contract.web.pojo;

import java.util.List;

/**页面对象类型
 * @author Administrator
 *
 */
public class Page {
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * list集合page底下的所有页面元素对象
	 */
	private List<UIElement> elements;
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public List<UIElement> getElements() {
		return elements;
	}
	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}
	
}
