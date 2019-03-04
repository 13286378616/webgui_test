package com.contract.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	/**从excel文件中读取数据
	 * @return
	 */
	public static Object[][] read(String filePath,String sheetName,int startRownum,int endRownum,int startCellnum,int endCellnum) {
		//首先获取一个workbook对象
		InputStream iStream = null;
		//先声明数组
		Object [][] datas = null;
		try {
			iStream = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(iStream);
			//拿到一个Sheet
			Sheet sheet = workbook.getSheet(sheetName);
			//初始化数组
			datas = new Object [endRownum-startRownum+1][endCellnum-startCellnum+1];
			//循环取出行
			for (int i = startRownum; i <= endRownum; i++) {
				//拿到要操作的Row对象
				Row row = sheet.getRow(i-1);		//i-1是索引，所以要用行号减一
				//拿到要操作的cell对象（循环取出列）
				for (int j = startCellnum; j <= endCellnum; j++) {
					//根据列索引，取出列
					Cell cell = row.getCell(j-1,MissingCellPolicy.CREATE_NULL_AS_BLANK);   //j-1是列号索引值，missingcellpolicy是枚举类
					//将列设置为字符串类型
					cell.setCellType(CellType.STRING);
					//取出当前列的值
					String value = cell.getStringCellValue();
					//将取到的值放入数组
					datas [i-startRownum][j-startCellnum] = value;
				}
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//关闭输入流对象
		}finally {
			if(iStream!=null){
				try {
					iStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return datas;
	}

	/**
	 * @param fielPath 要读取的文件路径
	 * @param sheetName 表单名
	 * @param cellNames 要读取的列名（数组）
	 * @return
	 * @throws Exception 
	 */
	public static Object[][] read2(String filePath, String sheetName, String [] cellNames){
		InputStream iStream = null;	
		ArrayList<ArrayList<String>> groups = null;
		try {
			//准备输入流对象
			iStream = new FileInputStream(new File(filePath));
			//获取workbook对象
			Workbook workbook = WorkbookFactory.create(iStream);
			//获取表单对象
			Sheet sheet = workbook.getSheet(sheetName);
			//准备map，保存标题和它所在的列索引之间的映射
			Map<String,Integer> cellNameAndCellnumMap = new HashMap<String,Integer>();
			//取出标题行，获取所有的标题数据，以及每一个标题所在的列索引
			Row titleRow = sheet.getRow(0);
			//取出表单中列的个数
			int lastCellnum = titleRow.getLastCellNum();
			//循环取出标题行的每一列，即每一个标题
			for (int i = 0; i < lastCellnum; i++) {
				Cell cell = titleRow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				//标题
				String title = cell.getStringCellValue();
				//取出当前标题列所在的列位置（列索引）
				int cellNum = cell.getAddress().getColumn();
				cellNameAndCellnumMap.put(title, cellNum);
			}
			//获取最后一行的索引
			int lastRowNum = sheet.getLastRowNum();
			//保存每一组数据（一行相当于一组）
			groups = new ArrayList<ArrayList<String>>();
			//从第二行开始解析，读取表单中的所有数据行
			for (int i = 1; i <= lastRowNum; i++) {		
				//获取一行
				Row row = sheet.getRow(i);
				//如果是空行
				if (isEmpty(row)) {
					//跳过，不处理
					continue;
				}
				//处理每一行之前，先准备一个list集合，将后面读到的列数据保存在这个list集合中，此list集合就是一组数据
				ArrayList<String> cellValuesPerRow = new ArrayList<String>();
				//取出此行上面对应的列数据
				for (int j = 0; j < cellNames.length; j++) {
					String cellName = cellNames[j];
					//根据列名，从map中获取列索引
					int cellnum = cellNameAndCellnumMap.get(cellName);
					//根据列索引，取出列对象
					Cell cell = row.getCell(cellnum,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					//取出列的值
					String value = cell.getStringCellValue();
					//将值保存到list当中
					cellValuesPerRow.add(value);
				}
				//处理完了一行，就将此行的数据添加到组当中
				groups.add(cellValuesPerRow);
			}
			//System.out.println("lastCellnum="+lastCellnum);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(iStream!=null){
				try {
					iStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//将list集合中的数据保存到二维数组中返回
		return listToArray(groups);
	}
	private static Object[][] listToArray(ArrayList<ArrayList<String>> groups) {
		//获取总共的组数
		int size1 = groups.size();
		//获取每一组的数据个数
		int size2 = groups.get(0).size();
		//声明二维数组
		Object [][] datas = new Object[size1][size2];
		//循环
		for (int i = 0; i < size1; i++) {
			//取出每一组
			ArrayList<String> group= groups.get(i);
			//循环
			for (int j = 0; j < size2; j++) {
				//取出每组当中的每个数据
				String value = group.get(j);
				//将数据保存到二维数组当中
				datas[i][j] = value;
			}
		}
		//返回二维数组
		return datas;
	}

	private static boolean isEmpty(Row row){
		int lastCellnum = row.getLastCellNum();
		for (int i = 0; i < lastCellnum; i++) {
			Cell cell = row.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue();
			if (value!=null&&value.trim().length()>0) {
				return false;
			}
		}
		return true;
	}

}
