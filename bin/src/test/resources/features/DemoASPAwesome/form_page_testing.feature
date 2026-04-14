Feature: Form Page Testing Controls

  @form_testing @pages @UISmoke
  Scenario Outline: Validating form controls from Pages
    Given Tester is navigating in <Page>
    Examples:
      | Page                   |
      | DEMOASPAWESOME         |
      | WIKI                   |
      | PRODINNERASPNETAWESOME |

