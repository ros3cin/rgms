package pages

import geb.Page
/**
 * Created with IntelliJ IDEA.
 * User: RENATO
 * Date: 03/03/13
 * Time: 02:24
 * To change this template use File | Settings | File Templates.
 */
class RegistrationPage extends Page{
    static url = "auth/register"

    static at = {
        title ==~ /RGMS/
    }

    static content = {

    }

    def fillFormWithValidInfo(){
        Random numero = new Random();
        String nome = "algum"+(numero.nextInt()%100);
        String username = "algum"+(numero.nextInt()%100);
        String password = "12345"+(numero.nextInt()%100);
        String email = "asd"+(numero.nextInt()%100)+"@hotmail.com";
        String university = "Federal University of Pernambuco";
        String status = "Graduate Student";
        $("form").name = nome;
        $("form").username = username;
        $("form").password1 = password;
        $("form").password2 = password;
        $("form").email = email;
        $("form").university = university;
        $("form").status = status;
        $("input[type='submit']").click();
    }

    def flashMessage(){
        $("div .message").text()
    }
}
