/**
 * 
 */
package com.xrds.basic.test.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

//	@Test
//	public void testExportExcel() {
//		try {
//			String[] properties = new String[] { "loanOrderNo", "mobile",
//					"customerId", "createTime", "relativesMobile",
//					"socialPhone", "colleaguePhone", "companyPhone",
//					"guarantorPhone", "contactList" };
//			ExcelReadHelper.excelRead(
//					"F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\202005102.xls", properties,
//					ConactBean.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	@Test
	public void testJsonExportExcel() {
		try {
			File file = new File("F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\202005102.json");
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
			for (ConactBean cb : ps) {
				List<CList> cs = gson.fromJson(cb.getContactList().toString(),
						new TypeToken<List<CList>>() {
						}.getType());

				Map<String, String> cMap = new HashMap<>();
				for (CList cl : cs) {
					if(StringUtils.isBlank(cl.getContactPhone()))continue;
					if(cl.getContactPhone().startsWith("9"))cl.setContactPhone("0"+cl.getContactPhone());
					if(!cl.getContactPhone().startsWith("09"))continue;
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
			workboot(ps);
			System.out.println(gson.toJson(ps));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void workboot(List<ConactBean> ps) throws Exception{
		String filePath = "F:\\开发文档\\坤普\\海外贷超项目\\回收记录\\tmp.xlsx";
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
		        XSSFCell cell = first.createCell(0);
	            cell.setCellValue("联系人");
	            cell.setCellStyle(cellStyle);

		        // 填充数据
	            Map<String,String> map= cb.getcList();
	            int i=1;
		        for (String tmp : map.keySet()) {
		            XSSFRow row = sheet.createRow(i);
		            i++;
		            XSSFCell cellP = row.createCell(0);
		            cellP.setCellValue(tmp);
		        }
		        
		        // 设置宽度自适应
		        for (int o = 0; o < 5; o++) {
		           sheet.autoSizeColumn(o, true);
		           sheet.setColumnWidth(o, sheet.getColumnWidth(o) * 17 / 10);
		        }
	        }
	        
	        workbook.write(outputStream);
	        
		
	}

}
