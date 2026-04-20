@E2E @AutomationExercise
Feature: End to End Checkout - Automation Exercise

  @Regression @Smoke
  Scenario Outline: Complete E2E purchase flow from signup to invoice download
    Given Tester is navigating to Automation Exercise homepage
    When I click on LoginSignup link
    And I enter signup name and email with random data
    And I click Signup button
    And I fill account information
    And I fill address information
    And I click Create Account button
    Then I should see Account Created message
    And I click Continue button
    Then I should be logged in as Test User
    # Navigate to products and add to cart
    And I navigate to products section
    And I add first product to cart
    And I click Continue on modal

    # Cart and Checkout
    And I click View Cart on modal
    Then I should see products in cart
    And I proceed to checkout
    Then I should see checkout page with delivery address
    And I add comment for order
    And I place order

    # Payment
    And I enter payment details
    And I click Pay Now button

    # Order Confirmation
    Then I should see Order Confirmed message

    # Invoice
    And I download invoice
    Then I should see invoice file downloaded
    Then I should see invoice total amount
    Then I verify invoice product name matches in downloaded file
    Then I verify invoice total matches in downloaded file

    Examples:
      | Page                   | Role           |
      | PRODINNERASPNETAWESOME | ProDinnerAdmin |
