package controllers;

import controllers.Auth.SecuredCustomer;
import models.Customer;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.quiz.indexQuizgivers;

import javax.inject.Inject;
import java.util.List;

@Security.Authenticated(SecuredCustomer.class)
public class QuizGiverController  extends Controller {

    @Inject
    FormFactory ff;

    public Result index(){
        List<Customer> quizgivers = Customer.find.all();
        return ok(indexQuizgivers.render(quizgivers));
    }

    public Result retriveMail(String email){

        Customer quizgiver = Customer.find.byId(email);

        if(quizgiver == null) {
            flash("danger", "Mailid Not Found, Please register your account");
            return notFound();
        }

        System.out.println("the email is :"+quizgiver);

        flash("success","Quizgiver email retrieved Successfully");

        return ok();
    }

}
