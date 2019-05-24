package controllers;

import controllers.dao.JavaJdbcConnection;
import controllers.Auth.SecuredCustomer;
import controllers.*;
import controllers.Reccomender.ItemBasedRecommend;
import controllers.Reccomender.UserBasedRecommendations;

import controllers.BooksDataSetController;
import controllers.BooksController;
import models.Customer;
import models.LocationUser;
import io.ebean.Finder;
import io.ebean.Model;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import controllers.dao.ConnectionHelper;

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
import play.db.Database;
import java.sql.Connection;
import java.util.Iterator;

import models.Booksdata;
import models.Ratings;
import models.NewLoggersReccomendTable;
import play.db.jpa.Transactional;
import play.db.jpa.JPAApi;
import javax.persistence.*;
import java.util.concurrent.*;
import javax.inject.*;

import play.db.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



public class CustomerController extends Controller {

    @PersistenceContext
    private EntityManager em;

  //@Inject
  //private JavaJdbcConnection jjc = new JavaJdbcConnection(Database db, DatabaseExecutionContext executionContext);
  //@Inject
  //AgeBasedRecommDao abrd;

    @Inject
    private ItemBasedRecommend ibr;

    @Inject
    private UserBasedRecommendations ubr;

  //  EntityManger em ;

        @Inject
        FormFactory ff;

        @Inject
        MailerClient mailerClient;

    @Inject
    ConnectionHelper connhelper;

    Connection connection = null;
    public String email;
    public String password;
    public Customer customer;
    public String ageof;
    public String country;
    public Integer ager1;
    public Integer ager2;

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

            flash("success","User Registered Successfully.");

            return redirect(routes.CustomerController.login());
        }

        public Result auth() {

            Form<Customer> form = ff.form(Customer.class).bindFromRequest();

            email = form.rawData().get("email");

            password = form.rawData().get("password");

            customer = Customer.find.byId(email);

            if (email == null || password == null) {
                flash("danger", "Email or Password is invalid.");
                return redirect(routes.CustomerController.login());
            }
            else if((customer == null) || (!Hash.check(password,customer.password))){
                flash("danger","Email or Password is invalid..");
                return redirect(routes.CustomerController.login());
            }

            System.out.println("Calling Fetch User Profile");
            UserProfileFetch();

            System.out.println("Calling Age and Country Based Recommendation");
            AgeBasedRecommend(ager1, ager2, country);

            System.out.println("Calling Item Based Recommendation");
            ibr.doItemBasedRecomendations(country, ager1, ager2);

            System.out.println("Calling User Based Recommendation");
            ubr.doUserBasedRecomendations(country, ager1, ager2);

            session("email", email);
            System.out.println("Calling Likert Based Recommendation");
            return redirect(routes.QuestionController.questions());
        }

        public void UserProfileFetch (){

        System.out.println("Inside UserProfileFetch");
        ageof = customer.age;
        country = customer.country;
        System.out.println("Country is : "+country);
        System.out.println("Age is : "+ageof);
        ager1 = Integer.parseInt(ageof)-2;
        ager2 = Integer.parseInt(ageof)+2;
        System.out.println("Age range is : "+ager1+" & "+ager2);
    }

        public void AgeBasedRecommend (Integer age1, Integer age2, String country1) {


         Integer ager1 = age1;
         Integer ager2 = age2;
         String country = country1;

            connection = connhelper.database();

            try {
               // String selectSQL0 ="truncate table livetable";
                String selectSQL0 ="call truncate_livetable()";
               // String selectSQL1 ="truncate table livetable2";
                String selectSQL1 ="call truncate_livetable2()";
             /*   String selectSQL2 ="INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags)\n" +
                        "SELECT bookrating,age,country_code,title, author,coverimage, tags \n" +
                        " FROM ratings as a,\n" +
                        " userprofile as b,\n" +
                        " booksdata as c \n" +
                        " WHERE a.user_id = b.user_id\n" +
                        " AND b.age between " + ager1 + " and " + ager2 + " AND a.bookrating in (4,5) AND a.isbn = c.isbn;";
*/
                String selectSQL2 ="INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags, url)\n" +
                        "SELECT bookrating,age,country_code,title, author,coverimage, tags, url \n" +
                        " FROM ratings as a,\n" +
                        " userprofile as b,\n" +
                        " booksdata as c \n" +
                        " WHERE a.user_id = b.user_id\n" +
                        " AND b.age between " + ager1 + " and " + ager2 + "" +
                        " AND b.country_code = '" + country + "' " +
                        " AND a.bookrating in (4,5) AND a.isbn = c.isbn;";

                System.out.println("222 : " + selectSQL2);
                PreparedStatement ps0 = connection.prepareStatement(selectSQL0);
                PreparedStatement ps1 = connection.prepareStatement(selectSQL1);
                PreparedStatement ps2 = connection.prepareStatement(selectSQL2);
                ps0.executeUpdate(selectSQL0);
                ps1.executeUpdate(selectSQL1);
                ps2.executeUpdate(selectSQL2);



            }
            catch (Exception e) {
                System.out.println("Exception in Age Based Recommendation " + e);
            }
            finally {
                try { connection.close(); } catch (Exception e) { /* ignored */ }
            }

        }


        @Security.Authenticated(SecuredCustomer.class)
        public Result logout(){
            session().clear();
            flash("success","You've been logged out");
            return redirect(routes.HomeController.customerLogin());
        }

    }