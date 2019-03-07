package controllers;

import models.Booksdata;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

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

/**
 * Created by Bhagya .
 */

import play.mvc.Security;
import play.libs.concurrent.HttpExecutionContext;


import javax.inject.Inject;

public class BooksDataSetController  extends Controller{

    @Inject
    FormFactory formFactory;




    // for all books

    public Result index(){
        List<Booksdata> books = Booksdata.find.all();
        return ok(showbookdata.render(books,"All Books"));
    }

/*
    public Result showallBooks(String id){
        Booksdata book = Booksdata.find.byId(id);
        if(book==null){
            return notFound(_404.render());
        }
        return ok(showbookdata.render(book));
    }
*/

    public Result show(String id){
        Booksdata book = Booksdata.find.byId(id);
        if(book==null){
            return notFound(_404.render());
        }
        return ok(displaybooks.render(book));
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
        List<Booksdata> booksdataLists = Booksdata.find.query().where()
                .ilike("title_book", "%co%")
                .orderBy("yearof_p asc")
                .setFirstRow(0)
                .setMaxRows(25)
                .findPagedList()
                .getList();
        return ok(portfolio.render(booksdataLists, "Page Books"));
    }


}
