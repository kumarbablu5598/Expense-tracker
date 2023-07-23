package com.Expense.tracker.Expense.tracker.service;
import com.Expense.tracker.Expense.tracker.dto.SignInInput;
import com.Expense.tracker.Expense.tracker.dto.SignUpOutput;
import com.Expense.tracker.Expense.tracker.model.AuthenticationToken;
import com.Expense.tracker.Expense.tracker.model.User;
import com.Expense.tracker.Expense.tracker.repository.IAuthToken;
import com.Expense.tracker.Expense.tracker.repository.IUserRepo;

import com.Expense.tracker.Expense.tracker.service.utility.emailUtility.EmailHandler;
import com.Expense.tracker.Expense.tracker.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo iUserRepo;
    @Autowired
    IAuthToken iAuthToken;

    public SignUpOutput signupUser(User user){
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??

        User existingUser = iUserRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getPassword());

            //save user with the new encrypted password

            user.setPassword(encryptedPassword);
            iUserRepo.save(user);
            return new SignUpOutput(signUpStatus, "Userregistered successfully!!!");
        }
        catch(Exception e) {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }
    }
    public List<User> getAllUsers(){
        return  iUserRepo.findAll();
    }
    public String signInUser(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getUserEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }

        //check if this user email already exists ??
        User existingUser = iUserRepo.findFirstByEmail(signInEmail);


        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;
        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getUserPassword());
            if(existingUser.getPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                iAuthToken.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }
    public String sigOutUser(String email) {
        User user = iUserRepo.findFirstByEmail(email);
       iAuthToken.delete(iAuthToken.findFirstByUser(user));
       // authTokenRepo.delete(authTokenRepo.findFirstByPatient(patient));
        return "User Signed out successfully";
    }

}
