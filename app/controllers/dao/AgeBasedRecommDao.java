/*package controllers.dao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.inject.Inject;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import play.db.Database;
import java.sql.Connection;
import java.util.Iterator;
import play.api.db.Databases;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import play.data.Form;
import play.data.FormFactory;
import controllers.dao.JavaJdbcConnection;
import controllers.Auth.SecuredCustomer;
import controllers.*;
import controllers.dao.AgeBasedRecommDao;
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

/*public class AgeBasedRecommDao extends Controller {



     public static void AgeRecomm(String age) {
         System.out.println("ageee in new class" + age);



    /*     System.out.println("bhagyaaaa");

         Connection connection = database.getConnection();
         try {

             String selectSQL = "select age from userprofile";

             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery(selectSQL );
             while (rs.next()) {
                 Integer ageee = rs.getInt("age");

                 System.out.println("ageee" + ageee);
             }
         }
         catch (Exception e) {

         }
    }
}
*/