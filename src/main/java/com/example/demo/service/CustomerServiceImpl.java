package com.example.demo.service;

import com.example.demo.Customer;
import com.example.demo.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResponseEntity<Customer> getById(Integer id) {
        return customerDao.getById(id);
    }

    @Override
    public ResponseEntity<String> insert(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public ResponseEntity<String> insertList(List<Customer> customerList) {
        return customerDao.insertList(customerList);
    }

    @Override
    public ResponseEntity<String> update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        return customerDao.delete(id);
    }
}
