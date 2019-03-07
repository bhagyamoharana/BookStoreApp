/*
package controllers;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.customer.login;

public class Application extends Controller {


    public  Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    public  class Login {

        public String email;
        public String password;

    }


    public  Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }
    public String validate() {
        if (User.authenticate(email, password) == null) {
            return "Invalid user or password";
        }
        return null;
    }

}
*/