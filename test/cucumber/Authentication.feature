Feature: Authentication Process
  As a registered member in the system
  I want to have access to all of its internal features, which are only accessible after a successful login procedure

Scenario:
  Given I am at the Member Listagem page
  When I select the "Principal" menu option
  Then I am redirected to the Publications Menu page

  @ignore  #Essa issue só existiria se o UrlMappings fosse configurado para visualizar views. Esse scenario não foi implementado.
Scenario:
  Given I am not logged
  When I access the Publications Menu Page
  Then I am redirected to the Login Page