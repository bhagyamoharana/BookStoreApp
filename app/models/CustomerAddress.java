/*update
package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
@Table(name = "customer_address")
public class CustomerAddress extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String city;

    @Constraints.Required
    public String state;

    @Constraints.Required
    public String country;



    public CustomerAddress(String city,String state, String country) {

        this.city = city;
        this.state = state;
        this.country = country;

    }


    @JsonIgnore
    @OneToOne
    public Customer customer;

    public static Finder<String,CustomerAddress> find = new Finder<>(CustomerAddress.class);




}
*/