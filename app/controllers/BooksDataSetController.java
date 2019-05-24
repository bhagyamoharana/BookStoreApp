package controllers;

import models.Booksdata;
import models.NewLoggersReccomendTable;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import views.html.bookdataset.*;
import views.html.errors.*;
import views.html.recommendation.userrecom;
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
import java.util.Map;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import controllers.*;
import controllers.dao.*;
import controllers.dao.ConnectionHelper;

/**
 * Created by Bhagya .
 */

import play.mvc.Security;
import play.libs.concurrent.HttpExecutionContext;


import javax.inject.Inject;

public class BooksDataSetController extends Controller {

    @Inject
    FormFactory ff;

    @Inject
    ConnectionHelper connhelper;




    // for all books

    public Result index(){
        List<Booksdata> books = Booksdata.find.all();
        return ok(showbookdata.render(books,"All Books"));
    }



    public Result show(String id){
        Booksdata book = Booksdata.find.byId(id);
        if(book==null){
            return notFound(_404.render());
        }
        return ok(displaybooks.render(book));
    }

    public Result showAllBooksUsingPagination(){


         int size = 5;
        List<Booksdata> cocoTasks = Booksdata.find.query().where()
                .ilike("Title", "%%")
                .setFirstRow(0)
                .setMaxRows(100)
                .findPagedList()
                .getList();

            return ok(portfolio.render(cocoTasks, "Page Books"));

    }


    public Result showOnly16(int noofprod){
        List<Booksdata> books = Booksdata.find.all();


      //  int count = Ebean.find(Booksdata.class).fetch("isbn").findRowCount();
        //System.out.println("Count"+count);
        int itemCount = books.size();
        System.out.println("no of rows"+itemCount);
        for(itemCount=1;itemCount<noofprod;itemCount++) {
            return ok(phpview.render(books, "All Books"));
        }
        return ok("Status exceeding");
    }

    public Result showPage(){


        DynamicForm dynamicForm = ff.form().bindFromRequest();
        Map<String,String> input = dynamicForm.rawData();
        String searchText = dynamicForm.get("search");

      // String i1=dynamicForm.get("textId"));
System.out.println("the entered text is"+searchText);
        List<Booksdata> booksdataLists = Booksdata.find.query().where()
                .ilike("Title", "%%")
               // .orderBy("year asc")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(booksdataLists, "Page Books"));
    }
    public Result showPageAction(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%1%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Action Books"));


    }
    public Result showPageComedy(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%2%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Comedy Books"));


    }

    public Result showPageCooking(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%3%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageFamily(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%4%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageHistory(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%5%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageHorror(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%6%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }

    public Result showPageRomance(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%7%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageScience(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%8%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageSports(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%9%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }
    public Result showPageTravel(){
        List<Booksdata> books = Booksdata.find.query().where().ilike("Tags","%10%")
                .setFirstRow(0)
                .setMaxRows(55)
                .findPagedList()
                .getList();
        return ok(portfolio.render(books,"Cookery Books"));


    }

   public Result showLivetable(){

       Database database = Databases.createFrom(
               "com.mysql.jdbc.Driver",
               "jdbc:mysql://localhost/playdb",
               ImmutableMap.of(
                       "username", "root",
                       "password", "admin"
               )
       );
       Connection connection = database.getConnection();

       try {
           String selectSQL ="insert into livetable2 " +
                   "select distinct bookrating, age, country_code, title, author, coverimage, " +
                   "tags, url from livetable";

           System.out.println("MOHARANAAAAAAAAAAAAAAAAAAAA" + selectSQL);
           PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
           preparedStatement.executeUpdate(selectSQL);

       }
       catch (Exception e) {
           System.out.println("=============BBBB================SQL Update has failed : " + e);
       }

        List<NewLoggersReccomendTable> books = NewLoggersReccomendTable.find.all();
        return ok(livebooks.render(books,"Recommendations"));
    }


}
