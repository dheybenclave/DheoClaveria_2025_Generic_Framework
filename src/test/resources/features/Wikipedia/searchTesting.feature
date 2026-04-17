Feature: Wikipedia Search Testing

  @Wikipedia @UISmoke @Search
  Scenario: Search for a term on Wikipedia
    Given Tester is navigating in wikipedia
    When I search for "Selenium (software)"
    Then I verify the search results are displayed

  @Wikipedia @Regression @Search
  Scenario Outline: Search multiple terms on Wikipedia
    Given Tester is navigating in wikipedia
    When I search for "<search_term>"
    Then I verify the search results are displayed

    Examples:
      | search_term         |
      | Automation testing |
      | Selenium WebDriver  |
      | Cucumber software   |