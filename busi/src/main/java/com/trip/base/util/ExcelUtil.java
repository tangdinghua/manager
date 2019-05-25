package com.trip.base.util;



import com.trip.base.exception.BusinessException;
import com.trip.busi.constants.ErrorCons;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Excel文件导出工具类
 *
 * @version 1.0
 */
public class ExcelUtil {
  protected static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
  private static final int PAGESIZE = 1000;
  private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String XLSX = ".xlsx";
  private static final String UTF8 = "UTF-8";
  private static final String ISO = "ISO8859-1";

  /**
   * 向浏览器客户端输出Excel文件
   */
  public static void exportExcel(String fileName, String sheetName, String[] titles,
      List<List<Object>> recordList, HttpServletResponse response) {
    exportExcel(fileName, sheetName, PAGESIZE, titles, recordList, response);
  }

  public static List<Map<String, String>> objectListToMapList(List<?> objList) {
    List<Map<String, String>> mapList = new ArrayList<>();
    for (Object obj : objList) {
      Map<String, String> map = objectToMap(obj);
      mapList.add(map);
    }
    return mapList;
  }

  public static Map<String, String> objectToMap(Object obj) {
    Map<String, String> map = new HashMap<>();
    Class<?> clazz = obj.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      String fieldName = field.getName();
      String value = null;
      try {
        value = String.valueOf(field.get(obj));
      } catch (IllegalArgumentException | IllegalAccessException e) {
        throw new RuntimeException("对象转换成Map时发生异常。", e);
      }
      map.put(fieldName, value);
    }
    return map;
  }

  public static String encode(String url, String enc) {
    try {
      return URLEncoder.encode(url, enc);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 向浏览器客户端输出Excel文件
   */
  public static void exportExcel(String fileName, String sheetName, int pageSize, String[] titles,
      List<List<Object>> recordList, HttpServletResponse response) {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFCellStyle headStyle = getHeadStyle(workBook);
    XSSFCellStyle bodyStyle = getBodyStyle(workBook);
    XSSFSheet sheet = null;
    int sheetCount = (int) Math.ceil(Double.valueOf(recordList.size()) / pageSize);
    if (sheetCount == 0) {
      sheet = workBook.createSheet(sheetName);
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.length; j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles[j]);
        }
      }
    }
    for (int i = 0; i < sheetCount; i++) {
      sheet = workBook.createSheet((i == 0) ? sheetName : (sheetName + "(" + (i + 1) + ")"));
      // 设置第一行表头的各个列标题
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.length; j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles[j]);
        }
      }
      // 设置第二行开始的动态列数据
      if (recordList != null) {
        int start = i * pageSize;
        int end = (i + 1) * pageSize;
        end = end > recordList.size() ? recordList.size() : end;
        for (int j = start, row = 1; j < end; j++, row++) {
          XSSFRow bodyRow = sheet.createRow(row);
          List<Object> record = recordList.get(j);
          for (int k = 0; k < record.size(); k++) {
            Object object = record.get(k);
            if (object == null) {
              object = "";
            }
            String value = null;
            if (object instanceof Date) {
              value = DateUtil.format((Date) object, DateUtil.TIME_FORMAT);
            } else {
              value = String.valueOf(object);
            }
            cell = bodyRow.createCell(k);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(value);
          }
        }
      }
    }
    // 输出文件并关闭输出流
    try {
      if (!fileName.toLowerCase().endsWith(XLSX) && !fileName.toLowerCase().endsWith(".xls")) {
        fileName += XLSX;
      }
      fileName = encode(fileName, UTF8);
      if (fileName.length() > 15) {
        fileName = new String(fileName.getBytes("gb2312"), ISO);
      }
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-store");
      response.setDateHeader("Expires", 0);
      response.setContentType("application/octet-stream; charset=utf-8");
      response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
      OutputStream os = response.getOutputStream();
      workBook.write(os);
      os.flush();
    } catch (Exception e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
  }

  public static void exportExcelWithMap(String fileName, String sheetName, int pageSize,
      List<String> titles, List<String> keys, List<Map<String, String>> recordList,
      HttpServletResponse response) {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFCellStyle headStyle = getHeadStyle(workBook);
    XSSFSheet sheet = null;
    int sheetCount = (int) Math.ceil(Double.valueOf(recordList.size()) / pageSize);
    if (sheetCount == 0) {
      sheet = workBook.createSheet(sheetName);
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
    }
    for (int i = 0; i < sheetCount; i++) {
      sheet = workBook.createSheet((i == 0) ? sheetName : (sheetName + "(" + (i + 1) + ")"));
      // 设置第一行表头的各个列标题
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
      // 设置第二行开始的动态列数据
      if (recordList != null) {
        int start = i * pageSize;
        int end = (i + 1) * pageSize;
        end = end > recordList.size() ? recordList.size() : end;
        for (int j = start, row = 1; j < end; j++, row++) {
          XSSFRow bodyRow = sheet.createRow(row);
          Map<String, String> record = recordList.get(j);
          for (int k = 0; k < keys.size(); k++) {
            cell = bodyRow.createCell(k);
            cell.setCellStyle(headStyle);
            String s = (record.get(keys.get(k)) == null || record.get(keys.get(k)).equals("null")) ? "" : record.get(keys.get(k));
            cell.setCellValue(s);
          }
        }
      }
    }
    // 输出文件并关闭输出流
    try {
      if (!fileName.toLowerCase().endsWith(XLSX) && !fileName.toLowerCase().endsWith(".xls")) {
        fileName += XLSX;
      }
      fileName = URLEncoder.encode(fileName, UTF8);
      if (fileName.length() > 15) {
        fileName = new String(fileName.getBytes("gb2312"), ISO);
      }
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-store");
      response.setDateHeader("Expires", 0);
      response.setContentType("application/octet-stream; charset=utf-8");
      response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
      OutputStream os = response.getOutputStream();
      workBook.write(os);
      os.flush();
    } catch (IOException e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
  }

  public static void exportExcelWithStatMap(String fileName, String sheetName, int pageSize,
                                        List<String> titles, List<String> keys, List<Map<String, String>> recordList,
                                        HttpServletResponse response) {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFCellStyle headStyle = getHeadStyle(workBook);
    XSSFSheet sheet = null;
    int sheetCount = (int) Math.ceil(Double.valueOf(recordList.size()) / pageSize);
    if (sheetCount == 0) {
      sheet = workBook.createSheet(sheetName);
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
    }
    for (int i = 0; i < sheetCount; i++) {
      sheet = workBook.createSheet((i == 0) ? sheetName : (sheetName + "(" + (i + 1) + ")"));
      // 设置第一行表头的各个列标题
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
      // 设置第二行开始的动态列数据
      if (recordList != null) {
        int start = i * pageSize;
        int end = (i + 1) * pageSize;
        end = end > recordList.size() ? recordList.size() : end;
        for (int j = start, row = 1; j < end; j++, row++) {
          XSSFRow bodyRow = sheet.createRow(row);
          Map<String, String> record = recordList.get(j);
          for (int k = 0; k < keys.size(); k++) {
            cell = bodyRow.createCell(k);
            cell.setCellStyle(headStyle);
            String s = (record.get(keys.get(k)) == null || record.get(keys.get(k)).equals("null")) ? "0.0" : record.get(keys.get(k));
            cell.setCellValue(s);
          }
        }
      }
    }
    // 输出文件并关闭输出流
    try {
      if (!fileName.toLowerCase().endsWith(XLSX) && !fileName.toLowerCase().endsWith(".xls")) {
        fileName += XLSX;
      }
      fileName = URLEncoder.encode(fileName, UTF8);
      if (fileName.length() > 15) {
        fileName = new String(fileName.getBytes("gb2312"), ISO);
      }
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-store");
      response.setDateHeader("Expires", 0);
      response.setContentType("application/octet-stream; charset=utf-8");
      response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
      OutputStream os = response.getOutputStream();
      workBook.write(os);
      os.flush();
    } catch (IOException e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
  }

  /**
   * 设置表头的单元格样式
   */
  private static XSSFCellStyle getHeadStyle(XSSFWorkbook workBook) {
    // 创建单元格样式
    XSSFCellStyle cellStyle = workBook.createCellStyle();
    // 设置单元格的背景颜色为淡蓝色
    cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    // 设置单元格左对齐
    cellStyle.setAlignment(HorizontalAlignment.LEFT);
    // 设置单元格边框为细线条
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setBorderTop(BorderStyle.THIN);
    return cellStyle;
  }

  /**
   * 设置表体的单元格样式
   */
  private static XSSFCellStyle getBodyStyle(XSSFWorkbook workBook) {
    // 创建单元格样式
    XSSFCellStyle cellStyle = workBook.createCellStyle();
    // 设置单元格左对齐
    cellStyle.setAlignment(HorizontalAlignment.LEFT);
    // 设置单元格边框为细线条
    cellStyle.setBorderLeft(BorderStyle.THIN);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setBorderRight(BorderStyle.THIN);
    cellStyle.setBorderTop(BorderStyle.THIN);
    return cellStyle;
  }

  /**
   * 读取excel内容
   *
   * @param filePath 文件路径
   * @return List<List<Object>>
   * @since [产品/模块版本](可选)
   */
  public static List<List<Object>> importExcel(String filePath) throws IOException {
    InputStream in = new FileInputStream(filePath);
    return importExcel(in);
  }

  /**
   * 导入Excel文件
   *
   * @param is 文件输入流
   * @return List<List<Object>>
   * @since [产品/模块版本](可选)
   */
  public static List<List<Object>> importExcel(InputStream is) throws IOException {
    if (!is.markSupported()) {
      is = new PushbackInputStream(is, 8);
    }
    if (POIFSFileSystem.hasPOIFSHeader(is)) {
      return importExcelHSSF(is);
    } else if (DocumentFactoryHelper.hasOOXMLHeader(is)) {
      return importExcelXSSF(is);
    }
    throw new IllegalArgumentException("excel版本无法解析");
  }

  public static List<Map<String, Object>> importExcelSecond(InputStream is) throws IOException {
    is = new PushbackInputStream(is, 8);

    if (POIFSFileSystem.hasPOIFSHeader(is)) {
      return importExcelHSSFSecond(is);
    } else {
      return importExcelXSSFSecond(is);
    }

  }

  /**
   * 读取excel内容
   *
   * @param is 文件流
   * @return List<List<Object>>
   * @since [产品/模块版本](可选)
   */
  public static List<List<Object>> importExcelXSSF(InputStream is) {
    DecimalFormat df = new DecimalFormat("0");
    List<List<Object>> recordList = new ArrayList<>();
    try (XSSFWorkbook wbs = new XSSFWorkbook(is)) {
      XSSFSheet sheet = wbs.getSheetAt(0);
      int rowNum = sheet.getLastRowNum() + 1;
      for (int j = 0; j < rowNum; j++) {
        XSSFRow row = sheet.getRow(j);
        if (null != row) {
          List<Object> rowList = new ArrayList<>();
          for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if (null != cell) {
              switch (cell.getCellTypeEnum()) {
                case NUMERIC: // 数字
                  rowList.add(df.format(cell.getNumericCellValue()));
                  break;
                case STRING: // 字符串
                  rowList.add(cell.getStringCellValue());
                  break;
                case BOOLEAN: // Boolean
                  rowList.add(cell.getBooleanCellValue());
                  break;
                case FORMULA: // 公式
                  rowList.add(cell.getCellFormula());
                  break;
                default:
                  break;
              }
            }
          }
          recordList.add(rowList);
        }
      }
    } catch (Exception e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
    return recordList;
  }

  public static List<Map<String, Object>> importExcelXSSFSecond(InputStream is) {
    List<Map<String, Object>> recordList = new ArrayList<>();
    try (XSSFWorkbook wbs = new XSSFWorkbook(is)) {
      XSSFSheet sheet = wbs.getSheetAt(0);
      int rowNum = sheet.getLastRowNum() + 1;
      XSSFRow firstRow = sheet.getRow(0);
      List<String> keyList = new ArrayList<>();
      for (int k = 0; k < firstRow.getLastCellNum(); k++) {
        Cell cell = firstRow.getCell(k);
        keyList.add(cell.getStringCellValue());
      }
      for (int j = 1; j < rowNum; j++) {
        XSSFRow row = sheet.getRow(j);
        if (null != row) {
          Map<String, Object> rowMap = new HashMap<>();
          for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if (null != cell) {
              switch (cell.getCellTypeEnum()) {
                case NUMERIC: // 数字
                  rowMap.put(keyList.get(k), cell.getNumericCellValue());
                  break;
                case STRING: // 字符串
                  rowMap.put(keyList.get(k), cell.getStringCellValue());
                  break;
                case BOOLEAN: // Boolean
                  rowMap.put(keyList.get(k), cell.getBooleanCellValue());
                  break;
                case FORMULA: // 公式
                  rowMap.put(keyList.get(k), cell.getCellFormula());
                  break;
                default:
                  break;
              }
            }
          }
          recordList.add(rowMap);
        }
      }
    } catch (Exception e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
    return recordList;
  }

  /**
   * 读取excel内容
   *
   * @param is 文件流
   * @return List<List<Object>>
   * @since [产品/模块版本](可选)
   */
  public static List<List<Object>> importExcelHSSF(InputStream is) {
    DecimalFormat df = new DecimalFormat("0");
    List<List<Object>> recordList = new ArrayList<>();
    try (HSSFWorkbook wbs = new HSSFWorkbook(is)) {
      HSSFSheet sheet = wbs.getSheetAt(0);
      int rowNum = sheet.getLastRowNum() + 1;
      for (int j = 0; j < rowNum; j++) {
        HSSFRow row = sheet.getRow(j);
        if (null != row) {
          List<Object> rowList = new ArrayList<>();
          for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if (null != cell) {
              switch (cell.getCellTypeEnum()) {
                case NUMERIC: // 数字
                  rowList.add(df.format(cell.getNumericCellValue()));
                  break;
                case STRING: // 字符串
                  rowList.add(cell.getStringCellValue());
                  break;
                case BOOLEAN: // Boolean
                  rowList.add(cell.getBooleanCellValue());
                  break;
                case FORMULA: // 公式
                  rowList.add(cell.getCellFormula());
                  break;
                default:
                  break;
              }
            }
          }
          recordList.add(rowList);
        }
      }
    } catch (Exception e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
    return recordList;
  }

  public static List<Map<String, Object>> importExcelHSSFSecond(InputStream is) {
    DecimalFormat df = new DecimalFormat("0");
    List<Map<String, Object>> recordList = new ArrayList<>();
    try (HSSFWorkbook wbs = new HSSFWorkbook(is)) {
      HSSFSheet sheet = wbs.getSheetAt(0);
      int rowNum = sheet.getLastRowNum() + 1;
      HSSFRow firstRow = sheet.getRow(0);
      List<String> keyList = new ArrayList<>();
      for (int k = 0; k < firstRow.getLastCellNum(); k++) {
        Cell cell = firstRow.getCell(k);
        keyList.add(cell.getStringCellValue());
      }
      for (int j = 1; j < rowNum; j++) {
        HSSFRow row = sheet.getRow(j);
        if (null != row) {
          Map<String, Object> rowMap = new HashMap<>();
          for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if (null != cell) {
              switch (cell.getCellTypeEnum()) {
                case NUMERIC: // 数字
                  rowMap.put(keyList.get(k), df.format(cell.getNumericCellValue()));
                  break;
                case STRING: // 字符串
                  rowMap.put(keyList.get(k), df.format(cell.getStringCellValue()));

                  break;
                case BOOLEAN: // Boolean
                  rowMap.put(keyList.get(k), df.format(cell.getBooleanCellValue()));
                  break;
                case FORMULA: // 公式
                  rowMap.put(keyList.get(k), df.format(cell.getCellFormula()));
                  break;
                default:
                  break;
              }
            }
          }
          recordList.add(rowMap);
        }
      }
    } catch (Exception e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
    return recordList;
  }

  public static void exportExcelWithObjectList(String fileName, String sheetName, int pageSize,
      String[] titles, String[] keys, List<?> recordList, HttpServletResponse response)
      throws Exception {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFCellStyle headStyle = getHeadStyle(workBook);
    XSSFSheet sheet = null;
    int sheetCount = (int) Math.ceil(Double.valueOf(recordList.size()) / pageSize);
    if (sheetCount == 0) {
      sheet = workBook.createSheet(sheetName);
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.length; j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles[j]);
        }
      }
    }
    for (int i = 0; i < sheetCount; i++) {
      sheet = workBook.createSheet((i == 0) ? sheetName : (sheetName + "(" + (i + 1) + ")"));
      // 设置第一行表头的各个列标题
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.length; j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles[j]);
        }
      }
      // 设置第二行开始的动态列数据
      if (recordList != null) {
        int start = i * pageSize;
        int end = (i + 1) * pageSize;
        end = end > recordList.size() ? recordList.size() : end;
        for (int j = start, row = 1; j < end; j++, row++) {
          XSSFRow bodyRow = sheet.createRow(row);
          Object record = recordList.get(j);
          for (int k = 0; k < keys.length; k++) {
            cell = bodyRow.createCell(k);
            cell.setCellStyle(headStyle);
            String s = BeanUtils.getProperty(record, keys[k]);
            cell.setCellValue(s);
          }
        }
      }
    }
    // 输出文件并关闭输出流
    try {
      if (!fileName.toLowerCase().endsWith(XLSX) && !fileName.toLowerCase().endsWith(".xls")) {
        fileName +=XLSX;
      }
      fileName = URLEncoder.encode(fileName, UTF8);
      if (fileName.length() > 15) {
        fileName = new String(fileName.getBytes("gb2312"), ISO);
      }
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-store");
      response.setDateHeader("Expires", 0);
      response.setContentType("application/octet-stream; charset=utf-8");
      response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
      OutputStream os = response.getOutputStream();
      workBook.write(os);
      os.flush();
    } catch (IOException e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
  }


  /**
   * 生成xlsx文件
   * @param excelFileName
   * @param excelList
   * @return
   * @throws Exception
   */
  public static File createTempExcel(String excelFileName, List<List<String>> excelList) throws Exception {
    //创建HSSFWorkbook对象(excel的文档对象)
    File file;
    try (HSSFWorkbook wb = new HSSFWorkbook()) {
      //建立新的sheet对象（excel的表单）
      HSSFSheet sheet = wb.createSheet(excelFileName);
      for (int i = 0; i < excelList.size(); i++) {
        HSSFRow row2 = sheet.createRow(i);
        //创建单元格并设置单元格内容
        List<String> col = excelList.get(i);
        for (int j = 0; j < col.size(); j++) {
          row2.createCell(j).setCellValue(col.get(j));
        }
      }
      //输出Excel文件
      file = File.createTempFile(excelFileName+DateUtil.format(new Date(),DateUtil.DATE_NOFUll_FORMAT+"-"), ".xls");
      logger.info("excel_path = {}",file.getAbsoluteFile());
      FileOutputStream outputStream = new FileOutputStream(file);
      try {
        wb.write(outputStream);// 将文档对象写入文件输出流
      } finally {
        outputStream.close();// 关闭文件输出流
      }
    }
    return file;
  }
  
  public static void createTempExcelWithMap(String fileName, File file, String sheetName, int pageSize,
      List<String> titles, List<String> keys, List<Map<String, String>> recordList
      ) {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFCellStyle headStyle = getHeadStyle(workBook);
    XSSFSheet sheet = null;
    int sheetCount = (int) Math.ceil(Double.valueOf(recordList.size()) / pageSize);
    if (sheetCount == 0) {
      sheet = workBook.createSheet(sheetName);
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
    }
    for (int i = 0; i < sheetCount; i++) {
      sheet = workBook.createSheet((i == 0) ? sheetName : (sheetName + "(" + (i + 1) + ")"));
      // 设置第一行表头的各个列标题
      XSSFRow headRow = sheet.createRow(0);
      XSSFCell cell = null;
      if (titles != null) {
        for (int j = 0; j < titles.size(); j++) {
          cell = headRow.createCell(j);
          cell.setCellStyle(headStyle);
          cell.setCellValue(titles.get(j));
        }
      }
      // 设置第二行开始的动态列数据
      if (recordList != null) {
        int start = i * pageSize;
        int end = (i + 1) * pageSize;
        end = end > recordList.size() ? recordList.size() : end;
        for (int j = start, row = 1; j < end; j++, row++) {
          XSSFRow bodyRow = sheet.createRow(row);
          Map<String, String> record = recordList.get(j);
          for (int k = 0; k < keys.size(); k++) {
            cell = bodyRow.createCell(k);
            cell.setCellStyle(headStyle);
            String s = (record.get(keys.get(k)) == null || record.get(keys.get(k)).equals("null")) ? "" : record.get(keys.get(k));
            cell.setCellValue(s);
          }
        }
      }
    }
    // 输出文件并关闭输出流
    try {
      if (!fileName.toLowerCase().endsWith(XLSX) && !fileName.toLowerCase().endsWith(".xls")) {
        fileName += XLSX;
      }
      fileName = new String(fileName.getBytes(),"UTF-8");
      File tempFile = new File(file,fileName);
      OutputStream out = new FileOutputStream(tempFile);
      workBook.write(out);
      out.flush();
      out.close();
    } catch (IOException e) {
      throw new BusinessException(ErrorCons.EXCEL_IMP_ERR.getCode(),ErrorCons.EXCEL_IMP_ERR.getMessage());
    }
  }

  private ExcelUtil() {}
}
