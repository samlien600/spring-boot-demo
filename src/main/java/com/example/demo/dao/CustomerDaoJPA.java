package com.example.demo.dao;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerDaoJPA extends CrudRepository<CustomerEntity, Integer> {

    public CustomerEntity getByName(String name);

    @Query(value="select id,name from customers where name like ?1 order by name desc", nativeQuery = true)
    public List<CustomerEntity> queryByNative(String name);
}
