Feature: Create Meal from Page

  @UISmoke @CRUD
  Scenario Outline: Create Meal from Pro Dinner Page
    Given ProDinnerAdmin is login in <Page> using <Role>
    Then validate and verify a meal created using the following :
      | Name            | Comments           |
      | TestSerenity    | Dhy_testing        |
      | TestingCucumber | Testing_Automation |

    Examples:
      | Page                   | Role           |
      | PRODINNERASPNETAWESOME | ProDinnerAdmin |
