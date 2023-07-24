package com.Expense.tracker.Expense.tracker.repository;

import com.Expense.tracker.Expense.tracker.model.AuthenticationToken;
import com.Expense.tracker.Expense.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthToken extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);

}
