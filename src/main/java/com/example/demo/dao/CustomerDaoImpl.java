package com.example.demo.dao;

import com.example.demo.model.Customer;
import com.example.demo.mapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_BY_ID_SQL = "select id,name from customers where id=:id";
    private final String INSERT_SQL = "insert into customers(name) value (:name)";
    private final String INSERT_LIST_SQL = "insert into customers(name) value (:name)";
    private final String UPDATE_SQL = "update customers set name=:name where id=:id";
    private final String DELETE_SQL = "delete from customers where id=:id";

    @Override
    public Customer getById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<Customer> customerList = namedParameterJdbcTemplate.query(GET_BY_ID_SQL, map, new CustomerRowMapper());
        if (!customerList.isEmpty()) {
            return customerList.get(0);
        }
        return null;
    }

    @Override
    public String insert(@RequestBody Customer customer) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_SQL, new MapSqlParameterSource(map), keyHolder);

        return "create row";
    }

    @Override
    public String insertList(@RequestBody List<Customer> customerList) {
        MapSqlParameterSource[] mapSource = new MapSqlParameterSource[customerList.size()];
        int i = 0;
        for (Customer stu : customerList) {
            mapSource[i] = new MapSqlParameterSource();
            mapSource[i].addValue("name", stu.getName());
            i++;
        }

        namedParameterJdbcTemplate.batchUpdate(INSERT_LIST_SQL, mapSource);
        return "batch insert";
    }

    @Override
    public String update(Customer customer) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        map.put("id", customer.getId());
        namedParameterJdbcTemplate.update(UPDATE_SQL, new MapSqlParameterSource(map));

        return "update row";
    }

    @Override
    public String delete(@PathVariable int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        namedParameterJdbcTemplate.update(DELETE_SQL, map);
        return "delete row";
    }

}
