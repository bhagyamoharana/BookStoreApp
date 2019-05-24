package models;


import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

//import org.json.xml;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Profile_Bean extends Model {

	//@Json
    public int piid;
	public String profname;

	public Date dob;
	public String place;


	public static Finder<String,Profile_Bean> find = new Finder<>(Profile_Bean.class);


}
