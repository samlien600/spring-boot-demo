package com.example.demo.dao;


import com.example.demo.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getById(Integer studentId);
    String insert(Customer customer);
    String insertList(List<Customer> customerList);
    String update(Customer customer);
    List<Customer> selectAll();
    String delete(int id);

}




