package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bhagya.
 */

@Entity
@Table(name = "bookdata")
public class Booksdata extends Model {

    @Id
    @GeneratedValue
    public String isbn;

    @Constraints.Required

    public String title_book;


    public String author_name;


    public Integer yearof_p;

    @Constraints.Required

    public String publisher_name;


    public String im1;


    public String im2;


    public String im3;

    public static Finder<String,Booksdata> find = new Finder<>(Booksdata.class);

    //List<Booksdata> nos = Booksdata.find.select("isbn").setDistinct(true).findList();
   // int count = Ebean.find(Booksdata.class.fetch("isbn").findRowCount();

  //  public static List<Booksdata> getPage(int page, int size) {
   //     PagedList<Booksdata> findPagedList = Ebean.find(Booksdata.class).findPagedList(page,size);
   //     return findPagedList.getList();
   // Model.find.where().setFirstRow(offset).setMaxRows(limit).findList();



}
