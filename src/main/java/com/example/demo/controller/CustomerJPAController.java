package com.example.demo.controller;

import com.example.demo.model.CustomerEntity;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedQuery;
import java.util.List;

@RestController
@RequestMapping("/customersJPA")
public class CustomerJPAController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> selectOne(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getByIdJPA(id));
    }

    @GetMapping()
    public ResponseEntity<List<CustomerEntity>> selectLikeByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.queryByNative(name));
    }

    @PostMapping()
    public ResponseEntity<String> insert(@RequestBody CustomerEntity customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.insertJPA(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteJPA(id));
    }

    @PutMapping()
    public ResponseEntity<String> update(@RequestBody CustomerEntity customer){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateJPA(customer));
    }


}
