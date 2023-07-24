package com.Expense.tracker.Expense.tracker.controller;
import com.Expense.tracker.Expense.tracker.dto.SignInInput;
import com.Expense.tracker.Expense.tracker.dto.SignUpOutput;
import com.Expense.tracker.Expense.tracker.model.User;
import com.Expense.tracker.Expense.tracker.service.AuthenticationService;
import com.Expense.tracker.Expense.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService  authenticationService;
    @GetMapping("getAllusers")
    public List<User> getallUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("signIn")
    public String signInUser(@RequestBody SignInInput signInInput){
        return userService.signInUser(signInInput);
    }
    @PostMapping("signUp")
    public SignUpOutput signupUser(@RequestBody User user){
        return userService.signupUser(user);
    }
    @DeleteMapping("signout")
    public String signout(String email, String token)
    {
        if(authenticationService.authenticate(email,token))
        return userService.sigOutUser(email);
        else{
            return "Sign out not allowed for non authenticated user.";
        }
    }

}
