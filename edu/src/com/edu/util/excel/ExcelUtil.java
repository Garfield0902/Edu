package com.edu.util.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.edu.util.DateUtils;

/**
 * 数据导出成excel,response相应输出
 * 
 * @author xieyang
 * 
 */
@SuppressWarnings({"deprecation", "unchecked", "resource"})
public class ExcelUtil {
	
	public static Logger logger=Logger.getLogger(ExcelUtil.class);
	private static Workbook wb;

	private static CellStyle titleStyle; // 标题行样式
	private static Font titleFont; // 标题行字体
	private static CellStyle dateStyle; // 日期行样式
	private static Font dateFont; // 日期行字体
	private static CellStyle headStyle; // 表头行样式
	private static Font headFont; // 表头行字体
	private static CellStyle contentStyle; // 内容行样式
	private static Font contentFont; // 内容行字体
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";
	
	
	/**
	 * 解析excel并返回内容信息，以sheet作为key
	 * @param excelResourceFile
	 * @return
	 * @throws Exception
	 */
	public static List<SheetContent> getExcelInfo(File excelResourceFile) throws Exception{
		
		if(excelResourceFile==null||(!excelResourceFile.exists())){
			//判断文件是否存在
			throw new Exception("file is not exist!");
		}else{
			//文件路径
			String path=excelResourceFile.getPath();
			Workbook workbook=null;
			if(path.endsWith(EXTENSION_XLS)){
				//excel2003版本
				 workbook=new HSSFWorkbook(new FileInputStream(excelResourceFile));
			}else if(path.endsWith(EXTENSION_XLSX)){
				//excel2007版本以上
				workbook=new XSSFWorkbook(new FileInputStream(excelResourceFile));
			}
			
			if(workbook!=null){
				return parseExcel(workbook);
			}
			
		}
		return null;
	}
	
	/**
	 * 解析excel并返回内容信息，以sheet作为key
	 * @param excelResourceFile
	 * @return
	 * @throws Exception
	 */
	public static List<SheetContent> getExcelInfo(InputStream inStr,String fileName) throws Exception{
		Workbook workbook = getWorkbook(inStr,fileName);
		if(workbook!=null){
			return parseExcel(workbook);
		}
		return null;
	}
	
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf(".")+1);  
        if(EXTENSION_XLS.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(EXTENSION_XLSX.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
	
	
   /**
    * 解析Excel
    * @param workbook
    * @return
    * @throws Exception
    */
   private static List<SheetContent> parseExcel(Workbook workbook){
	 //excel解析结果存储引用
	   List<SheetContent> sheetContents=new ArrayList<SheetContent>();
	   try {
			   //获取excel中包含的sheet总数
			   int sheetNumber=workbook.getNumberOfSheets();
			   //遍历sheet
			   for (int i = 0; i < sheetNumber; i++) {
				Sheet sheet=workbook.getSheetAt(i);
				if(sheet==null){
					//sheet为空时，继续循环
					continue;
				}
				//单个sheet中行开始编号
				int firstRowNumIndex=sheet.getFirstRowNum();
				//单个sheet中行结束编号
				int lastRowNumIndex=sheet.getLastRowNum();
				//sheet内容存储对象
				SheetContent sheetContent=new SheetContent();
				sheetContent.setSheetName(sheet.getSheetName());
				//除表头行以外的存储对象
				Map<Object, List<Object>> sheetContentMap=new HashMap<Object, List<Object>>();
				
				//遍历sheet
				for (int j = firstRowNumIndex; j <=lastRowNumIndex; j++) {
					//获取行对象
					Row row=sheet.getRow(j);
					if(row==null){
						break;
					}
					List<Object> contentList=new ArrayList<Object>();
					//遍历单个行的列
					for (int m = row.getFirstCellNum(); m <=row.getLastCellNum(); m++) {
						Cell c = row.getCell(m);
						if(c!=null){
							contentList.add(getCellValue(c));
						}
					}
					sheetContentMap.put(row.getRowNum(), contentList);
				}
				//将单个sheet的内容保存到此map中
				sheetContent.setSheetContentMap(sheetContentMap);
				sheetContents.add(sheetContent);
			   }
			   
	   } catch (Exception e) {
		   logger.error("Parse Excel fail , error: "+e);
//		}finally{
//			if (workbook!=null) {
//				try {
//					//关闭流
//					workbook.close();
//				} catch (IOException e) {
//					logger.error("Close Excel WorkBook fail , error: "+e);
//				}
//			}
		}
	   return sheetContents;
   }
   
   /**
    * 获取cell中的值
    * @param cell
    * @param treatAsStr
    * @return
    */
   private static Object getCellValue(Cell cell) {
	   Object value = null;  
       DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
       SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
       DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
       int celltype = cell.getCellType();
       switch (celltype) {  
       case Cell.CELL_TYPE_STRING:  
           value = cell.getRichStringCellValue().getString();  
           break;
       case Cell.CELL_TYPE_NUMERIC:  
           if("General".equals(cell.getCellStyle().getDataFormatString())){  
               value = df.format(cell.getNumericCellValue());  
           }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
               value = sdf.format(cell.getDateCellValue());  
           }else{  
               value = df2.format(cell.getNumericCellValue());  
           }  
           break;  
       case Cell.CELL_TYPE_BOOLEAN:  
           value = cell.getBooleanCellValue();  
           break;  
       case Cell.CELL_TYPE_BLANK:  
           value = "";  
           break;  
       default:  
           break;  
       }  
       return value;  
   }
   
	/**
	 * excel文件写出
	 * @param request
	 * @param response
	 * @param fileName
	 * @param hssfWorkbook
	 */
	public static void writeExcelToResponse(HttpServletRequest request,
			HttpServletResponse response, String fileName,
			Workbook workbook) {
		try {
			response.setHeader("content-disposition", "attachment;filename="
					+ new String (fileName.getBytes(),"ISO8859-1") + ".xlsx");
			response.setContentType("Application/msexcel;charset=utf-8");
			OutputStream fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
			logger.error("Write excel to response error, "+e);
		}

	}
	
	/**
	 * excel文件打包成zip文件
	 * @param request
	 * @param response
	 * @param fileName zip文件夹名称
	 * @param hssfWorkbook
	 */
	public static void writeExcelToResponseUseZip(HttpServletRequest request,HttpServletResponse response, String fileName,
			Workbook[] workbooks) {
		try {
			int flag=1;
			//fileName = URLEncoder.encode(fileName, "UTF-8");
			String zipFileName=new String(fileName.getBytes(),"ISO8859-1") + ".zip";
			response.setContentType("application/zip");
			response.setHeader("content-disposition", "attachment;filename="+ zipFileName);
			ZipOutputStream out = new ZipOutputStream(response.getOutputStream()); 
			for (Workbook workbook : workbooks) {
				//PS:excel写入到zip时，不能直接从workbook中写入到zip
				String excelFileName=fileName+"_"+flag+".xlsx";
				File excelFile=new File(excelFileName);
				FileOutputStream fileOutputStream=new FileOutputStream(excelFileName);
				//先将excel写入到文件中，后续从文件中读一次
				workbook.write(fileOutputStream);
				fileOutputStream.flush();
				fileOutputStream.close();
				ZipEntry entry = new ZipEntry(excelFileName);
				out.putNextEntry(entry); 
				//以下是读文件 操作
				FileInputStream fileInputStream = new FileInputStream(excelFileName);  
	            byte[] buf = new byte[2048];  
	            BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);  
	            int len;  
	            while ((len = origin.read(buf,0,2048))!=-1) {  
	               out.write(buf,0,len);  
	            } 
	            excelFile.deleteOnExit();
				flag++;
			}
			//写入完成后刷新
			out.finish();
		} catch (Exception e) {
			logger.error("Write excel to response error, "+e);
		}
	}

	/**
	 * @Description: 将Map里的集合对象数据输出Excel数据流
	 * @param setInfo
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Workbook export2Excel(ExportExcelDataInfo dataInfo)
			throws IOException, IllegalArgumentException,
			IllegalAccessException {
		LinkedHashMap<SheetDesc, List<?>> map = dataInfo.getObjsMap();
		// 初始化excel
		init();
		Set<SheetDesc> sheetDescSet = map.keySet();
		Integer i=0;
		for (SheetDesc sheetDesc : sheetDescSet) {
			i++;
			// 获取显示值与过滤值之间的关系
			List<FiledDescription> filedDescriptions = sheetDesc
					.getExcelFiledDescriptions();
			// sheet Name
			String sheetName = sheetDesc.getSheetName();
			Sheet sheet = getSheets(sheetName);

			// 创建sheet中的表头
			createTableTitleRow(sheetDesc, sheet);
			// 日期行
			createTableDateRow(sheetDesc, sheet);
			// 表头
			creatTableHeadRow(sheetDesc, sheet);
			//设置宽度自适应
			antoChangeWith(sheet, sheetDesc.getExcelFiledDescriptions().size(), filedDescriptions);
			// 待写入数据
			List<?> objects = map.get(sheetDesc);
			int rowNum = 3;
			if (objects != null && objects.size() > 0) {
				for (Object object : objects) {
					if(rowNum>1000003){
						break;
					}
					Row hssfRow = sheet.createRow(rowNum);
					int cellNum = 0;
					for (FiledDescription filedDescription : filedDescriptions) {
						Cell hssfCell = hssfRow.createCell(cellNum);
						String value = "";
						if(object instanceof Map){
							Map<String,Object> mapTmp = (Map<String,Object>)object;
							value = mapTmp.get(filedDescription.getFieldName()) == null ? "" : mapTmp.get(filedDescription.getFieldName()).toString();
						}else{
							Object fo = ReflectionUtils.invokeGetterMethod(object, filedDescription.getFieldName()) == null ? "" : ReflectionUtils.invokeGetterMethod(object, filedDescription.getFieldName());
							String ftname = ReflectionUtils.getFieldClazz(object, filedDescription.getFieldName()).getName();
							//将日期类型的val 转化为：yyyy-MM-dd hh:mm:ss 格式，然后导出
							if(ftname.equals("java.util.Date")){
								if(!"".equals(fo.toString())){
									value = DateUtils.formatDatetime(((java.util.Date)fo));
								}
							}else{
								//如果为null,用空字符串代替
								value = fo.toString();
							}
						}
						hssfCell.setCellValue(value);
						cellNum++;
					}
					rowNum++;
				}
			}
			if(dataInfo.getB()!=null&&i==dataInfo.getSheetNum()){
				//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）  
		        Drawing patriarch =sheet.createDrawingPatriarch();     
		        //anchor主要用于设置图片的属性  
		        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255,Short.parseShort(dataInfo.getCol1().toString()), dataInfo.getRow1(), Short.parseShort(dataInfo.getCol2().toString()), dataInfo.getRow2());     
		        anchor.setAnchorType(3);     
		        //插入图片    
		        patriarch.createPicture(anchor, wb.addPicture(dataInfo.getB(), XSSFWorkbook.PICTURE_TYPE_JPEG)); 
			}
		}
		return wb;
	}

	private static void antoChangeWith(Sheet sheet,int columnNum, List<FiledDescription> descriptions){
		for (int i = 0; i<columnNum; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	/**
	 * 初始化workBook Excel文件，设置字体及格式
	 */
	private static void init() {
		wb = new SXSSFWorkbook(500);

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();
		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
	}

	/**
	 * @Description: 自动调整列宽
	 */
	@SuppressWarnings("unused")
	private static void adjustColumnSize(Sheet[] sheets, int sheetNum,
			String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(SheetDesc sheetDesc,
			Sheet sheet) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, sheetDesc
				.getExcelFiledDescriptions().size()-1);
		sheet.addMergedRegion(titleRange);
		Row titleRow = sheet.createRow(0);
		titleRow.setHeight((short) 600);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(sheetDesc.getTitle());
	}

	/**
	 * @Description: 创建日期行(需合并单元格)
	 */
	private static void createTableDateRow(SheetDesc sheetDesc,
			Sheet sheet) {
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, sheetDesc
				.getExcelFiledDescriptions().size()-1);
		sheet.addMergedRegion(dateRange);
		Row dateRow = sheet.createRow(1);
		dateRow.setHeight((short) 350);
		Cell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(dateStyle);
		dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date()));
	}

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(SheetDesc sheetDesc,
			Sheet sheet) {
		CreationHelper factory = sheet.getWorkbook().getCreationHelper(); 
		//4.得到一个换图的对象  
        Drawing drawing = sheet.createDrawingPatriarch();
        //5. ClientAnchor是附属在WorkSheet上的一个对象，  其固定在一个单元格的左上角和右下角.  
        ClientAnchor anchor = factory.createClientAnchor();  
		
		// 表头
		Row headRow = sheet.createRow(2);
		headRow.setHeight((short) 350);
		// 序号列
		Cell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		//snCell.setCellValue("序号");
		// 列头名称
		for (int num = 0, len = sheetDesc.getExcelFiledDescriptions().size(); num < len; num++) {
			Cell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(sheetDesc.getExcelFiledDescriptions()
					.get(num).getFieldDesc());
			if(num%2==0){
				Comment comment0 = drawing.createCellComment(anchor);
				
				RichTextString str0 = factory.createRichTextString(sheetDesc.getExcelFiledDescriptions()
						.get(num).getFieldName());  
				comment0.setString(str0);
				headCell.setCellComment(comment0);
			}
		}
	}
	
	/**
	 * @Description: 创建所有的Sheet
	 */
	private static Sheet getSheets(String sheetName) {
		return wb.createSheet(sheetName);
	}

	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	@SuppressWarnings("unused")
	private static Cell[] getCells(Row contentRow, int num) {
		Cell[] cells = new HSSFCell[num + 1];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		cells[0].setCellValue(contentRow.getRowNum() - 2);

		return cells;
	}

	/**
	 * @Description: 初始化标题行样式
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}

	/**
	 * @Description: 初始化日期行样式
	 */
	private static void initDateCellStyle() {
		dateStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
	}

	/**
	 * @Description: 初始化表头行样式
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
		headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLUE.index);
		headStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		headStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		headStyle.setRightBorderColor(IndexedColors.BLUE.index);
	}

	/**
	 * @Description: 初始化内容行样式
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLUE.index);
		contentStyle.setBottomBorderColor(IndexedColors.BLUE.index);
		contentStyle.setLeftBorderColor(IndexedColors.BLUE.index);
		contentStyle.setRightBorderColor(IndexedColors.BLUE.index);
		contentStyle.setWrapText(true); // 字段换行
	}

	/**
	 * @Description: 初始化标题行字体
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * @Description: 初始化日期行字体
	 */
	private static void initDateFont() {
		dateFont.setFontName("隶书");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * @Description: 初始化表头行字体
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLUE_GREY.index);
	}

	/**
	 * @Description: 初始化内容行字体
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLUE_GREY.index);
	}
}