Feature: MidTrans Pillow Order Summary Page
  Background:
    Given I have completed the Checkout Process

    Scenario: User can see the Product Details on the Order Summary page
      When I am on the Order Summary page
      Then I will see the Product Details

    Scenario: User can see all the Payment Methods on the Order Summary page
      When I am on the Order Summary page
      Then I will see all of the payment methods

    Scenario: User is able to apply the Promotion to the price
      When I am on the Order Summary page
      And I click the Promo link
      Then The promotion will be applied to the price

    Scenario: User clicks the Credit Card button and is redirected to the Credit Card Details page
      When I am on the Order Summary page
      And I click the Credit Card button
      Then I will be redirected to the Credit Card Details page

    Scenario: User enters Credit Card details into the fields
      When I am on the Credit Card Details page
      Then I can enter credit card details into the fields