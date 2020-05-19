package Demo.DataDrivenOrangeHRM;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginTestCase {

	String data[][] = null;
	WebDriver driver;

	@DataProvider(name = "loginData")
	public String[][] loginDataProvider() throws BiffException, IOException {
		
		data= getExcellData();
		return data;
	}

	public String[][] getExcellData() throws BiffException, IOException {
		
		FileInputStream excel = new FileInputStream("E:\\ExcelsSheet\\OrangeHRMDD.xls");
		
		Workbook workbook = Workbook.getWorkbook(excel);
		
		Sheet sheet = workbook.getSheet(1);

		int rowCount = sheet.getRows();
		
		int columnCount = sheet.getColumns();

		String testData[][] = new String[rowCount - 1][columnCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j > columnCount; j++) {

				testData[i-1][j] = sheet.getCell(j, i).getContents();
			}
		}
		return testData;

	}

	@BeforeTest
	public void browserOpen() {
		System.setProperty("webdriver.chrome.driver", "./src/test/java/drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(dataProvider="loginData")
	public void LoginTest(String uName, String pword) {
		
		driver.get("https://opensource-demo.orangehrmlive.com/");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uName);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		
		WebElement login = driver.findElement(By.id("btnLogin"));
		login.click();
	}

	@AfterTest
	public void afterClose() {
		
		driver.quit();

		
	}

}
