package com.Expense.tracker.Expense.tracker.service;

import com.Expense.tracker.Expense.tracker.model.AuthenticationToken;
import com.Expense.tracker.Expense.tracker.model.User;
import com.Expense.tracker.Expense.tracker.repository.IAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IAuthToken iAuthToken;

    public void saveToken(AuthenticationToken token){
        iAuthToken.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return iAuthToken.findByUser(user);
    }
    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = iAuthToken.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }
         String tokenConnectedEmail = authToken.getUser().getEmail();

        return tokenConnectedEmail.equals(email);
    }
}
