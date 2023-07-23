package com.Expense.tracker.Expense.tracker.repository;

import com.Expense.tracker.Expense.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String newEmail);
}
