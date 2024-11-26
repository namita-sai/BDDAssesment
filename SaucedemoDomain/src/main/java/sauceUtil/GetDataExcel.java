package sauceUtil;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataExcel
{

	String path=".\\Resources\\SauceData.xlsx";
	FileInputStream file;
	DataFormatter df;
	String value="";
	XSSFWorkbook wb;

	public String getDataFromExcel(String sheetNm,int row,int cell)
	{
		try
		{
		    file=new FileInputStream(path);
			wb=new XSSFWorkbook(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		XSSFCell val = wb.getSheet(sheetNm).getRow(row).getCell(cell);
	    df=new DataFormatter();
	    value=df.formatCellValue(val);

	    return value;
	}

}
