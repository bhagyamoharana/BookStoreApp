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
@Table(name = "livetable2")
public class NewLoggersReccomendTable extends Model {



    public Integer bookrating;
    public Integer age;
    public String country_code;


    @Constraints.Required
    public String title;

    @Constraints.Required
    public String author;

    public String coverimage;

    public String tags;
    public String url;

    public static Finder<Integer,NewLoggersReccomendTable> find = new Finder<>(NewLoggersReccomendTable.class);


}
