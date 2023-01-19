package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchRoute {

	WebDriver driver;
	By SearchFieldElement = By.id("searchboxinput");
	By SearchButton = By.id("searchbox-searchbutton");
	By directionsButton = By.xpath("//span[@class=\'ehYgCe\']");
	By startingPointText = By.xpath("//input[@class=\"tactile-searchbox-input\"]");
	By searchStPointButton = By.xpath("//button[@aria-label=\"Remove starting point\"]//preceding::button[@data-tooltip=\"Search\"]");
	By routeName = By.xpath("//div//h1[contains(@id,'section-directions')]//span");
	By routeTime = By.xpath("//div[contains(@aria-labelledby,'section-directions')]//div[contains(@class,'fontHeadlineSmall')]//span[1]");
	By routeDistance = By.xpath("//div[contains(@aria-labelledby,'section-directions')]//div[contains(@class,'fontBodyMedium')]//span//following-sibling::span//following-sibling::div");

	public SearchRoute(WebDriver driver){
		this.driver=driver;
	}
	
	public void searchPlace(String place) {
		driver.findElement(SearchFieldElement).sendKeys(place);
		driver.findElement(SearchFieldElement).sendKeys(Keys.ENTER);
	}
	
	public void verifyCoordinates(String coordinates) throws Exception {
		Thread.sleep(5000);
		String currentUrl = driver.getCurrentUrl();
		String actualCoordinates = currentUrl.substring(currentUrl.indexOf("/@")+2, currentUrl.indexOf(",12z"));

		if (coordinates.contains(actualCoordinates)) {
			System.out.println("coordinates are matching");
		} else {
			throw new Exception("coordinates are not matching");
		}
	}
	
	public void clickDirections() {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("arguments[0].click();", driver.findElement(directionsButton));
	}
	
	public void enterStartingPoint(String startingPoint) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(startingPointText));
		driver.findElement(startingPointText).sendKeys(startingPoint);
		driver.findElement(searchStPointButton).click();
	}

	public void verifyTwoOrMoreRoutesDisplayed() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(routeName));
		if (driver.findElements(routeName).size() >= 2) {
			System.out.println("More than 2 routes are present");
		} else {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("FailureScreenShot.png"));
			throw new Exception("More than 2 routes are not present");

		}
	}

	public void printTheRouteTitleDistanceInMilesAndTheTravelTime() throws IOException {
		List<WebElement> routeNames = driver.findElements(routeName);
		List<WebElement> routeTimes = driver.findElements(routeTime);
		List<WebElement> routeDistances = driver.findElements(routeDistance);
		FileWriter fileWriter = new FileWriter("routes.txt");
		for (int i=0;i<routeTimes.size();i++){
			String routeName = "RouteName: " + routeNames.get(i).getText() + "\n";
			String routeTime = "RouteTime: " + routeTimes.get(i).getText() + "\n";
			String routeDistance = "RouteDistance: " + routeDistances.get(i).getText() + "\n";
			fileWriter.write(routeName);
			fileWriter.write(routeTime);
			fileWriter.write(routeDistance);
		}
		fileWriter.close();

	}
}
