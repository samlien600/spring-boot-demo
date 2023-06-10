package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    ResponseEntity<Customer> getById(Integer id);
    ResponseEntity<String> insert(Customer customer);
    ResponseEntity<String> insertList(List<Customer> customerList);
    ResponseEntity<String> update(Customer customer);
    ResponseEntity<String> delete(int id);

}
