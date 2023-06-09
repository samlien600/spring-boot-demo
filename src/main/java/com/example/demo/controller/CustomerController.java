package com.example.demo.controller;

import com.example.demo.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<String> insert(@RequestBody Customer customer) {
        return customerService.insert(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> selectOne(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PutMapping("/customers")
    public ResponseEntity<String> update(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return customerService.delete(id);
    }

    @PostMapping("/customers/batch")
    public ResponseEntity<String> insertList(@RequestBody List<Customer> customerList) {
        return customerService.insertList(customerList);
    }
}
