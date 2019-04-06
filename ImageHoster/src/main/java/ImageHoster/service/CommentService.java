package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    /* Marked the class CommentService as a service provider. @Service annotation is a specialization of @Component annotation. It
    can be applied to classes only. It is used to mark the class as a service provider. */
    @Autowired
    private CommentRepository commentRepository;
    /* Autowired the object of CommentRepository class. @Autowired annotation marks a constructor, field, setter
    method or config method as to be autowired by Spring's dependency injection facilities. It is used for automatic
    dependency injection. */

    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }
    /* Defined the method createComment(), which will be used to save the comment in the database. */

}