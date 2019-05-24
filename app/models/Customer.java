package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@Table(name = "customer_users")
public class Customer extends Model {

    @Id
    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    public String name;

    @JsonIgnore
    @Constraints.Required
    public String password;
    public String city;
    public String country;
    public String gender;
    public String age;


   // @JsonIgnore
   // @OneToMany(mappedBy="quizgiver",cascade = CascadeType.ALL)
 //   public List<AnswerSelected> answerSelectedLists;

    public static Finder<String,Customer> find = new Finder<>(Customer.class);
   // System.out.println("Inside Customer bean");
  //  Console.log("Inside class");


}
