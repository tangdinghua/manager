package com.trip.commons.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtils {

	public static final String POINT = ".";
	public static final String EMPTY = "";
	public static int totalRows; // sheet中总行数
	public static int totalCells; // 每一行总单元格数

	/**
	 * 单元格格式
	 * 
	 * @param xssfCell
	 * @return
	 */
	public static String getXValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String cellValue = "";
			if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
				// 用于转化为日期格式
				Date d = xssfCell.getDateCellValue();
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				cellValue = formater.format(d);
			} else {
				DecimalFormat df = new DecimalFormat("#.####");
				cellValue = df.format(xssfCell.getNumericCellValue());
				String strArr = cellValue.substring(cellValue.lastIndexOf(POINT) + 1, cellValue.length());
				if (strArr.equals("0000")) {
					cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
				}
			}
			return cellValue;
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	public static List<ArrayList<String>> readXlsx(MultipartFile file) {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		// IO流读取文件
		InputStream input = null;
		XSSFWorkbook wb = null;
		ArrayList<String> rowList = null;
		try {
			input = file.getInputStream();
			// 创建文档
			wb = new XSSFWorkbook(input);
			// 读取sheet(页)
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				totalRows = xssfSheet.getLastRowNum();
				// 读取Row,从第二行开始
				for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						rowList = new ArrayList<String>();
						totalCells = xssfRow.getLastCellNum();
						// 读取列，从第一列开始
						for (int c = 0; c <= totalCells + 1; c++) {
							XSSFCell cell = xssfRow.getCell(c);
							if (cell == null) {
								rowList.add(EMPTY);
								continue;
							}
							rowList.add(getXValue(cell).trim());
						}
						list.add(rowList);
					}
				}
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public static List<ArrayList<String>> readFile(File file) {
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		// IO流读取文件
		InputStream input = null;
		XSSFWorkbook wb = null;
		ArrayList<String> rowList = null;
		try {
			input = new FileInputStream(file);
			// 创建文档
			wb = new XSSFWorkbook(input);
			// 读取sheet(页)
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
				if (xssfSheet == null) {
					continue;
				}
				totalRows = xssfSheet.getLastRowNum();
				// 读取Row,从第二行开始
				for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						rowList = new ArrayList<String>();
						totalCells = xssfRow.getLastCellNum();
						// 读取列，从第一列开始
						for (int c = 0; c <= totalCells + 1; c++) {
							XSSFCell cell = xssfRow.getCell(c);
							if (cell == null) {
								rowList.add(EMPTY);
								continue;
							}
							rowList.add(getXValue(cell).trim());
						}
						list.add(rowList);
					}
				}
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
