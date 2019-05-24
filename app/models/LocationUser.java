package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import play.*;


@Entity
@Table(name = "userprofile")

public class LocationUser extends Model {

    @Id
    @Constraints.Required

    public int user_id;

    @Constraints.Required
    @Column( name="age")
    public int age;

    @JsonIgnore
    @Constraints.Required
    public int country_code;

    LocationUser(){
        this.user_id =user_id;
        this.age =age;
        this.country_code =country_code;

    }




    // @JsonIgnore
    // @OneToMany(mappedBy="quizgiver",cascade = CascadeType.ALL)
    //   public List<AnswerSelected> answerSelectedLists;

    public static Finder<Integer,LocationUser> find = new Finder<>(LocationUser.class);

    //public static Finder<Integer,LocationUser> find = new Finder<>(LocationUser.class);

//public static Finder<String,LocationUser> findByAge = LocationUser.find("SELECT * FROM LocationUser as m WHERE m.Age =:?");


    // System.out.println("Inside Customer bean");
    //  Console.log("Inside class");

    //public static Finder<String,LocationUser> find = new Finder<>(LocationUser.class);


    @Override
    public String toString() {
        return "LocationUser [user_id=" + user_id + ", age=" + age + ", country_code="
                + country_code + "]";
    }

}
