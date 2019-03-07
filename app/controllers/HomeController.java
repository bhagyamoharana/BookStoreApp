package controllers;

import play.mvc.*;

import views.html.*;

import views.html.customer.facebooklogin;
import views.html.customer.loginC;
import views.html.customer.customerlogin;
import views.html.customerhomepage;
import models.User;
import models.Customer;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        return ok(index.render());

    }

    public Result customerLogin(){
        return ok(customerhomepage.render());
    }

    public Result facebookLogin(){
        return ok(facebooklogin.render());
    }



 /*   public static void facebookLogin() {
        try {
            JsonObject profile = FbGraph.getObject("me"); // fetch the logged in user
            String email = profile.get("email").getAsString(); // retrieve the email
            // do useful things
            Session.current().put("username", email); // put the email into the session (for the Secure module)
        } catch (FbGraphException fbge) {
            flash.error(fbge.getMessage());
            if (fbge.getType() != null && fbge.getType().equals("OAuthException")) {
                Session.current().remove("username");
            }
        }
        facebooklogin();
    }

    public static void facebookLogout() {
        Session.current().remove("username");
        FbGraph.destroySession();
        facebooklogin();
    }
    public static void facebooklogin() {
        render();
    }
*/
}
