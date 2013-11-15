package models;
 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.mysql.jdbc.*;

import play.data.validation.*;
import play.db.jpa.*;
 
@Entity
public class Post extends Model {
 
	@Required
	public String title;
    
	@Required
	public Date postedAt;
    
    @Type(type = "org.hibernate.type.TextType")
    @Required
    @MaxSize(20000)
    public String content;
    
    @Required
    @ManyToOne
    public Utilisateur author;
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Tag> tags; 
    public Post(Utilisateur author, String title, String content) { 
        this.comments = new ArrayList<Comment>();
        this.tags = new TreeSet<Tag>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    public Post addComment1(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }
    public String addComment(String author, String content) {
    	String stm= "INSERT INTO JeuRegime" +
    "id, email, password, pseudo)"+
    "VALUES(this.email, this.password, this.pseudo)";
        return stm;
    }
    //pagination: voir le post précédent
    public Post previous() {
        return Post.find("postedAt < ? order by postedAt desc", postedAt).first();
    }
    //pagination: voir le post suivant 
    public Post next() {
        return Post.find("postedAt > ? order by postedAt asc", postedAt).first();
    }
   
    //tagguer un post
    public Post tagItWith(String name) {
        tags.add(Tag.findOrCreateByName(name));
        return this;
    }
   
    //retrouver tous les post avec un tag précis
    public static List<Post> findTaggedWith(String tag) {
        return Post.find(
            "select distinct p from Post p join p.tags as t where t.name = ?", tag
        ).fetch();
    }
    
    public static List<Post> findTaggedWith(String... tags) {
        return Post.find(
            "select distinct p.id from Post p join p.tags as t where t.name in (:tags) group by p.id having count(t.id) = :size"
        ).bind("tags", tags).bind("size", tags.length).fetch();
    }
    
    public String toString() {
        return title;
    }
}
