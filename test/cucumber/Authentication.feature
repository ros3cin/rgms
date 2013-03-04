Feature: Authentication Process
  As a registered member in the system
  I want to have access to all of its internal features, which are only accessible after a successful login procedure

 #Issue#76 - Usuário é direcionado para tela de login repetidas vezes

Scenario:
  Given I am at the Member Listagem page
  When I select the "Principal" menu option
  Then I am redirected to the Publications Menu page

 #Duas telas diferentes de login existentes durante a navegação: uma 'oficial', acessada pelo caminho auth/login, e outra
 #encontrada no caminho raiz '/' da aplicação

Scenario:
  Given I am not logged
  When I directly access the Member List Page
  Then I am redirected to the Login Page

Scenario:
  Given I am not logged
  When I access the Root Page
  Then I am redirected to the Login Page

 #Issue#9 - Preencher com valores default campos de formulários quando relevante

Scenario:
  Given I am at the Login Page
  When I click the "Create an account" link
  Then I am at the User Register Page
  And The University field is filled with "Federal University of Pernambuco"

 #O campo 'Remember me' da tela de login não surte efeito, ao reinicializar o browser.
@ignore
Scenario:
  Given I am not logged
  And I access the Root Page
  And I am redirected to the Login Page
  When I fill my login data
  And Press the Remember me checkbox
  And The login procedure is successful
  And I am redirected to the Publications Menu page
  And I close and reopen the browser
  And I access the Root Page
  Then I am redirected to the Publications Menu page

@ignore
Scenario:
  Given I am at the Member Listagem page
  When I select the "Principal" menu option
  Then I am redirected to the Publications Menu page

#Essa issue só existiria se o UrlMappings fosse configurado para visualizar views. Esse scenario não foi implementado.
@ignore
Scenario:
  Given I am not logged
  When I directly access the Publications Menu Page
  Then I am redirected to the Login Page

