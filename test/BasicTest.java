import org.junit.*;

import java.util.*;

import play.test.*;
import models.*;
 
public class BasicTest extends UnitTest {
   /* @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }*/
	
    @SuppressWarnings("unused")
	@Test
    public void fullTest() {
        
    	// Create a new Utilisateur and save it
        Utilisateur bob = new Utilisateur("bob@gmail.com", "secret", "Bob").save();
        Utilisateur job = new Utilisateur("job@gmail.com", "secret", "Job").save();
        
        //Create Posts
        Post firstbobPost = new Post(bob, "About the model layer", "The model has a central position in a Play! application. It is the domain-specific representation of the information on which the application operates.").save();
        Post secondbobPost = new Post(bob, "test", "second post").save();
        Post jobPost = new Post(job, "The MVC application", " A Play! application follows the MVC architectural pattern as applied to the architecture of the Web.").save();
        
        //create Tag
        Tag play = new Tag("play");
        Tag architecture = new Tag("architecture");
        Tag test = new Tag("test");
        Tag mvc = new Tag("MVC");
        
        
        
        
    	// Well
    	assertEquals(0, Post.findTaggedWith("Red").size());
    	
    	// Tag it now
        firstbobPost.tagItWith("Red").tagItWith("Blue").save();
        secondbobPost.tagItWith("Red").tagItWith("Green").save();
        
     // Check
        assertEquals(2, Post.findTaggedWith("Red").size());        
        assertEquals(1, Post.findTaggedWith("Blue").size());
        assertEquals(1, Post.findTaggedWith("Green").size());
        assertEquals(1, Post.findTaggedWith("Red", "Blue").size());   
        assertEquals(1, Post.findTaggedWith("Red", "Green").size());   
        assertEquals(0, Post.findTaggedWith("Red", "Green", "Blue").size());  
        assertEquals(0, Post.findTaggedWith("Green", "Blue").size());
       
        List<Map> cloud = Tag.getCloud();
        assertEquals(
            "[{tag=Blue, pound=1}, {tag=Red, pound=2}, {tag=Green, pound=1}]", 
            cloud.toString()
        );
        
        //Create Comments
        Comment c1 = new Comment(secondbobPost, "Tom", " This post is useless ?").save();
        Comment c2 = new Comment(jobPost, "Guest", "You are right !").save();
        Comment c3 = new Comment(jobPost, "Mike", "I knew that ...").save();
        
        // Count things
        assertEquals(2, Utilisateur.count());
        assertEquals(3, Post.count());
        assertEquals(3, Comment.count());
     
        // Try to connect as Utilisateurs
        assertNotNull(Utilisateur.connect("bob@gmail.com", "secret"));
        assertNotNull(Utilisateur.connect("job@gmail.com", "secret"));
        assertNull(Utilisateur.connect("job@gmail.com", "badpassword"));
        assertNull(Utilisateur.connect("tom@gmail.com", "secret"));
     
        // Find all of Bob's posts
        List<Post> bobPosts = Post.find("author.email", "bob@gmail.com").fetch();
        assertEquals(2, bobPosts.size());
     
        // Find all comments related to Bob's posts
        List<Comment> bobComments = Comment.find("post.author.email", "bob@gmail.com").fetch();
        assertEquals(1, bobComments.size());
     
        // Find the most recent post
        Post frontPost = Post.find("order by postedAt desc").first();
        assertNotNull(frontPost);
        assertEquals("The MVC application", frontPost.title);
     
        // Check that this post has two comments
        assertEquals(0, frontPost.comments.size());
     
        // Post a new comment
        frontPost.addComment1("Jim", "Hello guys");
        assertEquals(1, frontPost.comments.size());
        assertEquals(4, Comment.count());
    }
}
