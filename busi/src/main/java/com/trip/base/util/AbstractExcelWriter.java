package com.trip.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AbstractExcelWriter {
	
	/**
	 * 存储已加载过的字体
	 */
	protected Map<String, XSSFFont> fontMap = new HashMap<String, XSSFFont>();
	
	/**
	 * 存储已加载过的样式
	 */
	protected Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
	
	/**
	 * excel内置对象
	 */
	protected XSSFWorkbook workbook;
	
	/**
	 * 获取表头列Font样式（用户自定义）
	 * @return
	 */
	protected  abstract XSSFFont getTitleFont(); 
	
	/**
	 * 获取数据内容列Font样式（用户自定义）
	 * @return
	 */
	protected  abstract XSSFFont getDataFont(); 
	
	/**
	 * 获取数据表头列行高
	 * @return
	 */
	protected  abstract float getTitleRowHeight(); 
	
	/**
	 * 获取数据表头列背景颜色
	 * @return
	 */
	protected  abstract short getForeground(); 
	
	/**
	 * 获取数据数据列行高
	 * @return
	 */
	protected  abstract float getDataRowHeight(); 
	
	/**
	 * 传递给excel的参数
	 */
	protected Map<String,String> params;
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	protected AbstractExcelWriter() {
		// TODO Auto-generated constructor stub
		this.workbook = new XSSFWorkbook();
	}
	
	/**
	 * 得到导出表头样式
	 * @param isFirst 是否首列
	 * @param isLast 是否尾列
	 * @return
	 */
	protected XSSFCellStyle getTableTitileStyle(boolean isFirst,boolean isLast) {
		String key = "title"+isFirst+isLast;
		if(styles.get(key) == null){
			XSSFCellStyle style = workbook.createCellStyle();
			//创建表头背景颜色
			if(getForeground() != -1){
				style.setFillForegroundColor(getForeground());
				style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			}
			style.setFont(getTitleFont());// 设置字体
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置对齐方式
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);// 下边框
			style.setBorderRight(isLast?XSSFCellStyle.BORDER_MEDIUM:XSSFCellStyle.BORDER_THIN);// 右边框，如果是最末列加粗
			style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);// 上边框
			style.setBorderLeft(isFirst?XSSFCellStyle.BORDER_MEDIUM:XSSFCellStyle.BORDER_NONE);// 左边框，如果是最前列加粗
			//防止样式被重复创建
			styles.put(key, style);
		}
		

		return styles.get(key);
	}
	
	/**
	 * 得到导出数据列样式
	 * @param isFirst 是否首列
	 * @param isLast 是否尾列
	 * @param isLastRow 是否尾行数据
	 * @return
	 */
	protected XSSFCellStyle getDataStyle(boolean isFirst,boolean isLast,boolean isLastRow) {
		String key = "data"+isFirst+isLast+isLastRow;
		if(styles.get(key) == null){
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFont(getDataFont());// 设置字体
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置对齐方式
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			style.setBorderLeft(isFirst?XSSFCellStyle.BORDER_MEDIUM:XSSFCellStyle.BORDER_NONE);// 左边框，如果是最前列加粗
			style.setBorderBottom(isLastRow?XSSFCellStyle.BORDER_MEDIUM:XSSFCellStyle.BORDER_THIN);// 下边框,如果是最后行数据加粗
			style.setBorderRight(isLast?XSSFCellStyle.BORDER_MEDIUM:XSSFCellStyle.BORDER_THIN);// 右边框，如果是最末列加粗
			//防止样式被重复创建
			styles.put(key, style);
		}
		

		return styles.get(key);
	}
	
	public void writeDatas(Map<String, String> columnMap,List<Map> datas,int startRow,int startColumn){
		writeDatas(columnMap, datas, startRow, startColumn, workbook.getSheetName(0));
	}
	
	/**
	 * 定义excel写数据方法(纯数据)
	 * @param columnMap 表头字段映射map
	 * @param datas 实际数据容器，与columnMap映射
	 * @param startRow 写excel开始行
	 * @param startColumn 写excel结束行
	 * @param sheetName 要写的sheet名称
	 * @param params 要传递给excel的参数
	 * @return
	 */
	public void writeDatas(Map<String, String> columnMap,List<Map> datas,int startRow,int startColumn,String sheetName){
		//用来存放每列最大宽度的数组
		List<Integer> widths = new ArrayList<Integer>();
		//首先开始写表头列
		XSSFSheet sheet = workbook.getSheet(sheetName)==null ? workbook.createSheet(sheetName) : workbook.getSheet(sheetName);
		XSSFRow titlerow = sheet.createRow(startRow);
		//设置行高
		titlerow.setHeightInPoints(getTitleRowHeight());
		int startWriteCol = startColumn;
		for (Iterator i = columnMap.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, String> title = (Map.Entry<String, String>) i.next();
			//获取表头列样式
			XSSFCellStyle style= getTableTitileStyle(startWriteCol == startColumn, !i.hasNext());
			XSSFCell cell = titlerow.createCell(startWriteCol++);
			cell.setCellStyle(style);
			cell.setCellValue(title.getValue());
			widths.add(title.getValue().getBytes().length * 256 + 512);
		}
		for (Iterator i = datas.iterator(); i.hasNext();) {
			Map<String, Object> data = (Map<String, Object>) i.next();
			XSSFRow row = sheet.createRow(++startRow);
			row.setHeightInPoints(getDataRowHeight());
			startWriteCol = startColumn;
			for (Iterator j = columnMap.entrySet().iterator(); j.hasNext();) {
				Map.Entry<String, String> titleMap = (Map.Entry<String, String>) j.next();
				//获取数据列样式
				String value = data.get(titleMap.getKey()) == null ? "" : data.get(titleMap.getKey()).toString();
				widths.set(startWriteCol-startColumn, Math.max(widths.get(startWriteCol - startColumn), value.getBytes().length * 256 + 512));
				XSSFCellStyle style= getDataStyle(startWriteCol == startColumn, !j.hasNext(), !i.hasNext());
				XSSFCell cell = row.createCell(startWriteCol++);
				cell.setCellStyle(style);
				cell.setCellValue(value);
			}
		}
		
		//默认使用自动调节
		setColumnWidth(sheet,startColumn,widths);
	}
	
	public void writeDatas(Map<String, String> columnMap,List<?> datas,Class clazz,int startRow,int startColumn,String sheetName) throws Exception{
		//用来存放每列最大宽度的数组
		List<Integer> widths = new ArrayList<Integer>();
		//首先开始写表头列
		XSSFSheet sheet = workbook.getSheet(sheetName)==null ? workbook.createSheet(sheetName) : workbook.getSheet(sheetName);
		XSSFRow titlerow = sheet.createRow(startRow);
		//设置行高
		titlerow.setHeightInPoints(getTitleRowHeight());
		int startWriteCol = startColumn;
		for (Iterator i = columnMap.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, String> title = (Map.Entry<String, String>) i.next();
			//获取表头列样式
			XSSFCellStyle style= getTableTitileStyle(startWriteCol == startColumn, !i.hasNext());
			XSSFCell cell = titlerow.createCell(startWriteCol++);
			cell.setCellStyle(style);
			cell.setCellValue(title.getValue());
			widths.add(title.getValue().getBytes().length * 256 + 512);
		}
		for (Iterator i = datas.iterator(); i.hasNext();) {
			Object data = (Object) i.next();
			XSSFRow row = sheet.createRow(++startRow);
			row.setHeightInPoints(getDataRowHeight());
			startWriteCol = startColumn;
			for (Iterator j = columnMap.entrySet().iterator(); j.hasNext();) {
				Map.Entry<String, String> titleMap = (Map.Entry<String, String>) j.next();
				//获取数据列样式
				Method get=null;
				try {
					get = clazz.getMethod("get"+titleMap.getKey().substring(0,1).toUpperCase()+titleMap.getKey().substring(1), null);
				} catch (Exception e) {
				}
				String value = get==null||get.invoke(data, null) == null ? "" : get.invoke(data, null).toString();
				widths.set(startWriteCol-startColumn, Math.max(widths.get(startWriteCol - startColumn), value.getBytes().length * 256 + 512));
				XSSFCellStyle style= getDataStyle(startWriteCol == startColumn, !j.hasNext(), !i.hasNext());
				XSSFCell cell = row.createCell(startWriteCol++);
				cell.setCellStyle(style);
				cell.setCellValue(value);
			}
		}
		
		//默认使用自动调节
		setColumnWidth(sheet,startColumn,widths);
	}
	
	protected void setColumnWidth(int startCol,List<Integer> widths){
		setColumnWidth(workbook.getSheetAt(0),startCol,widths);
	}
	
	protected void setColumnWidth(XSSFSheet sheet,int startCol,List<Integer> widths){
		//自动调节列宽，符合表头和内容
		for (int i = 0; i < widths.size(); i++) {
			sheet.setColumnWidth(i + startCol, widths.get(i));
		}
	}
	
	protected void setValue(int row,int col,Object value){
		setValue(row,col,value,workbook.getSheetName(0));
	}
	
	/**
	 * 设置excel某列的值
	 * @param row 要设置的值所在行
	 * @param col 要设置的值所在列
	 * @param value 要设置的值
	 * @param sheetName 所在的excel sheet页名
	 */
	protected void setValue(int row,int col,Object value,String sheetName){
		workbook.getSheet(sheetName).getRow(row).getCell(col).setCellValue(value.toString());
	}
	
	/**
	 * 合并行或列
	 * @param row 要合并的起始行
	 * @param col 要合并的结束列
	 * @param endRow 要合并的结束行
	 * @param endCol 要合并的结束列
	 */
	protected void merge(int row,int col,int endRow,int endCol){
		merge(row, col, endRow, endCol, workbook.getSheetName(0));
	}
	
	/**
	 * 合并行或列
	 * @param row 要合并的起始行
	 * @param col 要合并的结束列
	 * @param endRow 要合并的结束行
	 * @param endCol 要合并的结束列
	 * @param sheetName 所在的excel sheet页名
	 */
	protected void merge(int row,int col,int endRow,int endCol,String sheetName){
		workbook.getSheet(sheetName).addMergedRegion(new CellRangeAddress(row, endRow, col, endCol));
	}
	
	/**
	 * 创建excel的一行
	 * @param rowIndex 行号
	 * @param rowHeight 行高
	 * @param sheetName 所在的excel sheet页名
	 */
	protected XSSFRow createRow(int rowIndex,float rowHeight,String sheetName){
		XSSFRow row = workbook.getSheet(sheetName).createRow(rowIndex);
		if(rowHeight > -1.0f)row.setHeightInPoints(rowHeight);
		return row;
	}
	
	protected XSSFRow createRow(int rowIndex){
		return createRow(rowIndex,getDataRowHeight());
	}
	
	protected XSSFRow createRow(int rowIndex,float rowHeight){
		return createRow(rowIndex, rowHeight, workbook.getSheetName(0));
	}
	
	protected XSSFCell createCell(int rowIndex,int colIndex){
		return createCell(rowIndex, colIndex, null);
	}
	
	protected XSSFCell createCell(int rowIndex,int colIndex,Object value){
		return createCell(rowIndex, colIndex, workbook.getSheetName(0), value);
	}
	
	protected XSSFCell createCell(int rowIndex,int colIndex,String sheetName,Object value){
		return createCell(rowIndex, colIndex, -1.0f, sheetName, value);
	}
	
	/**
	 * 创建excel的一列
	 * @param rowIndex 行号
	 * @param rowHeight 行高
	 * @param sheetName 所在的excel sheet页名
	 * @param colIndex 要创建的cell所在列号
	 * @param value 要设置的列值
	 */
	protected XSSFCell createCell(int rowIndex,int colIndex,float rowHeight,String sheetName,Object value){
		XSSFRow row = workbook.getSheet(sheetName).getRow(rowIndex);
		if(row == null){
			row = createRow(rowIndex,rowHeight,sheetName);
		}
		XSSFCell cell = row.createCell(colIndex);
		cell.setCellValue(value.toString());
		return cell;
	}
	
	/**
	 * 获取某一行
	 * @param rowIndex 行号
	 * @return
	 */
	protected XSSFRow getRow(int rowIndex){
		return getRow(rowIndex,workbook.getSheetName(0));
	}
	
	/**
	 * 获取某一行
	 * @param rowIndex 行号
	 * @param sheetName excel的sheet名
	 * @return
	 */
	protected XSSFRow getRow(int rowIndex,String sheetName){
		return workbook.getSheet(sheetName).getRow(rowIndex);
	}
	
	/**
	 * 获取某一列
	 * @param rowIndex 行号
	 * @param cellIndex 列号
	 * @return
	 */
	protected XSSFCell getCell(int rowIndex,int cellIndex){
		return getCell(rowIndex, cellIndex, workbook.getSheetName(0));
	}
	
	/**
	 * 获取某一列
	 * @param rowIndex 行号
	 * @param cellIndex 列号
	 * @param sheetName excel的sheet名
	 * @return
	 */
	protected XSSFCell getCell(int rowIndex,int cellIndex,String sheetName){
		return workbook.getSheet(sheetName).getRow(rowIndex).getCell(cellIndex);
	}
	
	protected XSSFFont getFont(String fontFamily,int size) {
		return getFont(fontFamily, size, HSSFColor.AUTOMATIC.index);
	}
	
	protected XSSFFont getFont(String fontFamily,int size,int color) {
		XSSFFont font = this.workbook.createFont();
		font.setColor((short) color);
		font.setFontHeightInPoints((short) size);
		font.setFontName(fontFamily);
		
		return font;
	}
	
	public InputStream getByteInputStream() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}
}
