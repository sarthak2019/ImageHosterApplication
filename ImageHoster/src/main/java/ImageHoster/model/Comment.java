package ImageHoster.model;

import javax.persistence.*;
import java.util.Date;

// @Entity annotation specifies that the corresponding class is a JPA entity.
@Entity
/* @Table annotation provides more options to customize the mapping.
   Here the name of the table to be created in the database is explicitly mentioned as 'comment'. Hence the table
   named 'comment' will be created in the database with all the columns mapped to all the attributes in 'Comment' class. */
@Table(name = "comment")
public class Comment {

    // @Id annotation specifies that the corresponding attribute is a primary key.
    @Id
    /* @GeneratedValue defines how to generate value for the given column. strategy = GenerationType.AUTO sets
       @GeneratedValue automatic. If table has defined any default value or it has
       defined auto increment in table then in that case we can use strategy = GenerationType.AUTO. */
    @GeneratedValue(strategy = GenerationType.AUTO)
    /* @Column annotation specifies that the attribute will be mapped to the column in the database.
       Here the column name is explicitly mentioned as 'id'. */
    @Column(name = "id")
    private Integer id;
    /* Declared an integer type variable id. */

    /* @Column annotation specifies that the attribute will be mapped to the column in the database.
       Here the columnDefinition = "TEXT" specifies that this column can have text-based data with datatype as text
       in the database. */
    @Column(columnDefinition = "TEXT")
    private String text;
    /* Declared an string type variable text. */

    /* @Column annotation specifies that the attribute will be mapped to the column in the database.
       Here the column name is explicitly mentioned as 'createdDate'. */
    @Column(name = "createdDate")
    private Date createdDate;
    /* Declared an date type variable createdDate. */

    /* The 'comment' table is mapped to 'users' table with Many:One mapping.
       One comment can have only one user (owner) but one user can have multiple comments.
       FetchType is EAGER. */
    @ManyToOne(fetch = FetchType.EAGER)
    /* Below annotation indicates that the name of the column in 'comment' table referring the primary key
    in 'users' table will be 'user_id'. */
    @JoinColumn(name = "user_id")
    private User user;
    /* Declared an User type variable user. */

    /* The 'comment' table is mapped to 'images' table with Many:One mapping.
       One comment can have only one image but one image can have multiple comments.
       FetchType is EAGER. */
    @ManyToOne(fetch = FetchType.EAGER)
    /* Below annotation indicates that the name of the column in 'comment' table referring the primary key in
    'images' table will be 'image_id' */
    @JoinColumn(name = "image_id")
    private Image image;
    /* Declared an Image type variable image. */


    public Comment() {
    }
    /* Generated default constructor for Comment class. */

    public Comment(int id, String text, Date createdDate) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
    }
    /* Generated parameterised constructor for Comment class. */

    public Integer getId() {
        return id;
    }
    /* Generated getter method for the variable id present inside Comment class. */

    public void setId(Integer id) {
        this.id = id;
    }
    /* Generated setter method for the variable id present inside Comment class. */

    public String getText() {
        return text;
    }
    /* Generated getter method for the variable text present inside Comment class. */

    public void setText(String text) {
        this.text = text;
    }
    /* Generated setter method for the variable text present inside Comment class. */

    public Date getCreatedDate() {
        return createdDate;
    }
    /* Generated getter method for the variable createdDate present inside Comment class. */

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /* Generated setter method for the variable createdDate present inside Comment class. */

    public User getUser() {
        return user;
    }
    /* Generated getter method for the variable user present inside Comment class. */

    public void setUser(User user) {
        this.user = user;
    }
    /* Generated setter method for the variable user present inside Comment class. */

    public Image getImage() {
        return image;
    }
    /* Generated getter method for the variable image present inside Comment class. */

    public void setImage(Image image) {
        this.image = image;
    }
    /* Generated setter method for the variable image present inside Comment class. */

}
