package com.egen.orderapi.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@XmlRootElement
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long customerId;
}

