package nhathan.com.ExcelProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelTweet {
	
	private int rowNum;
	private FileOutputStream fileOut;
	private Workbook workbook;
	private Sheet sh;

	public WriteExcelTweet() {
		super();
		this.rowNum = 1;
		this.workbook = new XSSFWorkbook();
		this.sh = this.workbook.createSheet("Tweet_mining");
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public void increaseRowNum() {
		this.rowNum += 1;
	}
	
	public void writeToExcel(Tweet data, String fileName) {
		try {
//			//Create work book in .xlsx format
//			Workbook workbook = new XSSFWorkbook();
//			//Create Sheet
//			Sheet sh = workbook.createSheet("Tweet_mining");
//			Create top row with column heading
			String[] columnHeadings = {"Content", "Favorite", "Retweet", "Place", "User Location", "Hashtags"};
			//Style font header
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short)12);
			headerFont.setColor(IndexedColors.BLACK.index);
			//Style cell header
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			//Create header row
			Row headerRow = sh.createRow(0);
			//Iterate over column heading to create column
			for(int i=0;i<columnHeadings.length;i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeadings[i]);
				cell.setCellStyle(headerStyle);
			}
			//Freeze Header Row
			sh.createFreezePane(0, 1);
			//Fill data
			Tweet tweet_data = data;
			CreationHelper creationHelper= workbook.getCreationHelper();
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yyyy"));
			//Import data to Excel
			Row row = sh.createRow(this.rowNum);
			row.createCell(0).setCellValue(tweet_data.getText());
			row.createCell(1).setCellValue(tweet_data.getFavoriteCount());
			row.createCell(2).setCellValue(tweet_data.getRetweetCount());
			row.createCell(3).setCellValue(tweet_data.getPlace());
			row.createCell(4).setCellValue(tweet_data.getUserLocation());
			row.createCell(5).setCellValue(tweet_data.getHashtag().toString());
//			//Autosize columns
//			for(int i=0;i<columnHeadings.length;i++) {
//				sh.autoSizeColumn(i);
//			}
//			Sheet sh2 = workbook.createSheet("Second");
			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
		//	workbook.close();
			System.out.println("Completed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
