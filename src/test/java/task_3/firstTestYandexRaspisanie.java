package task_3;

import org.junit.AfterClass;
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

public class firstTestYandexRaspisanie {

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
		WebElement element;

		element = driver.findElement(By.id("from"));
		element.clear();
		element.sendKeys("Кемерово");

		element = driver.findElement(By.id("to"));
		element.clear();
		element.sendKeys("Москва");

		element = driver.findElement(By.id("when"));
		element.clear();
		element.sendKeys("7 сентября");

		element.submit();

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


		List<WebElement> tripNames = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("SegmentTitle__title")));

		List<WebElement> tripNumbers = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("SegmentTitle__number")));

		List<WebElement> times = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("SearchSegment__duration")));

		List<WebElement> transportIcons = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("TransportIcon__icon")));


		if (tripNames.size() > 0) {
			if (tripNames.get(0).isDisplayed())
				System.out.println("Отображается название рейса");
			else
				System.out.println("   НЕ отображается название рейса");
		}


		if (times.size() > 0) {
			if (times.get(0).isDisplayed())
				System.out.println("У направления есть время в пути");
			else
				System.out.println("   НЕТ у направления длительности пути");
		}


		if (transportIcons.size() == tripNames.size())
			System.out.println("У всех рейсов есть иконка транспорта");
		else
			System.out.println("   НЕ у всех рейсов есть иконка транспорта");

		if (tripNumbers.size() == 5)
			System.out.println("Рейсов отобразилось 5");
		else
			System.out.println("   Рейсов НЕ отобразилось 5, рейсов отобразилось " + tripNumbers.size());
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}