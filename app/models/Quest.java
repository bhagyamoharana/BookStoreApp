package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;



    @Entity
    @Table(name = "questions")
    public class Quest extends Model {

        @Id
        @GeneratedValue
        public Integer qID;

        @Constraints.Required
        public String question;

        public static Finder<Integer, models.Quest> find = new Finder<>(models.Quest.class);
    }

