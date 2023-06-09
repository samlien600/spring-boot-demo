package com.example.demo.controller;

import com.example.demo.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<String> insert(@RequestBody Customer customer) {
        return customerService.insert(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> selectOne(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return customerService.delete(id);
    }

    @PostMapping("/batch")
    public ResponseEntity<String> insertList(@RequestBody List<Customer> customerList) {
        return customerService.insertList(customerList);
    }
}
