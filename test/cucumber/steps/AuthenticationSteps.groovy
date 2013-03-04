import geb.Browser
import geb.ConfigurationLoader
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
import pages.MembersListPage
import pages.PublicationsPage
import pages.RootPage
import pages.UserRegisterPage
import rgms.publication.Periodico
import steps.TestDataAndOperations

import static cucumber.api.groovy.EN.*
import geb.Page

Then (~'I am redirected to the Publications Menu page') { ->
    at PublicationsPage
}

Then (~'I am redirected to the Login Page') { ->
    at LoginPage
}

Given (~'I access the Root Page') { ->
    to RootPage
    //sleep(30000)
}

When (~'I directly access the Member List Page') { ->
    to MembersListPage
}

Given(~'I am at the Member Listagem page') { ->
    to LoginPage
    at LoginPage
    page.fillLoginData("admin", "adminadmin") != null
    at PublicationsPage
    page.select("Member")
    at MembersListPage
}

When (~'I select the "([^"]*)" menu option') { String option ->
    assert( page.menuSelect(option) != null )
}

Given(~'I am at the Login Page') { ->
    to LoginPage
    at LoginPage
}

When (~'I click the "([^"]*)" link') { linkText ->
    assert(page.clickLink(linkText) != null)
}

Then (~'I am at the User Register Page'){ ->
    at UserRegisterPage
}

Then (~'The University field is filled with "([^"]*)"') { defaultName ->
    assert( page.university.value() ==~ /${defaultName}/ )
}

Given (~'I am not logged') { ->
    page.browser.config.setAutoClearCookies(false)
    //to LoginPage
    //sleep(15000)
    //println "browsert: " + page.driver.manage().getCookieNamed("rememberMe")
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
/*
When (~'I close and reopen the browser') { ->
    ChromeDriver driverr = (ChromeDriver) page.driver
    Capabilities capabilities = driverr.getCapabilities()
    //sleep(30000)
    println "browsert: " + driverr.manage().getCookieNamed("rememberMe")
    println "browsert: " + driverr.getCommandExecutor()
    SessionId session = driverr.getSessionId()
    //sleep(30000)
    println "browsert: " + session
    //page.browser.config.autoClearCookies = false
    page.browser.config.setAutoClearCookies(false)
    Object driverConf = page.browser.config.getDriverConf()
    println "broooooo: " + ((Browser) page.browser).config.getDriverConf()

    page.browser.config.setAutoClearCookies(false)
    driverr.close()
    page.browser.config.setAutoClearCookies(false)
    //sleep(30000)

    //page.driver = new ChromeDriver()

    println "browsert: " + driverr.getSessionId()
    driverr.setSessionId(session.toString())
    println "browsert: " + driverr.getSessionId()
    driverr.startSession(capabilities)

    println "browsert: " + session.toString()
    println "browsert: " + driverr.getSessionId()
    //driverr.setSessionId(session.toString())
    println "browsert: " + driverr.getSessionId()
    driverr.startClient()
    page.browser.config.setDriverConf(driverConf)
    page.browser.config.setAutoClearCookies(false)
    sleep(15000)
} */
        /*
Given (~'I am at the Login Page') { ->
    to LoginPage
    at LoginPage
    Browser browser2 = page.browser
    WebDriver driverr = (ChromeDriver) page.driver
    println "browsert: " + driverr.windowHandle
    println "browsert: " + driverr
    //Browser browser3 = page.browser
    //browser2.driver.close()
    Capabilities capabilities = driverr.getCapabilities()
    SessionId session = driverr.getSessionId()
    browser2.close()
    //browser2.driver.get("http://www.google.com.br/")
    //println "browsert: " + page.browser
    //browser2.setDriver(driverr)
    //Browser bb = new Browser()
    driverr.setSessionId(session.toString())
    driverr.startSession(capabilities)
    driverr.startClient()
    //driverr.navigate().to("http://www.google.com.br/")


    //page.browser = bb
    //driverr.
    println "browsert: " + driverr.windowHandle
    println "browsert: " + driverr
    //browser2.setDriver(new ChromeDriver())
    //browser2.init(browser3)
    //browser2 = new Browser()
    //browser2.driver = new ChromeDriver()
    //page.setBrowser(browser2)

    //browser2.page(LoginPage)
    to LoginPage
    at LoginPage
    //Page aa;
    //aa.setb
    /*browser.withNewWindow({
        to LoginPage
        at LoginPage
    })*/
    /*browser = new Browser()
    browser.driver = new ChromeDriver()

    Browser.drive(browser){
        to LoginPage
        at LoginPage
    }//.quit() */
    /*Browser.drive(browser) {
        to LoginPage
        at LoginPage
    }.quit()*/

    //browser.to LoginPage
    //browser.at LoginPage

    /*to LoginPage
    at LoginPage
    browser.close()*/
    /*def loader = new ConfigurationLoader("a-custom-environment")
    def config = loader.conf
    browser = new Browser(config)*/
    //Browser.close()
   // WebDriver.quit()
    //close()


    //to LoginPage
    //at LoginPage
//}

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