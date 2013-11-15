package models;
 
import java.util.*;

import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.*;
 
@Entity

public class Utilisateur extends Model {
	 
    @Email
    @Required
    public String email;
    @Required
    public String password;
    @Required
    public String pseudo;

    
    public Utilisateur(String email, String password, String pseudo) {
        this.email = email;
        this.password = password;
        this.pseudo = pseudo;

    }
    public static Utilisateur connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    public String toString() {
        return email;
    }
}
    