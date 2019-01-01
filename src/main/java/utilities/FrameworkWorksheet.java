package utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FrameworkWorksheet {

	
	public static XSSFWorkbook oWB;
	public static FrameworkWorksheet oExFrmwork = new FrameworkWorksheet();
	
	public FrameworkWorksheet(){
		
	try{
			oWB = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\resources\\TestData\\TestData.xlsx"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	};
	
	public static FrameworkWorksheet getInstance(){
		return oExFrmwork;
	}
	
	public XSSFWorkbook getWorkbook()
	{
		return oWB;
	}
	
	public void save(FileOutputStream fos)
	{
		try
		{
			oWB.write(fos);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}

