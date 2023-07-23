package com.Expense.tracker.Expense.tracker.controller;

import com.Expense.tracker.Expense.tracker.model.Product;
import com.Expense.tracker.Expense.tracker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("getAllproduct")
    public List<Product> getallproduct(){
        return productService.getAllproduct();
    }
    @GetMapping("getAllproductbasedondate/{date}")
    public List<Product> getallproductBasedOnDate(@PathVariable LocalDate date){
        return productService.getproductondate(date);
    }
    @GetMapping("getAllproductbasedontime{time}")
    public List<Product> getallproductBasedOnTime(@PathVariable LocalTime time){
        return productService.getproductontime(time);
    }
    @PostMapping("addproduct")
    public void addproduct(@RequestBody Product product){
        productService.addproduct(product);
    }
}
