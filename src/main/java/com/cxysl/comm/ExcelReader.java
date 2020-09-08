package com.cxysl.comm;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.util.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelReader<T>{
	private static Logger logger = LoggerFactory.getLogger(ExcelReader.class);

	Class<T> clazz = null;
	Workbook wb = null;
	Map<Integer,List<String[]>> map=new HashMap<Integer, List<String[]>>();
	public ExcelReader(){
	}
	public ExcelReader(InputStream in){
		try {
			wb = WorkbookFactory.create(in);
		}  catch (Exception e) {
			logger.error(e.toString(),e);
		}

	}
	public ExcelReader(File file){
		try {
			InputStream in=new FileInputStream(file);
			wb = WorkbookFactory.create(in);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}

	}

	//得到泛型类T
	public ExcelReader<T> setClazz(Class<T> clazz){
		this.clazz=clazz;
		return this;
	}
	/**
	 *
	 * @param filedNames 传入 对象的属性名数组
	 * @return
	 * @throws Exception
	 */
	public List<T> parseToList(String[] filedNames) throws Exception {
		return parseToList(filedNames, false,null);
	}

	//从第二行开始
	public List<T> parseToListBegin2(String[] filedNames) throws Exception {
		return parseToList(filedNames, true,null);
	}

	/**
	 * 是否忽略第index行数据
	 * @param filedNames 传入 对象的属性名数组
	 * @param map 外面传的参数
	 * @throws Exception
	 */
	public List<T> parseToList(int index,int rowIndex,String[] filedNames,Map<String,Object> map) throws Exception {
		List<String[]> datas=this.getAllData(index,rowIndex);
		List<T> list=new ArrayList<T>();
		T o=null;
		for (String[] data : datas) {
			o=clazz.newInstance();
			for (int m = 0; m < filedNames.length; m++) {
				if(StringUtils.isEmpty(filedNames[m])){
					continue;
				}
				Field field = clazz.getDeclaredField(filedNames[m]);
				String val = data[m];
				if(StringUtils.isEmpty(val)){
					continue;
				}
				Object value = null;
				if(field.getType().getTypeName().equals("java.lang.Long")){
					value = Long.valueOf(val);

				}else if(field.getType().getTypeName().equals("java.lang.Integer")){
					value = Integer.valueOf(val);

				}else if(field.getType().getTypeName().equals("java.util.Date")){
					SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
					String sDate = null;
					try
					{
						Date date=sdf1.parse(val);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						sDate=sdf.format(date);
					}
					catch (ParseException e)
					{
						logger.error("解析异常:",e);
					}
					value = com.bbt.common.util.DateUtil.parse(sDate, com.bbt.common.util.DateUtil.YYYY_MM_DD_HH_MM_SS);
//					System.out.println(value+"\n"+val+"---------------\n");

				}else if(field.getType().getTypeName().equals("java.math.BigDecimal")){
					BigDecimal bd = new BigDecimal(val);
//				value = BigDecimal.valueOf(Long.valueOf(val));
					value = bd;
				}
				else{
					value = val;
				}
				RefectUtils.setFieldValue(filedNames[m],o,value, clazz);
			}
			if(null!=map){
				for (Object key : map.keySet()) {
					RefectUtils.setFieldValue(key.toString(),o,map.get(key), clazz);
				}
			}
			list.add(o);
		}
		return list;
	}


	public List<T> parseToList(List<String[]> datas,String[] filedNames) throws Exception {
		List<T> list=new ArrayList<T>();
		T o=null;
		for (String[] data : datas) {
			o=clazz.newInstance();
			for (int m = 0; m < filedNames.length; m++) {
				if(StringUtils.isEmpty(filedNames[m])){
					continue;
				}
				Field field = clazz.getDeclaredField(filedNames[m]);
				String val = data[m];
				if(StringUtils.isEmpty(val)){
					continue;
				}
				Object value = null;
				if(field.getType().getTypeName().equals("java.lang.Long")){
					value = Long.valueOf(val);
				}else if(field.getType().getTypeName().equals("java.lang.Integer")){
					value = Integer.valueOf(val);
				}else{
					value = val;
				}
				RefectUtils.setFieldValue(filedNames[m],o,value, clazz);
			}
			list.add(o);
		}
		return list;
	}

	public List<T> parseToList(String[] filedNames,boolean flag,Map<String,Object> map) throws Exception {
		return parseToList(0, 0,filedNames, map);
	}
	/**
	 * 取Excel所有数据，包含header
	 * @return  Map<Integer,List<String[]>>
	 */
	public Map<Integer,List<String[]>> getAllDatas(){
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			map.put(i, getAllData(i,0));
		}
		return map;
	}
	/**
	 * 取Excel某一个sheet所有数据，包含header
	 * @return  List<String[]>
	 */
	public List<String[]> getAllData(int sheetIndex,int rowIndex){
		List<String[]> dataList=new ArrayList<String[]>();
		int columnNum = 0;
		Sheet sheet = wb.getSheetAt(sheetIndex);
		if(sheet.getRow(rowIndex)!=null){
			columnNum = sheet.getRow(rowIndex).getLastCellNum()-sheet.getRow(rowIndex).getFirstCellNum();
		}
		if(columnNum>0){
			for(Row row:sheet){
				if(row.getRowNum()<rowIndex){
					continue;
				}

				String[] singleRow = new String[columnNum];
				int n = 0;
				for(int i=0;i<columnNum;i++){
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch(cell.getCellType()){
						case Cell.CELL_TYPE_BLANK:
							singleRow[n] = "";
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
							break;
						//数值
						case Cell.CELL_TYPE_NUMERIC:
							if(DateUtil.isCellDateFormatted(cell)){
								singleRow[n] = String.valueOf(cell.getDateCellValue());
							}else{
								cell.setCellType(Cell.CELL_TYPE_STRING);
								String temp = cell.getStringCellValue();
								//判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
								if(temp.indexOf(".")>-1){
									singleRow[n] = String.valueOf(new Double(temp)).trim();
								}else{
									singleRow[n] = temp.trim();
								}
							}
							break;
						case Cell.CELL_TYPE_STRING:
							singleRow[n] = cell.getStringCellValue().trim();
							break;
						case Cell.CELL_TYPE_ERROR:
							singleRow[n] = "";
							break;
						case Cell.CELL_TYPE_FORMULA:
							cell.setCellType(Cell.CELL_TYPE_STRING);
							singleRow[n] = cell.getStringCellValue();
							if(singleRow[n]!=null){
								singleRow[n] = singleRow[n].replaceAll("#N/A","").trim();
							}
							break;
						default:
							singleRow[n] = "";
							break;
					}
					n++;
				}
				// if("".equals(singleRow[0])){continue;}//如果第一列为空，跳过
				dataList.add(singleRow);
			}
		}
		return dataList;
	}
	/**
	 * 返回Excel最大行index值，实际行数要加1
	 * @return
	 */
	public int getRowNum(int sheetIndex){
		Sheet sheet = wb.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
	}

	/**
	 * 返回数据的列数
	 * @return
	 */
	public int getColumnNum(int sheetIndex){
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row row = sheet.getRow(0);
		if(row!=null&&row.getLastCellNum()>0){
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * 获取某一行数据
	 * @param rowIndex 计数从0开始，rowIndex为0代表header行
	 * @return
	 */
	public String[] getRowData(int sheetIndex,int rowIndex){
		String[] dataArray = null;
		if(rowIndex>this.getColumnNum(sheetIndex)){
			return dataArray;
		}else{
			dataArray = new String[this.getColumnNum(sheetIndex)];
			return this.map.get(sheetIndex).get(rowIndex);
		}

	}

	/**
	 * 创建07 Excel
	 * @throws IOException
	 */
	public  static <T>void createExcel2007(String title,String[] headers,String[] filedNames,OutputStream os,Collection<T> dataset) throws IOException {
		// 输出流
		try {
			// 工作区
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建第一个sheet
			XSSFSheet sheet1 = wb.createSheet(title);
			// 生成第一行
			XSSFRow row1 = sheet1.createRow(0);
			//row1.setHeightInPoints((short) 30);
			// 给这一行赋值
			for (short i = 0; i < headers.length; i++) {
				row1.createCell(i).setCellValue(headers[i]);
			}
			XSSFRow row=null;
			//遍历集合数据，产生数据行
			int  index=0;
			Iterator<T> it = dataset.iterator();
			while (it.hasNext()) {
				index++;
				row = sheet1.createRow(index);
				T t = (T) it.next();
				for (int i = 0; i < filedNames.length; i++) {
					Object value=RefectUtils.getFieldValueByName(filedNames[i], t, t.getClass());
					if(null!=value){
						row.createCell(i).setCellValue(value.toString());
					}
				}
			}
			// 写文件
			wb.write(os);
			os.flush();
			//addData2007(title, fileName, filedNames, dataset);
			// 关闭输出流
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			IOUtils.close(os);
		}

	}



	public static <T> void createExcel2007(XSSFWorkbook wb, String title, String[] headers, String[] filedNames,Collection<T> dataset) {
		if(dataset == null) {
			return;
		}
		// 输出流
		// 工作区
		// 工作区
		// 创建第一个sheet
		XSSFSheet sheet1 = wb.createSheet(title);
		// 生成第一行
		XSSFRow row1 = sheet1.createRow(0);
		// row1.setHeightInPoints((short) 30);
		// 给这一行赋值
		for (short i = 0; i < headers.length; i++) {
			row1.createCell(i).setCellValue(headers[i]);
		}
		XSSFRow row = null;
		// 遍历集合数据，产生数据行
		int index = 0;
		Iterator<T> it = dataset.iterator();
		while (it.hasNext()) {
			index++;
			row = sheet1.createRow(index);
			T t = (T) it.next();
			for (int i = 0; i < filedNames.length; i++) {
				Object value = RefectUtils.getFieldValueByName(filedNames[i], t, t.getClass());
				if (null != value) {
					row.createCell(i).setCellValue(value.toString());
				}
			}
		}

	}

	/**
	 * 创建07 Excel
	 * @param fileName 路径
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public  static <T>void createExcel2007(String title,String[] headers,String[] filedNames,String fileName ,Collection<T> dataset) throws IOException {
		createExcel2007(title, headers, filedNames, new FileOutputStream(fileName), dataset);

	}
	public static<T> void addData2007(int  index,File file,String[] filedNames,Collection<T> dataset) {
		FileOutputStream os=null;
		FileInputStream is =null;
		try{
			// 输出流
			is = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			XSSFSheet sheet1 = wb.getSheetAt(index);
			//System.out.println(sheet1.getLastRowNum());
			XSSFRow row=null;
			//遍历集合数据，产生数据行
			Iterator<T> it = dataset.iterator();
			while (it.hasNext()) {
				row = sheet1.createRow(sheet1.getLastRowNum() + 1);
				T t = (T) it.next();
				for (int i = 0; i < filedNames.length; i++) {
					if(StringUtils.isEmpty(filedNames[i])){
						continue;
					}
					Object value=RefectUtils.getFieldValueByName(filedNames[i], t, t.getClass());
					if(null!=value){
						row.createCell(i).setCellValue(value.toString());
					}
				}
			}
			os = new FileOutputStream(file);
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtils.close(is);
			IOUtils.close(os);
		}
	}

	/**
	 * 给Excel追加数据测试
	 */
	public static<T> void addData2007(int index,String fileName,String[] filedNames,Collection<T> dataset) {
		addData2007(index, new File(fileName), filedNames, dataset);
	}

	public static <T> void addData2007Colums(int index, File file, String[] headers, String[] filedNames,
											 List<T> dataset) {
		FileOutputStream os;
		FileInputStream in;
		try {
			os = new FileOutputStream(file);
			in = new FileInputStream(file);
			addData2007Colums(index, in, os, headers, filedNames, dataset);
		} catch (FileNotFoundException e) {

		}

	}

	public static <T> void addData2007Colums(int index, InputStream in, OutputStream os, String[] headers,
											 String[] filedNames, List<T> dataset) {
		try {
			// 输出流
			XSSFWorkbook wb = new XSSFWorkbook(in);
			// System.out.println(sheet1.getLastRowNum());
			//XSSFRow row = null;
			int rowIndex = 0;
			int columnNum = 0;
			XSSFSheet sheet = wb.getSheetAt(index);
			if (sheet.getRow(rowIndex) != null) {
				columnNum = sheet.getRow(rowIndex).getLastCellNum() - sheet.getRow(rowIndex).getFirstCellNum();
			}
			if (columnNum > 0) {
				for (Row row : sheet) {
					if (row.getRowNum() == 0) {
						for (int i = 0; i < headers.length; i++) {
							int j = i +1;
							row.createCell(j).setCellValue(headers[i]);
						}
					}else {
						try {
							T t =  dataset.get(row.getRowNum()-1);
							for (int i = 0; i < filedNames.length; i++) {
								int j = i +1;
								Object value = RefectUtils.getFieldValueByName(filedNames[i], t, t.getClass());
								if (null != value) {
									row.createCell(j).setCellValue(value.toString());
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			wb.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(in);
			IOUtils.close(os);
		}
	}


	/**
	 * 给每一行的单元格添加样式
	 *
	 * @param cellStyle
	 *            样式
	 * @param sheet
	 *            sheet页
	 * @param row
	 *            行
	 */
	public void setCellStyle(XSSFCellStyle cellStyle, XSSFSheet sheet,
							 XSSFRow row) {
		for (short i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn((short) i);
		}
	}

	/**
	 * 获取某一列数据
	 * @param colIndex
	 * @return
	 */
	public String[] getColumnData(int sheetIndex,int colIndex){
		String[] dataArray = null;
		if(colIndex>this.getColumnNum(sheetIndex)){
			return dataArray;
		}else{
			if(map.isEmpty()) {
				getAllDatas();
			}
			List<String[]> dataList=map.get(sheetIndex);
			if(dataList!=null&&dataList.size()>0){
				dataList.remove(0);
				dataArray = new String[this.getRowNum(sheetIndex)+1];
				int index = 0;
				for(String[] rowData:dataList){
					if(rowData!=null){
						dataArray[index] = rowData[colIndex];
						index++;
					}
				}
			}
		}
		return dataArray;

	}


}