package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerEntity;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customersMyBatis")
public class CustomerMyBatisController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> selectLikeByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getByNameMyBatis(name));
    }
}
