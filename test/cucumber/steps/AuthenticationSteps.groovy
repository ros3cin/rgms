import org.apache.shiro.SecurityUtils
import pages.ArticleCreatePage
import pages.ArticlesPage
import pages.LoginPage
import pages.RegistrationPage
import pages.MembersListPage
import pages.PublicationsPage
import rgms.publication.Periodico
import rgms.member.Member
import rgms.member.MemberController
import steps.TestDataAndOperations

import static cucumber.api.groovy.EN.*

Given (~'The system has no user with an "([^"]*)"') {String emailInvalido  ->
    Member usuario = Member.findByEmail(emailInvalido)
    assert usuario == null
}

When (~'I try to create a "([^"]*)" with an "([^"]*)"') {String novoUsuario, String emailInvalido  ->
    Member usuario = Member.findByUsername(novoUsuario)
    assert usuario == null
}

Then (~'It won\'t save the "([^"]*)" with the "([^"]*)"') {String novoUsuario, String emailInvalido ->
    Member novo = new Member(name: "novo usuario",username: novoUsuario,passwordHash: "senha",email: emailInvalido,status: "Graduate Student"
            ,university: "UFPE",enabled: false);
    assert !novo.save()

}

Given(~'I am at the Member Listagem page') { ->
    to LoginPage
    at LoginPage
    page.fillLoginData("admin", "adminadmin")
    at PublicationsPage
    page.select("Member")
    at MembersListPage
}

When (~'I select the "([^"]*)" menu option') { String option ->
    page.menuSelect(option)
}

Then (~'I am redirected to the Publications Menu page') { ->
    at PublicationsPage
}

Given(~'I am at the Login Page') { ->
    to LoginPage
}
When(~'I try to login with an user that does not exist'){ ->
    at LoginPage
    page.fillLoginData('NonExistentUser','NonExistentUserPass')
}
Then(~'A login failure message is displayed'){ ->
    at LoginPage
    assert (page.flashMessage() != null)
}

Given(~'I am at the Registration Page') {  ->
     to RegistrationPage
}
When(~'I register a user with success')  {  ->
     at RegistrationPage
     page.fillFormWithValidInfo()
}
Then(~'A message indicating the user was successfully registered is displayed at login page'){->
     at RegistrationPage
     assert (page.flashMessage() != null)
}

Given (~'I am not logged') {    ->
    assert SecurityUtils.subject?.principal == null
}

When (~'I access the Publications Menu Page') {  ->
    to LoginPage
    at LoginPage
    page.fillLoginData("admin", "adminadmin")
}

Then (~'I am redirected to the Login Page') { ->
    at LoginPage

}

Given (~'I\'m at the login page') {    ->
    to LoginPage
    assert Member.findByUsername("naoHabilitado") != null
}

When (~'I miss the password for a user that is not enabled') {  ->
    at LoginPage
    page.fillLoginData("naoHabilitado", "senhaerrada")
}

Then (~'A message of login failed is displayed') { ->
    at LoginPage
    assert page.flashMessage() != null

}


