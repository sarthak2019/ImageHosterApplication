package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    //This controller method is called when the request pattern is of type 'users/registration'
    //This method declares User type and UserProfile type object
    //Sets the user profile with UserProfile type object
    //Adds User type object to a model and returns 'users/registration.html' file
    @RequestMapping("users/registration")
    public String registration(Model model) {
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);
        model.addAttribute("User", user);
        return "users/registration";
    }

    //This controller method is called when the request pattern is of type 'users/registration' and also the incoming request is of POST type
    //This method calls the business logic and after the user record is persisted in the database, directs to login page
    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(User user, Model model) {
    /* Added the object of Model type object to the parameters of the method registerUser(), so that the attributes
    named as passwordTypeError and User can be added as the attributes of this Model type object and the view will
    show users/registration page agian, if the password entered by the user does not contain atleast 1 alphabet,
    1 number & 1 special character. */
        String password = user.getPassword();
        /* Fetched the password entered by the user during registration by calling getPassword() method on the object
        of the User class */
        String error = "Password must contain atleast 1 alphabet, 1 number & 1 special character";
        /* Created and assigned value to the string named error which will be assigned as the value of the model
        attribute named passwordTypeError. */
        boolean hasAlphabet = false;
        /* Created a new boolean variable named hasAlphabet which will be used to check if the entered password
        contains any alphabet. */
        boolean hasDigit = false;
        /* Created a new boolean variable named hasDigit which will be used to check if the entered password
        contains any digit. */
        boolean hasSpecialCharacter = false;
        /* Created a new boolean variable named hasSpecialCharacter which will be used to check if the entered password
        contains any special character. */
        for(int i = 0; i < password.length(); i++){
        /* This for loop will be used to check if the password entered by the user contains atleast 1 alphabet,
        1 number & 1 special character */
            char x = password.charAt(i);
            if(Character.isLetter(x)){
                hasAlphabet = true;
                /* If the character at the specific position of the password is a letter, i.e, an alphabet, then the
                variable hasAlphabet will be set as true. */
            }
            if(Character.isDigit(x)){
                hasDigit = true;
                /* If the character at the specific position of the password is a digit, then the
                variable hasDigit will be set as true. */
            }
            if(!Character.isLetter(x)&&!Character.isDigit(x)){
                hasSpecialCharacter = true;
                /* If the character at the specific position of the password is any character other than a digit or
                a letter, then the variable hasSpecialCharacter will be set as true. */
            }
        }
        if(hasAlphabet == true && hasDigit == true && hasSpecialCharacter == true) {
        /* If the entered password contains atleast 1 alphabet, 1 number & 1 special character then this if block will be
        executed and the user details will get saved in the database by calling the method userService.registerUser(). */
            userService.registerUser(user);
            /* The user details will get saved in the database by calling the method userService.registerUser() */
            return "users/login";
            /* Used the return statement return "users/login";, so that the view will show the /users/login
            page, so that the user can login by entering the username and password. */
        }
        else{
        /* If the entered password does not contain atleast 1 alphabet, 1 number & 1 special character, then this
        else block will be executed and the view will again show users/registration page with a message: ‘Password must contain at least 1 alphabet, 1 number & 1 special character’. */
            User user1 = new User();
            /* Created a new object of the User class which will be assigned as the value of the model attribute named
            User. */
            UserProfile profile1 = new UserProfile();
            /* Created a new object of the UserProfile class which will be assigned as the value of the object of
            the UserProfile class present in the User class. */
            user1.setProfile(profile1);
            /* Set the value of the object of the UserProfile class present in the User class. */
            model.addAttribute("passwordTypeError", error);
            /* Added an attribute with the name passwordTypeError to the model object, which will be used to display
            the message: Password must contain atleast 1 alphabet, 1 number & 1 special character in the users/registration page, if the password
            does not contain atleast 1 alphabet, 1 number & 1 special character. */
            model.addAttribute("User", user1);
            /* Added an attribute with the name User to the model object with the value assigned as the object of the
            User class, which will be used to store the registration details of the user. */
            return "users/registration";
            /* Used the return statement return "users/registration";, so that the view will show the
            users/registration page, so that the user can register again, if the entered password by the user
            does not contain atleast 1 alphabet, 1 number & 1 special character. */
        }
    }

    //This controller method is called when the request pattern is of type 'users/login'
    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    //This controller method is called when the request pattern is of type 'users/login' and also the incoming request is of POST type
    //The return type of the business logic is changed to User type instead of boolean type. The login() method in the business logic checks whether the user with entered username and password exists in the database and returns the User type object if user with entered username and password exists in the database, else returns null
    //If user with entered username and password exists in the database, add the logged in user in the Http Session and direct to user homepage displaying all the images in the application
    //If user with entered username and password does not exist in the database, redirect to the same login page
    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session) {
        User existingUser = userService.login(user);
        if (existingUser != null) {
            session.setAttribute("loggeduser", existingUser);
            return "redirect:/images";
        } else {
            return "users/login";
        }
    }

    //This controller method is called when the request pattern is of type 'users/logout' and also the incoming request is of POST type
    //The method receives the Http Session and the Model type object
    //session is invalidated
    //All the images are fetched from the database and added to the model with 'images' as the key
    //'index.html' file is returned showing the landing page of the application and displaying all the images in the application
    @RequestMapping(value = "users/logout", method = RequestMethod.POST)
    public String logout(Model model, HttpSession session) {
        session.invalidate();

        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "index";
    }
}
