package controllers;

import controllers.Auth.SecuredCustomer;
import controllers.BooksDataSetController;
import controllers.BooksController;
import controllers.CustomerController;
import controllers.Reccomender.ItemBasedRecommend;
import controllers.Reccomender.UserBasedRecommendations;
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
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import play.db.*;
import com.google.common.collect.ImmutableMap;
import models.NewLoggersReccomendTable;
import views.html.quiz.questionaire;
import views.html.quiz.facebook;
import views.html.quiz.thanks;
import views.html.quiz.quiz;
import views.html.bookdataset.portfolio;
import views.html.bookdataset.livebooks;
import views.html.bookdataset.fblivebooks;

import javax.inject.Inject;
import java.util.Map;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import controllers.dao.ConnectionHelper;

public class QuestionController extends Controller {


    @Inject
    BooksDataSetController bdsc;

    @Inject
    FormFactory ff;

    @Inject
    ConnectionHelper connhelper;

    @Inject
    private ItemBasedRecommend ibr;

    @Inject
    private UserBasedRecommendations ubr;

    Connection connection = null;

   @Inject
   CustomerController cs;

   private static String workingDir = System.getProperty("user.dir");
   private static final String HUMAN_MASTER_FILE = workingDir +"\\PreTestLikert\\Human\\HumanMaster.csv";

    public Result questions() {
        System.out.println("rendering form......");
        return ok(questionaire.render());
    }

    public Result fbquestions() {

            Integer ager1 = 28;
            Integer ager2 = 32;
            String country = "Ireland";

        System.out.println("Calling Age and Country Based Recommendation for Facebook user");
        cs.AgeBasedRecommend(ager1, ager2, country);

        System.out.println("Calling Item Based Recommendation for Facebook user");
        ibr.doItemBasedRecomendations(country, ager1, ager2);

        System.out.println("Calling User Based Recommendation for Facebook user");
        ubr.doUserBasedRecomendations(country, ager1, ager2);

       //     connection = connhelper.database();

         /*   try {
                String selectSQL0 ="truncate table livetable";
                String selectSQL1 ="truncate table livetable2";

                String selectSQL2 ="INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags)\n" +
                        "SELECT bookrating,age,country_code,title, author,coverimage, tags \n" +
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

                connection.close();

            }
            catch (Exception e) {
                System.out.println("Exception in Age Based Recommendation " + e);
            }*/

        System.out.println("rendering form facebook......");
        return ok(facebook.render());
    }


    public void submitAnswers() {
        System.out.println("Inside submit button of questionaire");
        bdsc.showLivetable();
    }


  //  @Security.Authenticated(SecuredCustomer.class)
    public Result saveAnswers(){
        String mailid = null;

        connection = connhelper.database();

      try {
          mailid = SecuredCustomer.USER().email;
      }
      catch (Exception e){
          System.out.println("mail id exception : " + e);
      }


      System.out.println("BHAGYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
        DynamicForm dynamicForm = ff.form().bindFromRequest();

        Map<String,String> input = dynamicForm.rawData();


        Integer i1=Integer.parseInt(dynamicForm.get("q1"));
        Integer i2=Integer.parseInt(dynamicForm.get("q2"));
        Integer i3=Integer.parseInt(dynamicForm.get("q3"));
        Integer i4=Integer.parseInt(dynamicForm.get("q4"));
        Integer i5=Integer.parseInt(dynamicForm.get("q5"));
        Integer i6=Integer.parseInt(dynamicForm.get("q6"));
        Integer i7=Integer.parseInt(dynamicForm.get("q7"));
        Integer i8=Integer.parseInt(dynamicForm.get("q8"));
        Integer i9=Integer.parseInt(dynamicForm.get("q9"));
        Integer i10=Integer.parseInt(dynamicForm.get("q10"));

        Integer x[] = new Integer[] {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};

      List<Integer> list = new ArrayList<>();

        for (int i = 0; i < x.length; i++)
        {
            if (x[i] == 5)
            {
                list.add(i + 1);
            }
            else if (x[i] == 4) {
                list.add(i + 1);
            }
        }

       /* if (list.size() == 0) {
            for (int i = 0; i < x.length; i++) {
                if (x[i] == 4) {
                    list.add(i + 1);
                }
            }
        }*/


      try {
          String selectSQL2 ="insert into livetable2 " +
                  "select distinct bookrating, age, country_code, title, author, coverimage, " +
                  "tags, url from livetable";
          PreparedStatement ps2 = connection.prepareStatement(selectSQL2);
          if (list.size() == 0) {
              System.out.println("Likert SQL2:" + selectSQL2);
              ps2.executeUpdate(selectSQL2);

              if (mailid != null) {
                  List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
                  return ok(livebooks.render(books, "Recommendations for you"));
              }
              else {
                  List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
                  return ok(fblivebooks.render(books, "Recommendations for you"));
              }

          }

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));

                String selectSQL1 ="INSERT INTO LIVETABLE (bookrating,age,country_code,title,author,coverimage, tags, url)\n" +
                        "SELECT isbn,isbn,author,title, author,coverimage, tags, url FROM booksdata\n" +
                        "WHERE Tags in (" +list.get(i)+ ");";

                PreparedStatement ps1 = connection.prepareStatement(selectSQL1);
            System.out.println("selectSQL1" + selectSQL1);
                ps1.executeUpdate(selectSQL1);
            }
          System.out.println("selectSQL2" + selectSQL2);
          ps2.executeUpdate(selectSQL2);
      }
            catch (Exception e) {
                System.out.println("=============================SQL Update has failed : " + e);
            }
      finally {
          try { connection.close(); } catch (Exception e) { /* ignored */ }
      }


      //  flash("success", "Saved answers Successfully.");



      if (mailid != null) {
          List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
          return ok(livebooks.render(books, "Recommendations for you"));
      }
      else {
          List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
          return ok(fblivebooks.render(books, "Recommendations for you"));
      }


    }
}