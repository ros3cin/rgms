package pages


import geb.Page
import geb.navigator.Navigator

class MembersListPage extends Page {
    static url = "member/list"

    static at = {
        title ==~ /Member Listagem/

    }

    static content = {
    }

    def menuSelect(String option){
        //$("div.nav").find("a.${option}").click()
        $("div.nav").find("a", text: option).click()
    }

}
