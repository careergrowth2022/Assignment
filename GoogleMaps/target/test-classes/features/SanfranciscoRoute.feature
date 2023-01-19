
@f1
Feature: Verify route from Chico, California to San Francisco, California

#Background: Given User navigates to Google maps in the browser

  Scenario: Verify the coordinates for San Francisco in Google Maps
    Given User navigates to Google maps in the browser
    When User search for "San Francisco, California"
    Then verifies the coordinates for San Francisco "37.7576793,-122.5076409"
    And click on directions
    When User searches with "Chico, California" as Starting point
    Then verify two or more routes displayed
    And print the route title, distance in miles, and the travel time