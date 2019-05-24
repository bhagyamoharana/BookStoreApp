/*
package controllers;

import controllers.dao.JavaJdbcConnection;
import controllers.Auth.SecuredCustomer;
import controllers.*;
import controllers.Reccomender.ItemBasedRecommend;

import controllers.BooksDataSetController;
import controllers.BooksController;
import controllers.QuestionController;
import models.Customer;
import models.LocationUser;
import io.ebean.Finder;
import io.ebean.Model;
import models.AnswerSelected;
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

import utils.Hash;
import views.html.customer.customerlogin;
import views.html.customer.loginC;
import views.html.customer.facebooklogin;
import views.html.quiz.questionaire;
import views.html.quiz.fbquestions;
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
import io.ebean.Model;
import models.Customer;
import models.Booksdata;
import models.Profile_Bean;
import models.Ratings;
import models.NewLoggersReccomendTable;
import play.db.jpa.Transactional;
import play.db.jpa.JPAApi;
import javax.persistence.*;
import java.util.concurrent.*;
import javax.inject.*;
import com.restfb.FacebookClient;
import com.restfb.*;
import play.db.*;
import java.util.Date;
import play.mvc.Http;
import play.data.DynamicForm;
import views.html.bookdataset.portfolio;
import views.html.bookdataset.livebooks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import views.html.recommendation.*;
import java.util.List;
import java.util.ArrayList;
import controllers.dao.ConnectionHelper;

//import play.libs.json;



public class FacebookLoginDataController extends Controller {

    @Inject
    FormFactory ff;

    @Inject
    ConnectionHelper connhelper;

    @Inject
    BooksDataSetController bdsc;

    Connection connection = null;

    public Result getDetails() {
        System.out.println("After logging in  facebook, directing to questions page");
        return ok(fbquestions.render());
    }

    public Result saveAnswers()
    {
        System.out.println("Inside questions from fbook ");

        connection = connhelper.database();

        DynamicForm dynamicForm = ff.form().bindFromRequest();

        Map<String,String> input = dynamicForm.rawData();

        System.out.println("Inside questions from fb ");
        Integer i1=Integer.parseInt(dynamicForm.get("fq1"));
        Integer i2=Integer.parseInt(dynamicForm.get("fq2"));
        Integer i3=Integer.parseInt(dynamicForm.get("fq3"));
        Integer i4=Integer.parseInt(dynamicForm.get("fq4"));
        Integer i5=Integer.parseInt(dynamicForm.get("fq5"));
        Integer i6=Integer.parseInt(dynamicForm.get("fq6"));
        Integer i7=Integer.parseInt(dynamicForm.get("fq7"));
        Integer i8=Integer.parseInt(dynamicForm.get("fq8"));
        Integer i9=Integer.parseInt(dynamicForm.get("fq9"));
        Integer i10=Integer.parseInt(dynamicForm.get("fq10"));

        Integer x[] = new Integer[] {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < x.length; i++)
        {
            if (x[i] == 5)
            {
                list.add(i + 1);
            }
        }

        if (list.size() == 0) {
            for (int i = 0; i < x.length; i++) {
                if (x[i] == 4) {
                    list.add(i + 1);
                }
            }
        }


        try {
            String selectSQL2 ="insert into livetable2 " +
                    "select distinct bookrating, age, country_code, title, author, coverimage, " +
                    "tags, url from livetable";
            PreparedStatement ps2 = connection.prepareStatement(selectSQL2);
            if (list.size() == 0) {
                System.out.println("=================liat size is zero");
                ps2.executeUpdate(selectSQL2);
                List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
                return ok(livebooks.render(books, "Recommendations for you"));
            }

            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));

                System.out.println("=================liat size is NOTT zero");
                String selectSQL1 ="INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags)\n" +
                        "SELECT isbn,isbn,isbn,title, author,coverimage, tags FROM booksdata\n" +
                        "WHERE Tags in (" +list.get(i)+ ");";

                PreparedStatement ps1 = connection.prepareStatement(selectSQL1);
                System.out.println("=================One" + selectSQL1);
                ps1.executeUpdate(selectSQL1);
                System.out.println("=================Two");
            }
            ps2.executeUpdate(selectSQL2);
            System.out.println("=================three" + selectSQL2);
        }
        catch (Exception e) {
            System.out.println("=============================SQL Update has failed : " + e);
        }

        //  flash("success", "Saved answers Successfully.");

        List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
        return ok(livebooks.render(books,"Recommendations for you"));

    }

}
*/