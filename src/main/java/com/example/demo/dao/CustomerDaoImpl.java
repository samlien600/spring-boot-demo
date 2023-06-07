package com.example.demo.dao;

import com.example.demo.Customer;
import com.example.demo.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Customer getById(Integer id) {
        String sql = "select id,name from customer where id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> selectAll() {
        String sql = "select id,name from customer";
        Map<String, Object> map = new HashMap<String, Object>();
        List<Customer> list = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());
        return list;
    }

    @Override
    public String insert(@RequestBody Customer customer) {
        System.out.println("insert ~");
        String sql = "insert into customer(name) value (:name)";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        System.out.println("getKey==" +keyHolder.getKey());

        return "row create";
    }

    @Override
    public String insertList(@RequestBody List<Customer> customerList) {
        String sql = "insert into customer(name) value (:name)";
        MapSqlParameterSource[] mapSource = new MapSqlParameterSource[customerList.size()];
        int i = 0;
        for (Customer stu : customerList) {
            mapSource[i] = new MapSqlParameterSource();
            mapSource[i].addValue("name", stu.getName());
            i++;
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSource);
        return "batch insert";
    }

    @Override
    public String update(Customer customer) {
        String sql = "update customer set name=:name where id=:id";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", customer.getName());
        map.put("id", customer.getId());
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map));

        return "update row";
    }

    @Override
    public String delete(@PathVariable int id) {
        System.out.println("delete~");
        Map<String, Object> map = new HashMap<String, Object>();
        String sql = "delete from customer where id=:id";
        map.put("id", id);

        namedParameterJdbcTemplate.update(sql, map);
        return "delete row";
    }

}
