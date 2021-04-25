package com.egen.orderapi.services;

import com.egen.orderapi.dto.OrderDTO;
import com.egen.orderapi.models.Item;
import com.egen.orderapi.models.Orders;
import com.egen.orderapi.repositories.OrderRepository;
import com.sun.tools.corba.se.idl.InvalidArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    RedisTemplate redisTemplate;

    @Caching(
            put = {@CachePut(value = "orderCache", key = "#order.orderId")},
            evict = {@CacheEvict(value = "allCustomersCache", allEntries = true)}
    )
    public @ResponseBody Orders createOrderService(OrderDTO order) {
        return this.repository.save(order.getOrder());
    }

    @Caching(
            put = {@CachePut(value = "fetchOrderCache", key = "#order.orderId")},
            evict = {@CacheEvict(value = "allCustomersCache", allEntries = true)}
    )
    public Orders fetchOrderService(long id) {
        //Write code to fetch order from redis Cache
//        if(redisTemplate.opsForValue().get(id)!=null) {
//            return (Orders) redisTemplate.opsForValue().get(id);
//        }
        Optional<Orders> optional = repository.findById(id);
        //redisTemplate.opsForValue().set(id,optional.get());
        return optional.orElse(new Orders());
    }

    // Fetch all orders
    public Collection<Orders> fetchAllOrders() {

        return repository.findAll();
    }
    // Delete order by ID
    public void deleteOrder(long OrderId) {
        repository.delete(fetchOrderService(OrderId));
    }

    // Added pagination to getAllOrders to limit results
    public List<Orders> getAllOrders(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Orders> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Orders>();
        }
    }

    public Collection<Item> getAllItems(){
        return repository.findByItem() ;
    }
}
