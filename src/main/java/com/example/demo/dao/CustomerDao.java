package com.example.demo.dao;


import com.example.demo.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerDao {

    ResponseEntity<Customer> getById(Integer studentId);
    ResponseEntity<String> insert(Customer customer);
    ResponseEntity<String> insertList(List<Customer> customerList);
    ResponseEntity<String> update(Customer customer);
    ResponseEntity<String> delete(int id);

}




