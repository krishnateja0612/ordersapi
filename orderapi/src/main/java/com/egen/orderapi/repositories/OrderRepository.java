package com.egen.orderapi.repositories;

import com.egen.orderapi.models.Item;
import com.egen.orderapi.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Async
    @Query("SELECT o FROM Orders o WHERE o.orderId=:id ")
     Optional<Orders> findById(long id);

    //Please remove it later. Just to test join query.
    @Async
    @Query("SELECT i,o.orderId FROM Item i INNER JOIN Orders o ON o.orderId = i.itemId")
    Collection<Item> findByItem();


}
