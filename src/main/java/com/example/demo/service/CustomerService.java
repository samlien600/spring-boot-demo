package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    Customer getById(Integer id);
    String insert(Customer customer);
    String insertList(List<Customer> customerList);
    String update(Customer customer);
    String delete(int id);

}
