package com.example.demo.dao;

import com.example.demo.Customer;
import com.example.demo.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
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

    @Override
    public ResponseEntity<Customer> getById(Integer id) {
        String sql = "select id,name from customer where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        Customer customer = namedParameterJdbcTemplate.queryForObject(sql, map, new CustomerRowMapper());

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @Override
    public ResponseEntity<String> insert(@RequestBody Customer customer) {
        System.out.println("insert ~");
        String sql = "insert into customer(name) value (:name)";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        System.out.println("getKey==" +keyHolder.getKey());

        return ResponseEntity.status(HttpStatus.OK).body("create row");
    }

    @Override
    public ResponseEntity<String> insertList(@RequestBody List<Customer> customerList) {
        String sql = "insert into customer(name) value (:name)";
        MapSqlParameterSource[] mapSource = new MapSqlParameterSource[customerList.size()];
        int i = 0;
        for (Customer stu : customerList) {
            mapSource[i] = new MapSqlParameterSource();
            mapSource[i].addValue("name", stu.getName());
            i++;
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSource);
        return ResponseEntity.status(HttpStatus.OK).body("batch insert");
    }

    @Override
    public ResponseEntity<String> update(Customer customer) {
        String sql = "update customer set name=:name where id=:id";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        map.put("id", customer.getId());
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map));

        return ResponseEntity.status(HttpStatus.OK).body("update row");
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable int id) {
        System.out.println("delete~");
        Map<String, Object> map = new HashMap<String, Object>();
        String sql = "delete from customer where id=:id";
        map.put("id", id);

        namedParameterJdbcTemplate.update(sql, map);
        return ResponseEntity.status(HttpStatus.OK).body("delete row");
    }

}
