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
@Table(name = "ratings")

public class Ratings extends Model {

    @Id
    @Constraints.Required
    @Column( name="User_id")
    public int user_id;

    @Constraints.Required
    @Column( name="isbn")
    public int isbn;

    @JsonIgnore
    @Constraints.Required
    public int bookrating;

    Ratings(){
        this.user_id =user_id;
        this.isbn =isbn;
        this.bookrating =bookrating;

    }



    public static Finder<Integer,Ratings> find = new Finder<>(Ratings.class);


    @Override
    public String toString() {
        return "Ratings [user_id=" + user_id + ", isbn=" + isbn + ", bookrating="
                + bookrating + "]";
    }

}
