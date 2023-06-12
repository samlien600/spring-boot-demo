package com.example.demo.controller;

import com.example.demo.aop.MyAspect;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    private static Logger log = LoggerFactory.getLogger(CustomerControllerTest.class);

    @Autowired
    private MockMvc mockMvn;

    @Test
    public void selectOne() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/customers/{id}", 1);

        MvcResult mvcResult = mockMvn.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("sam")))
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        log.info("response body：" + body);
    }

    @Transactional
    @Test
    public void insert() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"sam test\"\n" +
                        "}");
        mockMvn.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Transactional
    @Test
    public void delete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/customers/{id}", 1);
        mockMvn.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Transactional
    @Test
    public void update() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"sam test\",\n" +
                        "  \t\"id\" : 1\n" +
                        "}");
        mockMvn.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Transactional
    @Test
    public void insertList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customers/batch")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "  {\n" +
                        "    \"name\": \"sam test1\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "  \t\"name\": \"sam test2\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "  \t\"name\": \"sam test3\"\n" +
                        "  }\n" +
                        "]\n");
        mockMvn.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}