package controllers;

import controllers.Auth.SecuredCustomer;
import controllers.*;
import controllers.BooksDataSetController;
import controllers.BooksController;
import models.Customer;

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

public class CustomerController extends Controller {



        @Inject
        FormFactory ff;

        @Inject
        MailerClient mailerClient;

        public Result sendEmail(){

            Email email = new Email()
                    .setSubject("Simple email")
                    .setFrom("Mister FROM <noreply@tutorism.pk>")
                    .addTo("Miss TO <azeemtariq00@gmail.com>")
                    .setBodyText("A text message");

            mailerClient.send(email);

            return ok("Done");
        }


        public Result register(){
            if(SecuredCustomer.CHECK()){
                return redirect(routes.BooksDataSetController.showPage());
            }
            Form<Customer> form = ff.form(Customer.class);
            return ok(registercustomer.render(form));
        }

        public Result login(){
            if(SecuredCustomer.CHECK()){
                return redirect(routes.BooksDataSetController.showPage());
            }
            Form<Customer> form = ff.form(Customer.class);
            return ok(loginC.render(form));
        }

        public Result save(){

            Form<Customer> form = ff.form(Customer.class).bindFromRequest();

            if(form.hasErrors()){
                flash("danger","Input validation failed.");
                return badRequest(registercustomer.render(form));
            }

            Map<String,String> input = form.rawData();

            Customer customer = form.get();

            try {
                customer.password = Hash.create(customer.password);
            } catch (Exception e) {
                flash("danger",e.getMessage());
                return badRequest(registercustomer.render(form));
            }

            customer.save();



            flash("success","Registered Successfully.");

            return redirect(routes.CustomerController.login());
        }

        public Result auth(){

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
            if((customer == null) || (!Hash.check(password,customer.password))){
                flash("danger","Email or Password is invalid..");
                return redirect(routes.CustomerController.login());
            }

            session("email", email);

            return redirect(routes.QuestionController.questions());
        }

        @Security.Authenticated(SecuredCustomer.class)
        public Result logout(){
            session().clear();
            flash("success","You've been logged out");
            return redirect(routes.HomeController.customerLogin());
        }


    }