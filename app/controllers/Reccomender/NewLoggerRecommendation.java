package controllers;

import controllers.Auth.SecuredCustomer;
import controllers.*;
import controllers.BooksDataSetController;
import controllers.BooksController;
import models.Customer;
import models.LocationUser;
import io.ebean.Finder;
import io.ebean.Model;
import play.db.*;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import utils.Hash;
import views.html.customer.customerlogin;
import views.html.customer.loginC;
import views.html.quiz.questionaire;
import views.html.customer.registercustomer;
import views.html.bookdataset.portfolio;

import javax.inject.Inject;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Iterator;

import models.Booksdata;



public class NewLoggerRecommendation extends Controller {



    @Inject
    FormFactory ff;




    public Result verifyISBNwithSimilarUsers(){

        Form<Customer> form = ff.form(Customer.class).bindFromRequest();

        String email = form.rawData().get("email");
        System.out.println("Inside login method"+email);
        String password = form.rawData().get("password");
        System.out.println("Password is"+password);

        if(email == null || password == null){
            flash("danger","Email or Password is invalid.");
            return redirect(routes.CustomerController.login());
        }

        Customer customer = Customer.find.byId(email);
        System.out.println("Inside Authentication block");
        String ageof = customer.age;
        System.out.println("Age is"+ageof);
        int ager1 = (Integer.parseInt(ageof))-1;
        int ager2=(Integer.parseInt(ageof))+1;
        System.out.println("age range is"+ager1+ "and"+ager2);


        List sslist = new ArrayList();
        sslist = LocationUser.find.query().where().between( "age",ager1,ager2).findList();

        //   List<LocationUser> list = LocationUser.find.query().where().between("age", 54,56).findList();
        int itemCount = sslist.size();
        System.out.println("no of rows :"+itemCount);
        List first = new ArrayList();
        List second = new ArrayList();
        List third = new ArrayList();
        List fourth = new ArrayList();

        for(int i = 0;i<itemCount;i++) {
            //  System.out.println(+i+" sslist is " + sslist.get(i));
            if (i==0)
            {
                first.add(sslist.get(i));
                // System.out.println(" first one is" + first);
            }
            else if (i==1)
            { second.add(sslist.get(i));
                //  System.out.println("second one is " + second);
            }
            else if (i==2){
                third.add(sslist.get(i));
                // System.out.println("third one is " + third);
            }
            else {
                fourth.add(sslist.get(i));
                // System.out.println("fourth one is " + fourth);
            }
        }
        System.out.println(" first one is" + first);
        int fsize = first.size();
        System.out.println("fsize is"+fsize);
        System.out.println("second one is " + second);
        System.out.println("third one is " + third);
        System.out.println("fourth one is " + fourth);

        if((customer == null) || (!Hash.check(password,customer.password))){
            flash("danger","Email or Password is invalid..");
            return redirect(routes.CustomerController.login());
        }

        session("email", email);
        return redirect(routes.QuestionController.questions());
    }




}