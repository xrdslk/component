/**
 * 
 */
package com.xrds.basic.test.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xrds.basic.component.common.util.ExcelReadHelper;

/**
 * @author GSZY
 *
 */
public class ExportExcelTest {

//	protected static final Logger LOGGER = LoggerFactory.getLogger(CommonTest.class);

	@Test
	public void testExportExcel() {
		try {
			String[] properties = new String[] { "customer_id", "callName",
					"callNumber", "callType", "callDate",
					"callDuration" };
			ExcelReadHelper.excelRead(
					"F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\calllog0509.xlsx", properties,
					CallDuration.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	

	@Test
	public void testJsonExportExcel() {
		try {
			File file = new File("F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\0511\\contact.json");
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				int line = 1;
				while ((tempString = reader.readLine()) != null) {
					sb.append(tempString);
					line++;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
			Gson gson = new Gson();
			List<ConactBean> ps = gson.fromJson(sb.toString(),
					new TypeToken<List<ConactBean>>() {
					}.getType());
			String[] properties = new String[] { "customer_id", "callName",
					"callNumber", "callType", "callDate",
					"callDuration" };
			List<Object>  callDurationList = ExcelReadHelper.excelRead(
					"F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\0511\\calllog.xlsx", properties,
					CallDuration.class);
			Map<String,Map<String,String>> callMap = new HashMap<>();
			for(Object o:callDurationList){
				CallDuration callDuration = (CallDuration)o;
				if(StringUtils.isBlank(callDuration.getCallNumber()))continue;
				if(callDuration.getCallNumber().startsWith("9"))callDuration.setCallNumber("0"+callDuration.getCallNumber());
				if(!callDuration.getCallNumber().startsWith("09"))continue;
				if(callDuration.getCallNumber().length()>4)
				if(callMap.containsKey(callDuration.getCustomer_id())){
					callMap.get(callDuration.getCustomer_id()).put(callDuration.getCallNumber(), callDuration.getCallName());
				}else{
					Map<String,String> map = new HashMap<>();
					map.put(callDuration.getCallNumber(), callDuration.getCallName());
					callMap.put(callDuration.getCustomer_id(), map);
				}
			}
			
			for (ConactBean cb : ps) {
				List<CList> cs = gson.fromJson(cb.getContactList().toString(),
						new TypeToken<List<CList>>() {
						}.getType());

				Map<String, String> cMap = new HashMap<>();
				for (CList cl : cs) {
					if(StringUtils.isBlank(cl.getContactPhone()))continue;
					if(cl.getContactPhone().startsWith("9"))cl.setContactPhone("0"+cl.getContactPhone());
					if(!cl.getContactPhone().startsWith("09"))continue;
					if(cl.getContactPhone().length()>4)
					cMap.put(cl.getContactPhone(), cl.getContactName());
				}
				cMap.put(cb.getColleaguePhone(), cb.getColleaguePhone());
				cMap.put(cb.getCompanyPhone(), cb.getColleaguePhone());
				cMap.put(cb.getGuarantorPhone(), cb.getColleaguePhone());
				cMap.put(cb.getRelativesMobile(), cb.getColleaguePhone());
				cMap.put(cb.getSocialPhone(), cb.getColleaguePhone());
				cb.setcList(cMap);
				cs.clear();
			}
			workboot(ps,callMap);
			System.out.println(gson.toJson(ps));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void workboot(List<ConactBean> ps,Map<String,Map<String,String>> callMap) throws Exception{
		String filePath = "F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\0511\\联系方式.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
	        FileOutputStream outputStream = new FileOutputStream(filePath);
	        
	        for(ConactBean cb:ps){
	        	XSSFSheet sheet = workbook.createSheet(cb.getLoanOrderNo()+"_"+cb.getMobile());

		        // 自定义样式：居中、蓝色、加粗
		        final XSSFFont font = workbook.createFont();
		        font.setBold(true);
		        font.setColor(IndexedColors.BLUE.getIndex());
		        XSSFCellStyle cellStyle = workbook.createCellStyle();
		        cellStyle.setFont(font);
		        cellStyle.setAlignment(HorizontalAlignment.CENTER);

		        // 设置首行
		        XSSFRow first = sheet.createRow(0);
		        XSSFCell cellF = first.createCell(0);
		        cellF.setCellValue("联系人");
		        cellF.setCellStyle(cellStyle);
		        XSSFCell cellS = first.createCell(1);
		        cellS.setCellValue("通话记录");
		        cellS.setCellStyle(cellStyle);

		        // 填充数据
	            Map<String,String> map= cb.getcList();
		        Map<String,String> cMap = callMap.get(cb.getCustomerId());
	            if(cMap==null || cMap.isEmpty()){
	            	int i=1;
	            	for (String tmp : map.keySet()) {
			            XSSFRow row = sheet.createRow(i);
			            i++;
			            XSSFCell cellP = row.createCell(0);
			            cellP.setCellValue(tmp);
			        }
	            }else{
	            	int count = map.size()>cMap.size()?map.size():cMap.size();
	            	Iterator<Map.Entry<String, String>> it1 = map.entrySet().iterator();
	            	Iterator<Map.Entry<String, String>> it2 = cMap.entrySet().iterator();
	            	for(int i=1;i<=count;i++){
	            		if(it1.hasNext() && it2.hasNext()){
	            			Map.Entry<String, String> entry1 = it1.next();
	            			Map.Entry<String, String> entry2 = it2.next();
	                        XSSFRow row = sheet.createRow(i);
					        XSSFCell cellP1 = row.createCell(0);
					        cellP1.setCellValue(entry1.getKey());
					        XSSFCell cellP2 = row.createCell(1);
					        cellP2.setCellValue(entry2.getKey());
	            		}else{
	            			if(it1.hasNext()){
	            				Map.Entry<String, String> entry = it1.next();
		                        XSSFRow row = sheet.createRow(i);
						        XSSFCell cellP = row.createCell(0);
						        cellP.setCellValue(entry.getKey());
	            			}
	            			if(it2.hasNext()){
	            				Map.Entry<String, String> entry = it2.next();
		                        XSSFRow row = sheet.createRow(i);
						        XSSFCell cellP = row.createCell(1);
						        cellP.setCellValue(entry.getKey());
	            			}
	            		}
	            	}
	            }
		        
//		        if(cMap!=null && !cMap.isEmpty()){
//		        	int j=1;
//			        for (String tmp : cMap.keySet()) {
//			            XSSFRow row = sheet.createRow(j);
//			            j++;
//			            XSSFCell cellP = row.createCell(1);
//			            cellP.setCellValue(tmp);
//			        }
//		        }
		        
		        // 设置宽度自适应
		        for (int o = 0; o < 5; o++) {
		           sheet.autoSizeColumn(o, true);
		           sheet.setColumnWidth(o, sheet.getColumnWidth(o) * 17 / 10);
		        }
	        }
	        
	        workbook.write(outputStream);
	        
		
	}
	
	
	
	
	

}
