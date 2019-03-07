package controllers.Auth;

import controllers.routes;
import models.User;
import models.Customer;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class SecuredCustomer extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.CustomerController.login());
    }


    public static String GET_USER(){
        Http.Context ctx = Http.Context.current();
        return ctx.session().get("email");
    }


    public static boolean CHECK(){
        return (GET_USER() != null);
    }

    public static Customer USER(){
        return CHECK() ? Customer.find.byId(GET_USER()):null;
    }





}