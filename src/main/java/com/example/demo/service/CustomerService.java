package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerEntity;

import java.util.List;

public interface CustomerService {

    Customer getById(int id);
    String insert(Customer customer);
    String insertList(List<Customer> customerList);
    String update(Customer customer);
    String delete(int id);

    CustomerEntity getByIdJPA(int id);
    String insertJPA(CustomerEntity customer);
    String deleteJPA(int id);
    String updateJPA(CustomerEntity customer);
    CustomerEntity getByNameJPA(String name);
    List<CustomerEntity> queryByNative(String name);

    List<Customer> getByNameMyBatis(String name);
}
