package pages

import geb.Page
import geb.navigator.Navigator

class UserRegisterPage extends Page {
    static url = "/auth/register"

    static at = {
        title ==~ /RGMS/ && university != null && registerButton != null
    }

    static content = {
        university {$("form input", name: "university")}
        registerButton {$("form input", value: "Register")}
    }

}
