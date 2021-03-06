package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Created by Bhagya.
 */

@Entity
@Table(name = "booksdata")
public class Booksdata extends Model {

    @Id
    @GeneratedValue
    public String isbn;

    @Constraints.Required

    public String title;


    public String author;

    @ManyToOne
    public String tags;

    @Column( name="YearOfPublication")
    public Integer year;

    @Constraints.Required

    public String publisher;


    public String coverimage;

    public String url;

   /* @JsonIgnore
    @ManyToMany
    @JoinTable(name = "taste_preferences",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") })

*/

    public static Finder<String,Booksdata> find = new Finder<>(Booksdata.class);



    //List<Booksdata> nos = Booksdata.find.select("isbn").setDistinct(true).findList();
   // int count = Ebean.find(Booksdata.class.fetch("isbn").findRowCount();

  //  public static List<Booksdata> getPage(int page, int size) {
   //     PagedList<Booksdata> findPagedList = Ebean.find(Booksdata.class).findPagedList(page,size);
   //     return findPagedList.getList();
   // Model.find.where().setFirstRow(offset).setMaxRows(limit).findList();



}
