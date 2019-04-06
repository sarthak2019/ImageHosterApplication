package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/* @Controller annotation indicates that the particular class serves the role of a controller */
@Controller
public class CommentController {
/* Marked the class CommentController as a controller class by using @Controller annotation. @Controller
annotation acts as a stereotype for the annotated class, indicating its role. The dispatcher scans such
annotated classes for mapped methods and detects @RequestMapping annotations. */

    @Autowired
    private CommentService commentService;
    /* Autowired the object of CommentService class. @Autowired annotation marks a constructor, field, setter
    method or config method as to be autowired by Spring's dependency injection facilities. It is used for automatic
    dependency injection.  */

    @Autowired
    private ImageService imageService;
    /* Autowired the object of ImageService class. @Autowired annotation marks a constructor, field, setter
    method or config method as to be autowired by Spring's dependency injection facilities. It is used for automatic
    dependency injection.  */

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    /* Created a Request Mapping that maps to the POST request URL ˜/image/{imageId}/{imageTitle}/comments",
    which will be used to save one comment for an image */
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String comment, Comment newComment, HttpSession session) throws IOException {
    /* Added the path variables imageId and imageTitle to the method parameters, so that the correct image can be fetched
    from the database by uniquely identifying the image by getting its id from the path variable imageId,
    if two or more images have the same title. */
        User user = (User) session.getAttribute("loggeduser");
        /* Fetched the object for the logged in user by calling getAttribute method on the object of HttpSession. */
        Image image = imageService.getImage(imageId);
        /* Fetched the object of the image by calling imageService.getImage() method. */
        newComment.setText(comment);
        /* Set the text variable present in Comment class. */
        newComment.setCreatedDate(new Date());
        /* Set the createdDate variable present in Comment class. */
        newComment.setUser(user);
        /* Set the user variable present in Comment class. */
        newComment.setImage(image);
        /* Set the image variable present in Comment class. */
        commentService.createComment(newComment);
        /* Called the method commentService.createComment(), so that the comment can be saved in the database. */
        return "redirect:/images/" + image.getId() + "/" + image.getTitle();
        /* Used the return statement return "redirect:/images/" + updatedImage.getId() + "/" + updatedImage.getTitle();,
         so that after the comment gets saved in the database, the controller will redirect to showImage() method
         in ˜ImageController class. */
    }
}
