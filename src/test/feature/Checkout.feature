Feature: MidTrans Pillow Checkout
  Background:
    Given Website is launched for testing

    Scenario: User clicks the Buy button and is redirected to the Checkout page
      When I click the Buy button
      Then I will go to the Checkout page

    Scenario: User can see the price (20000) of the pillow on the Checkout page
      Given I click the Buy button
      When I will go to the Checkout page
      Then The pillow is added to cart for 20000

    Scenario: User can see Customer Details on the Checkout page
      When I am on the Checkout page
      Then I can see the Customer Details

    Scenario: User can enter their information into the Customer Details Fields
      When I am on the Checkout page
      Then I can enter my details into the Customer Details fields

    Scenario: User is redirected to Order Summary when they click the Checkout button
      Given I filled out the Customer Details
      When I click the Checkout button
      Then I will go to the Order Summary Page