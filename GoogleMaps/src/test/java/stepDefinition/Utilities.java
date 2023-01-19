package stepDefinition;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utilities {

	private static WebDriver driver;
	public static void openUrl(String url) {
		
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	public static WebDriver initializeWebDriver(){
		//System.setProperty("webdriver.chrome.driver", "/Users/snehith/eclipse-workspace/GoogleMaps/chromedriver");
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver(chromeOptions);
		driver = new ChromeDriver();
		return driver;
	}
}
