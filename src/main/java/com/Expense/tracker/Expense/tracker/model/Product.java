package com.Expense.tracker.Expense.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productTitle;
    private String productDescription;
    private Integer productPrice;
    private LocalDate productLocalDate;
    private LocalTime productLocalTime;
    @OneToOne
    @JoinColumn(name = "fk_user_Id")
    private User user;

}
