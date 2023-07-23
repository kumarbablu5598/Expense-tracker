package com.Expense.tracker.Expense.tracker.service;

import com.Expense.tracker.Expense.tracker.model.Product;
import com.Expense.tracker.Expense.tracker.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepo iProductRepo;

    public List<Product> getAllproduct(){
        return  iProductRepo.findAll();
    }
    //get product on particular date
    public List<Product> getproductondate(LocalDate localDate){
        List<Product> productList = getAllproduct();
        List<Product> requiredlist = new ArrayList<>();
        for(Product product : productList){
            if(product.getProductLocalDate().equals(localDate)) requiredlist.add(product);
        }
        return requiredlist;
    }

    //get product on particular time
    public List<Product> getproductontime(LocalTime localTime){
        List<Product> productList = getAllproduct();
        List<Product> requiredlist = new ArrayList<>();
        for(Product product : productList){
            if(product.getProductLocalTime().equals(localTime)) requiredlist.add(product);
        }
        return requiredlist;
    }
    public void addproduct(Product product){
        iProductRepo.save(product);
    }
}
