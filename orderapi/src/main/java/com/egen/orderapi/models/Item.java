package com.egen.orderapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonFormat
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long itemId;

//    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinColumn(name = "orderId")
//    private Orders order;

    @Column
    private String itemName;

    @Column
    private int itemQuantity;


}
