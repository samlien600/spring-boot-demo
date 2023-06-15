package com.example.demo.dao;


import com.example.demo.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerDao  {
    Customer getById(Integer studentId);
    String insert(Customer customer);
    String insertList(List<Customer> customerList);
    String update(Customer customer);
    String delete(int id);

}




