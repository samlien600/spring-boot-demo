package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer getById(Integer id) {
        return customerDao.getById(id);
    }

    @Override
    public String insert(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public String insertList(List<Customer> customerList) {
        if (customerList.size() > 10) {
            return "can not insert greater than 10 row";
        }
        return customerDao.insertList(customerList);
    }

    @Override
    public String update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public String delete(int id) {
        return customerDao.delete(id);
    }
}
