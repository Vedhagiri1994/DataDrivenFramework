package loginTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {

	String[][] data = { { "Admin", "admin123" }, { "Admin", "admin" }, { "admin", "admin123" },
			{ "admin", "Admin123" } };

	@DataProvider(name = "loginData")
	public String[][] loginDataProvider() {

		return data;
	}

	@Test(dataProvider = "loginData")
	public void loginWithBothCorrect(String uName, String pWord) {
		System.setProperty("webdriver.chrome.driver", "./src/test/java/drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/symfony/web/index.php/auth/login");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uName);

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pWord);

		WebElement login = driver.findElement(By.id("btnLogin"));
		login.click();

		driver.quit();
	}

}
