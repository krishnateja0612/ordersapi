package com.egen.orderapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
//@RedisHash("Orders")  implementing serializable for Redis to
// access serializable objects
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long orderId;

    @Column
    private String orderStatus;

    @Column
    private long orderTotal;

    @Column
    private long orderSubTotal;

    @Column
    private long orderTax;

    @Embedded
    @Column
    private Billing billing;

    @Embedded
    @Column
    private Shipping shipping;

    @Embedded
    @Column
    private Payment payment;

    @OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_fk",referencedColumnName = "orderId")
    private List<Item> items;

}
