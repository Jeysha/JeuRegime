package models;
 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.mysql.jdbc.PreparedStatement;

import play.data.validation.*;
import play.db.jpa.*;
 
@Entity
public class Comment extends Model {
 
	@Required
    public String author;
	@Required
    public Date postedAt;
     
    @Type(type = "org.hibernate.type.TextType")
    @Required
    @MaxSize(20000)
    public String content;
    
    @Required
    @ManyToOne
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    
    public String toString() {
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }
}