Feature: Grid Filtering Testing

  @Grid @UISmoke @Grid_Scenario_1
  Scenario Outline: Validate and Verify the Filter grid using parent controls
    Given Tester is navigating in <Page>
#    When I verify the element navigation search box in the page
    And I go to Grid > Filtering > Misc modules
    Then I verify the grid filter using parent control element
    And I filter the grid using the following :
      | field   | value        |
      | person  | Tracy        |
      | food    | Hot Beverage |
      | country | Greville     |
      | country | any country  |
#  Get Multple result in grid
    Then I validate the grid result using the following :
      | Id   | Person | Food         | Country      | Date      | Location   | Chef            |
      | 3349 | Tracy  | Hot Beverage | Jisina       | 5/11/2009 | Visit      | Demeter Harvest |
      | 3249 | Tracy  | Hot Beverage | Jisina       | 6/3/2016  | Home       | Hyperion Light  |
      | 2977 | Tracy  | Hot Beverage | La Croisette | 11/9/2021 | University | Omu Man         |

#  Get Specific result in grid
    And I filter the grid using the following :
      | field  | value        |
      | person | Jennifer     |
      | food   | French toast |
    Then I validate the grid result using the following :
      | Id   | Person   | Food         | Country | Date      | Location | Chef          |
      | 1709 | Jennifer | French toast | Jisina  | 1/5/2021  | Diner    | Peter Gibbons |
#      | 1709 | Jennifer | French toast | Jisina  | 11/1/2009 | Diner    | Peter Gibbons |
    Examples:
      | Page           |
      | DEMOASPAWESOME |





