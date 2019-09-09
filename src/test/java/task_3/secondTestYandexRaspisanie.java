package task_3;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class secondTestYandexRaspisanie {

	private static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.gecko.driver", "C:\\!\\GoogleDrive\\Java\\Browser Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://rasp.yandex.ru");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void main() {

		findId_Clear_Send("from", "Кемерово проспект Ленина");
		findId_Clear_Send("to", "Кемерово Бакинский переулок");
		findId_Clear_Send("when", "Среда");

		driver.findElement(By.xpath("/html/body/div/div/header/div[1]/div/div[4]/span/label[5]/span"))
				.click(); // Click "Автобус"

		driver.findElement(By.xpath("/html/body/div/div/header/div[1]/div/div[5]/form/button[2]/span/span"))
				.click(); // Click "Найти"

		List<WebElement> errors = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ErrorPageSearchForm__title")));


		for (int i = 0; i < errors.size(); i++)
			if(errors.get(i).getText().equals("Пункт прибытия не найден. Проверьте правильность написания или выберите другой город."))
				Assert.assertEquals(errors.get(i).getText(), "Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.");
	}

	public void findId_Clear_Send(String id, String text){
		WebElement element = driver.findElement(By.id(id));
		element.clear();
		element.sendKeys(text);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
