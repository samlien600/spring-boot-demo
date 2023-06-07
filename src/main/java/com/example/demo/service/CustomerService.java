package com.example.demo.service;

import com.example.demo.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Integer id);
    String insert(Customer customer);
    String insertList(List<Customer> customerList);
    String update(Customer customer);
    List<Customer> selectAll();
    String delete(int id);

}
