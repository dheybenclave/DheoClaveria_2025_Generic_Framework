Feature: Grid Filtering Testing

  @Grid @UISmoke @Grid_Scenario_1
  Scenario Outline: Validate and Verify the Filter grid using parent controls
    Given Tester is navigating in <Page>
    And I go to Grid > Filtering > Misc modules
    Then I verify the grid filter using parent control element
    And I filter the grid using the following :
      | field   | value       |
      | person  | Tracy       |
      | food    | Banana      |
      | country | Greville    |
      | country | any country |
#  Get Multple result in grid
    Then I validate the grid result using the following :
      | Id   | Person | Food   | Country      | Date      | Location | Chef         |
      | 3509 | Tracy  | Banana | Loch Modan   |  7/7/2022 | Diner    | Casse Croute |
      | 3297 | Tracy  | Banana | Orgrimmar     | 7/17/2012 | Visit    | Omu Man      |
      | 2893 | Tracy  | Banana | Winterspring |  6/9/2017 | Home    | Bruce Nolan  |
#  Get Specific result in grid
    And I filter the grid using the following :
      | field  | value    |
      | person | Jennifer |
      | food   | Pizza    |
    Then I validate the grid result using the following :
      | Id   | Person   | Food  | Country | Date     | Location   | Chef            |
      | 2037 | Jennifer | Pizza | Carpana | 7/3/2020 | University | Demeter Harvest |

    Examples:
      | Page           |
      | DEMOASPAWESOME |

  @Grid @UISmoke @Grid_Scenario_2
  Scenario Outline: Validate and Verify the Filter grid using parent controls with JSON data
    Given Tester is navigating in <Page>
    And I go to Grid > Filtering > Misc modules
    Then I verify the grid filter using parent control element
    And I filter the grid using the following :
      | field  | value  |
      | person | Tracy  |
      | person | Tracy  |
      | food   | Banana |
#  Get Multple result in grid
    Then I validate the grid result :
      | field  | value  |
      | person | Tracy  |
      | person | Tracy  |
      | food   | Banana |
#  Get Specific result in grid
    And I filter the grid using the following :
      | field  | value    |
      | person | Jennifer |
      | food   | Pizza    |
    Then I validate the grid result :
      | field  | value    |
      | person | Jennifer |
      | food   | Pizza    |

    Examples:
      | Page           |
      | DEMOASPAWESOME |
