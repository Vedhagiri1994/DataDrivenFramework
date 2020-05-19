   package Demo.DataDrivenOrangeHRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenUsingPOI {
	
	static List <String> userNameList = new ArrayList<String>();
	static List <String> passwordList = new ArrayList<String>();

	public void readExcel() throws IOException {

		FileInputStream excel = new FileInputStream("E:/ExcelsSheet/OrangeHRMDD1.xlsx");
		Workbook workbook = new XSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(1);

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
		Row rowValue = rowIterator.next();

		rowValue.iterator();

		Iterator<Cell> columnIterator = rowValue.iterator();
		int i=2;
		while (columnIterator.hasNext()) {
			if(i%2==0){
				userNameList.add(columnIterator.next().getStringCellValue());
			}else{
				passwordList.add(columnIterator.next().getStringCellValue());
			}
			i++;
			}
		}
	}
	
	public void login(String uName, String pWord){
		System.setProperty("webdriver.chrome.driver", "./src/test/java/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uName);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pWord);
		
		WebElement login = driver.findElement(By.id("btnLogin"));
		login.click();
		
		driver.quit();
	}
	
	public void executeTest(){
		
		for(int i=0;i<userNameList.size();i++){
			
			login(userNameList.get(i),passwordList.get(i));
		}
	}

	public static void main(String[] args) throws IOException {
		
		DataDrivenUsingPOI usingPOI = new DataDrivenUsingPOI();
		usingPOI.readExcel();
		System.out.println("UserName List" + userNameList);
		System.out.println("Password List" + passwordList);
		usingPOI.executeTest();

	}

}
