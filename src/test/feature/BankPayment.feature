Feature: MidTrans Pillow OTP Page function
  Background:
    Given I have entered my credit card details

  Scenario: I will click Pay Now, I will see the Transaction Details
    When I click the Pay Now button after entering the credit card details
    Then I will be redirected to the Bank Payment page with the Transaction Details

  Scenario: I will enter a valid OTP
    Given I am on the Bank Payment page
    When I enter a valid OTP into the Bank payment page
    Then My order will be successful

  Scenario: I will enter an invalid OTP
    Given I am on the Bank Payment page
    When I enter an invalid OTP into the Bank payment page
    Then My order will fail

  Scenario: I will cancel my transaction
    Given I am on the Bank Payment page
    When I click the Cancel button
    Then My order will fail