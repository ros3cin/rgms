package pages

import geb.Page

class LoginPage extends Page {
    static url = "auth/login"

    static at = {
        title ==~ /Login/// || title ==~ /RGMS/
    }

    static content = {
        //rememberMe {$("form").find("input#rememberMe")}
        rememberMe {$("form input#rememberMe")}
    }

    def submitForm = {$("form").signIn().click()}
    def clickLink = { textLink -> $("a", text: textLink).click() }

    def fillLoginDataOnly(String l, String p) {
        $("form").username = l
        $("form").password = p
    }

    def fillLoginData(String l, String p) {
        $("form").username = l
        $("form").password = p
        $("form").signIn().click()
    }

    def flashMessage(){
        $("div .message").text()
    }
}