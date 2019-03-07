package controllers;

import controllers.Auth.SecuredCustomer;
import controllers.BooksDataSetController;
import controllers.BooksController;
import controllers.CustomerController;
import models.Address;
import models.User;
import models.Quest;
import models.Customer;
import models.AnswerSelected;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.Hash;

import views.html.quiz.questionaire;
import views.html.quiz.thanks;
import views.html.quiz.quiz;
import views.html.bookdataset.portfolio;

import javax.inject.Inject;
import java.util.Map;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import java.util.List;


public class QuestionController extends Controller {

    @Inject
    FormFactory ff;
   CustomerController cs;
   private static String workingDir = System.getProperty("user.dir");
   private static final String HUMAN_MASTER_FILE = workingDir +"\\PreTestLikert\\Human\\HumanMaster.csv";

    public Result questions() {
        System.out.println("rendering form");
        return ok(questionaire.render());
    }


    public Result submitAnswers() {
        System.out.println("Inside submit button of questionaire");
        return ok(thanks.render());
    }


    @Security.Authenticated(SecuredCustomer.class)
    public Result saveAnswers(){

        String mailid = SecuredCustomer.USER().email;
        System.out.println("Email is :"+mailid);
        DynamicForm dynamicForm = ff.form().bindFromRequest();
        System.out.println("q1: " + dynamicForm.get("q1"));
        System.out.println("q2: " + dynamicForm.get("q2"));
        System.out.println("q3: " + dynamicForm.get("q3"));
        System.out.println("q4: " + dynamicForm.get("q4"));
        System.out.println("q5: " + dynamicForm.get("q5"));
        System.out.println("q6: " + dynamicForm.get("q6"));
        System.out.println("q7: " + dynamicForm.get("q7"));
        System.out.println("q8: " + dynamicForm.get("q8"));
        System.out.println("q9: " + dynamicForm.get("q9"));
        System.out.println("q10: " + dynamicForm.get("q10"));
        Map<String,String> input = dynamicForm.rawData();

       // AnswerSelected ans = input.get();
   //     String mailid = SecuredCustomer.USER().email;
        String q1=dynamicForm.get("q1");




        System.out.println("rendering questionaire form q1 is"+q1 );
        flash("success", "Saved answers Successfully.");

        return redirect(routes.QuestionController.submitAnswers());
    }


}
     /*   if(form.hasErrors()){
            flash("danger","Input validation failed.");
            return badRequest(questionaire.render());
        }

        Map<String,String> input = form.rawData();

        AnswerSelected answerSelected = form.get();



        answerSelected.save();



        flash("success","Saved answers Successfully.");

        return redirect(routes.QuestionController.submitAnswers());



        //return redirect(routes.BooksController.submitAnswers());
    }




}
*/