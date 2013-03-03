Feature: Authentication Process
  As a registered member in the system
  I want to have access to all of its internal features, which are only accessible after a successful login procedure

Scenario:
  Given I am at the Member Listagem page
  When I select the "Principal" menu option
  Then I am redirected to the Publications Menu page


  Scenario:
    Given I am at the Login Page
    When I try to login with an user that does not exist
    Then A login failure message is displayed
