package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class CustomerEntity {

    @Column(name="name")
    private String name;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
