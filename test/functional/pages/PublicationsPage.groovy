package pages

import geb.Page
import geb.navigator.Navigator

class PublicationsPage extends Page {
    static url = "auth/signIn"

    static at = {
        title ==~ /RGMS/
    }

    static content = {
    }

    /*def option(String s) {
        println("HEHEHEHEH: " + ($("a").find({ it.text()  == s}) as Navigator).text())
    }*/

    def select(String s) {
        $('div', id: 'status').find('a', text: s).click()
    }
}
