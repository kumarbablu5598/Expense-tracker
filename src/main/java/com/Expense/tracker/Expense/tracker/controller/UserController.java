package com.Expense.tracker.Expense.tracker.controller;
import com.Expense.tracker.Expense.tracker.dto.SignInInput;
import com.Expense.tracker.Expense.tracker.dto.SignUpOutput;
import com.Expense.tracker.Expense.tracker.model.User;
import com.Expense.tracker.Expense.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("getAllusers")
    public List<User> getallUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("signIn")
    public String signInUser(@RequestBody SignInInput signInInput){
        return userService.signInUser(signInInput);
    }
    @GetMapping("signUp")
    public SignUpOutput signupUser(@RequestBody User user){
        return userService.signupUser(user);
    }
    @DeleteMapping("signout")
    public String signout(@RequestBody String email)
    {
        return userService.sigOutUser(email);
    }
}
