package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;



@Entity
@Table(name = "answers_table")
public class AnswerSelected extends Model {


    public String  email_c;
    public String  q1;
    public String  q2;
    public String  q3;
    public String  q4;
    public String  q5;
    public String  q6;
    public String  q7;
    public String  q8;
    public String  q9;
    public String  q10;



    public static Finder<String, models.AnswerSelected> find = new Finder<>(models.AnswerSelected.class);
}

