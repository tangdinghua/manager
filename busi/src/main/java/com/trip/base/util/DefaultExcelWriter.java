package com.trip.base.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DefaultExcelWriter extends AbstractExcelWriter {
	
	@Override
	protected XSSFFont getTitleFont() {
		String key = "titleFont";
		if(fontMap.get(key) == null){
			XSSFFont font = this.getFont("宋体", 8);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			fontMap.put(key, font);
		}
		
		return fontMap.get(key);
	}

	@Override
	protected XSSFFont getDataFont() {
		String key = "dataFont";
		if(fontMap.get(key) == null){
			XSSFFont font = this.getFont("宋体", 10);
			fontMap.put(key, font);
		}
		
		return fontMap.get(key);
	}

	@Override
	protected float getTitleRowHeight() {
		// TODO Auto-generated method stub
		return 16.5f;
	}

	@Override
	protected float getDataRowHeight() {
		// TODO Auto-generated method stub
		return 16.5f;
	}

	@Override
	protected short getForeground() {
		// TODO Auto-generated method stub
		return IndexedColors.YELLOW.getIndex();
	}
	
	@Override
	protected void setColumnWidth(XSSFSheet sheet, int startCol,
			List<Integer> widths) {
		// TODO Auto-generated method stub
		super.setColumnWidth(sheet, startCol, widths);
//		for (int i = 0; i < widths.size(); i++) {
//			sheet.setColumnWidth(i + startCol, 256 * 10);
//		}
	}
}
