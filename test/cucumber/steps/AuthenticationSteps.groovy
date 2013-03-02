import pages.ArticleCreatePage
import pages.ArticlesPage
import pages.LoginPage
import pages.MembersListPage
import pages.PublicationsPage
import rgms.publication.Periodico
import steps.TestDataAndOperations

import static cucumber.api.groovy.EN.*

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