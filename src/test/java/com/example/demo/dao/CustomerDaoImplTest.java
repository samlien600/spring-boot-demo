package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerDaoImplTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void getById() {
        ResponseEntity<Customer> customer = customerDao.getById(1);
        assertNotNull(customer.getBody());
        assertEquals("sam", customer.getBody().getName());
    }

    @Transactional
    @Test
    public void delete() {
        ResponseEntity<String> customer = customerDao.delete(1);
        assertNotNull(customer.getBody());
    }

    @Transactional
    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("demo");
        ResponseEntity<String> result = customerDao.update(customer);
        assertEquals("update row", result.getBody());
    }

    @Transactional
    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setName("test");
        ResponseEntity<String> result = customerDao.insert(customer);
        assertEquals("create row", result.getBody());
    }

    @Transactional
    @Test
    public void insertList() {
        List<Customer> customerList = new ArrayList<Customer>();
        Customer custome;
        int number = 5;
        for (int i=0;i < number;i++) {
            custome = new Customer();
            custome.setName("test" +i);
            customerList.add(custome);
        }
        ResponseEntity<String> result = customerDao.insertList(customerList);
        assertEquals("batch insert", result.getBody());
    }
}