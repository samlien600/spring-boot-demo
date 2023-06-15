package com.example.demo.service;

import com.example.demo.dao.CustomerDaoJPA;
import com.example.demo.dao.CustomerDaoMyBatis;
import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerDaoJPA customerDaoJPA;

    @Autowired
    private CustomerDaoMyBatis customerDaoMyBatis;

    // Spring Boot JDBC Start
    @Override
    public Customer getById(int id) {
        Customer customer = customerDao.getById(id);
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getName()).append("_demo");

        customer.setName(sb.toString());
        return customer;
    }

    @Override
    public String insert(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public String insertList(List<Customer> customerList) {
        if (customerList.size() > 10) {
            throw new IllegalArgumentException("can not insert greater than 10 row");
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

    // Spring Boot DATA JPA start
    public CustomerEntity getByIdJPA(int id) {
        CustomerEntity customer = customerDaoJPA.findById(id).orElse(null);
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getName()).append("_demo");

        customer.setName(sb.toString());
        return customer;
    }

    public CustomerEntity getByNameJPA(String name) {
        CustomerEntity customer = customerDaoJPA.getByName(name);
        return customer;
    }

    public String insertJPA(CustomerEntity customer) {
        customerDaoJPA.save(customer);
        return "create row";
    }

    public String deleteJPA(int id) {
        customerDaoJPA.deleteById(id);
        return "delete row";
    }

    public String updateJPA(CustomerEntity customer) {
        CustomerEntity cus = customerDaoJPA.findById(customer.getId()).orElse(null);
        if (cus != null) {
            customerDaoJPA.save(customer);
            return "update row";
        } else {
            throw new IllegalArgumentException("can not find id:" + customer.getId());
        }
    }

    public List<CustomerEntity> queryByNative(String name) {
        List<CustomerEntity> customerList = customerDaoJPA.queryByNative(name+"%");
        return customerList;
    }

    // MyBatis
    @Override
    public List<Customer> getByNameMyBatis(String name) {
        return customerDaoMyBatis.getByName(name +"%");
    }
}
