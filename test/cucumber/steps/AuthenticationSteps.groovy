import geb.Browser
import geb.ConfigurationLoader
import org.apache.commons.validator.EmailValidator
import org.apache.shiro.SecurityUtils
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.SessionId
import pages.ArticleCreatePage
import pages.ArticlesPage
import pages.LoginPage
import pages.RegistrationPage
import pages.MembersListPage
import pages.PublicationsPage
import pages.RootPage
import pages.UserRegisterPage
import rgms.publication.Periodico
import rgms.member.Member
import rgms.member.MemberController
import steps.TestDataAndOperations
import steps.TestDataAuthentication

import static cucumber.api.groovy.EN.*
import geb.Page

Then (~'I am redirected to the Publications Menu page') { ->
    at PublicationsPage
}
Then (~'I am redirected to the Login Page') { ->
    at LoginPage
}
Then (~'I am redirected to the User Register Page'){ ->
    at UserRegisterPage
}
Given (~'I access the Root Page') { ->
    to RootPage
}
When (~'I directly access the Member List Page') { ->
    to MembersListPage
}

Given(~'I am at the Login Page') { ->
    to LoginPage
    at LoginPage
}
Given(~'I am at the User Register Page') {  ->
    to UserRegisterPage
    at UserRegisterPage
}
Given(~'I am at the Member Listagem page') { ->
    to LoginPage
    at LoginPage
    page.fillLoginDataAndSubmit("admin", "adminadmin")
    at PublicationsPage
    page.getLink("Member").click()
    at MembersListPage
}

When (~'I click the "([^"]*)" link') { linkText ->
    assert( page.getLink(linkText).click() != null )
}
When (~'I select the "([^"]*)" menu option') { String option ->
    assert( page.getMenuOption(option).click() != null )
}
When (~'I submit the form') { ->
    assert( page.submitForm() != null )
}

When(~'I try to login with an user that does not exist'){ ->
    page.fillLoginDataAndSubmit('NonExistentUser','NonExistentUserPass')
}
When (~'I try to login with an existent user, though with wrong password') {->
    page.fillLoginDataAndSubmit("admin","123")
}
Then(~'A login failure message is displayed'){ ->
    assert ( page.readFlashMessage() != null )
}
Given (~'The system has no user with the "([^"]*)" email') {String email ->
    assert( Member.findByEmail(email) == null )
}


When(~'I register a user with success')  {  ->
    assert ( page.createNewUser() != null )
}
Then(~'A message indicating the user was successfully registered is displayed'){->
    assert ( page.readFlashMessage() != null )
}


Given (~'The user of "([^"]*)" username is not yet enabled') { username ->
    def user = Member.findByUsername(username)
    assert( !user?.enabled )
}
When (~'I miss the password for "([^"]*)" username') { username ->
    page.fillLoginDataAndSubmit(username, "senhaerrada")
}


When (~'I try to create a "([^"]*)" username with the "([^"]*)" email') {String novoUsuario, String emailInvalido  ->
    Member usuario = Member.findByUsername(novoUsuario)
    assert usuario == null
}
Then (~'It won\'t save the "([^"]*)" username with the "([^"]*)" email') {String novoUsuario, String emailInvalido ->
    Member novo = new Member(name: "novo usuario",username: novoUsuario,passwordHash: "senha",email: emailInvalido,status: "Graduate Student"
            ,university: "UFPE",enabled: false);
    assert !novo.save()
}


Then (~'The University field is filled with "([^"]*)"') { defaultName ->
    assert( page.university.value() ==~ /${defaultName}/ )
}


When (~'I mistype my password at the second password field') { ->
    def user = TestDataAuthentication.findByUsername("user186")
    page.password1.value(user.password)
    page.password2.value(user.password+"aa")
}
When (~'I fill my remaining user data') { ->
    def user = TestDataAuthentication.findByUsername("user186")
    page.name.value(user.name)
    page.username.value(user.username)
    page.email.value(user.email)
    page.university.value(user.university)
    page.status.value(user.status)
}
Then (~'The password fields are empty') {->
    assert(
        page.password1.value() == "" &&
        page.password2.value() == ""
    )
}
Then (~'My remaining user data is still at their corresponding fields') {->
    def user = TestDataAuthentication.findByUsername("user186")
    assert(
    page.name.value() == user.name &&
            page.username.value() == user.username &&
            page.email.value() == user.email &&
            page.university.value() == user.university &&
            page.status.value() == user.status
    )
}


Given (~'I am not logged') { ->
    page.browser.config.setAutoClearCookies(false)
    page.driver.manage().deleteAllCookies()
}




















When (~'I close and reopen the browser') { ->
    page.browser.config.setAutoClearCookies(false)
    ChromeDriver driverr = (ChromeDriver) page.driver
    SessionId session = driverr.getSessionId()
    driverr.close()
    page.browser.config.setAutoClearCookies(false)
    driverr.startSession(capabilities)
    //driverr.setSessionId(session.toString())
    driverr.startClient()
}
When (~'I fill my login data') { ->
    at LoginPage
    page.fillLoginDataOnly("admin", "adminadmin")
}

When (~'Press the Remember me checkbox'){ ->
    page.rememberMe = true
}

When (~'The login procedure is successful') { ->
    page.submitForm()
    at PublicationsPage
}

