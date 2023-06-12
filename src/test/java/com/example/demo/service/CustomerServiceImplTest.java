package com.example.demo.service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerDao customerDao;

    @Test
    public void getById() {
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1);
        mockCustomer.setName("test name");
        Mockito.when(customerDao.getById(1)).thenReturn(mockCustomer);

        Customer customer = customerService.getById(1);
        assertNotNull(customer);
        assertEquals(1, customer.getId());
        assertEquals("test name_demo", customer.getName());
    }

}
