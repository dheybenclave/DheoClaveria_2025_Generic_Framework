Feature: Grid Filtering Testing

  @Grid @UISmoke @Grid_Scenario_1
  Scenario Outline: Validate and Verify the Filter grid using parent controls
    Given Tester is navigating in <Page>
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
      | Id   | Person | Food         | Country       | Date      | Location   | Chef              |
      | 2085 | Tracy  | Hot Beverage | Sylvanaar     | 1/25/2018 | Diner      | Chronos Timpus    |
      | 1609 | Tracy  | Hot Beverage | Elwynn Forest | 9/7/2011  | Visit      | Pepper Tomato     |
      | 1221 | Tracy  | Hot Beverage | Orgrimmar     | 8/1/2016  | University | Cheyenne Goldblum |

#  Get Specific result in grid
    And I filter the grid using the following :
      | field  | value     |
      | person | Jennifer  |
      | food   | Apple Pie |
    Then I validate the grid result using the following :
      | Id   | Person   | Food      | Country | Date      | Location   | Chef    |
      | 1837 | Jennifer | Apple Pie | Feralas | 6/27/2011 | University | Omu Man |
    Examples:
      | Page           |
      | DEMOASPAWESOME |


  @Grid @UISmoke @Grid_Scenario_2
  Scenario Outline: Validate and Verify the Filter grid using parent controls with JSON data
    Given Tester is navigating in <Page>
    And I go to Grid > Filtering > Misc modules
    Then I verify the grid filter using parent control element

    And I filter the grid using the following :
      | field   | value        |
      | person  | Tracy        |
      | food    | Hot Beverage |
      | country | any country  |
#  Get Multple result in grid
    Then I validate the grid result :
      | field   | value        |
      | person  | Tracy        |
      | food    | Hot Beverage |
      | country | any country  |

#  Get Specific result in grid
    And I filter the grid using the following :
      | field  | value     |
      | person | Jennifer  |
      | food   | Apple Pie |
    Then I validate the grid result :
      | field  | value     |
      | person | Jennifer  |
      | food   | Apple Pie |

    Examples:
      | Page           |
      | DEMOASPAWESOME |





