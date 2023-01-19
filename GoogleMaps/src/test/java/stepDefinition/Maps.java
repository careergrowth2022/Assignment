package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
//import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SearchRoute;

import java.io.IOException;


public class Maps extends Utilities{

	WebDriver driver = initializeWebDriver();
	SearchRoute search = new SearchRoute(driver);
	
	@Given("User navigates to Google maps in the browser")
	public void User_navigates_to_Google_maps_in_the_browser() {
		Utilities.openUrl("https://www.google.com/maps/");
	}
	@When("User search for {string}")
	public void user_search_for(String place) {
		search.searchPlace(place);
	}

	@And("click on directions")
	public void click_on_directions() {
		search.clickDirections();
	}
	
	@When("User searches with {string} as Starting point")
	public void User_searches_with_as_Starting_point(String startingPoint) {
		search.enterStartingPoint(startingPoint);
	}


	@Then("verifies the coordinates for San Francisco {string}")
	public void verifiesTheCoordinatesForSanFrancisco(String coordinates) throws Exception {
		search.verifyCoordinates(coordinates);
	}

	@Then("verify two or more routes displayed")
	public void verifyTwoOrMoreRoutesDisplayed() throws Exception {
		search.verifyTwoOrMoreRoutesDisplayed();
	}


	@And("print the route title, distance in miles, and the travel time")
	public void printTheRouteTitleDistanceInMilesAndTheTravelTime() throws IOException {
		search.printTheRouteTitleDistanceInMilesAndTheTravelTime();
	}
}
