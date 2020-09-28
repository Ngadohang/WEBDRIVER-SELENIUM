package DataProviderTestngExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
		
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;


	//tạo object 
		public static Object [][] getTableArray(String FilePath, String SheetName) throws Exception {
			Object [][] tabArray =null;
			try {
				//dùng java oi để insert file excel
				FileInputStream ExcelFile =new FileInputStream(FilePath);
				//dùng apache poi để open excel vào sheet
				 ExcelWBook = new XSSFWorkbook(ExcelFile);
				 ExcelWSheet = ExcelWBook.getSheet(SheetName);
				
				//lấy mốc để lấy dữ liệu
				
				int startRow=1;
				int startCol=1;
				
				//lây tong row của sheet
				int totalRow= ExcelWSheet.getLastRowNum();
				int totalCol=2 ;//viet fuction lay cot
				//Vòng lặp row file excel
				int r=0;
				for(int i=startRow; i<=totalRow; i++ , r++) {
					int c=0;
					for(int j=startCol; j<=totalCol;j++ , c++) {
						//hàm lấy value từng ô excel
							tabArray[r][c] = getCellData(r,c);
							System.out.println(tabArray);
					}
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("Không đọc được file");
				e.printStackTrace();
			} catch(IOException e) {
				System.out.println("Không đọc được file");
				e.printStackTrace();
			}
			
			return tabArray;
		}
		
		//Hàm lấy giá trị từng ô excel
		public static String getCellData(int row, int col) {
			String result="";
			try {
				//dinh danh ô excel
				Cell=ExcelWSheet.getRow(row).getCell(col);
				 CellType dataType = Cell.getCellType();
				if(dataType.equals(3)) {
					return result;
				}else {
					String cellData= Cell.getStringCellValue();
					result= cellData;
				}	
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw(e);
			}	
			
			return result;
		}
		
		//
		
	
}



