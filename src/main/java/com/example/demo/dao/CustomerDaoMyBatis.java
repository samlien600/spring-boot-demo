package com.example.demo.dao;

import com.example.demo.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerDaoMyBatis {

    @Select("Select id,name from customers where name like #{name}")
    public List<Customer> getByName(String name);

}
