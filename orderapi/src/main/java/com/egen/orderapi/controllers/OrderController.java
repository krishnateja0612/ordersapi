package com.egen.orderapi.controllers;

import com.egen.orderapi.dto.OrderDTO;
import com.egen.orderapi.models.Item;
import com.egen.orderapi.models.Orders;
import com.egen.orderapi.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/orderapi")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("v1/order/{id}")
    @ApiOperation(value = "View a list of available orders",response = OrderDTO.class)
    public Orders getOrder(@PathVariable long id){
        return orderService.fetchOrderService(id);
    }

    @GetMapping("v1/orders")
    public @ResponseBody
    Collection<Orders> getAllOrders(){
        return orderService.fetchAllOrders();
    }

    @GetMapping("/v1/orders/sorter")
    public ResponseEntity<?> getAllOrdersSorted(
            @RequestParam(defaultValue = "100") Integer pageNo,
            @RequestParam(defaultValue = "200") Integer pageSize,
            @RequestParam(defaultValue = "customerId") String sortBy)
    {
        List<Orders> list = orderService.getAllOrders(pageNo, pageSize, sortBy);

        if(list.size()>0)
            return new ResponseEntity<List<Orders>>(list, new HttpHeaders(), HttpStatus.OK);
        else
            return ResponseEntity.ok("Customer Not found");
    }

    @PostMapping("/order")
    @CrossOrigin("*")
    public @ResponseBody  Orders createOrder(@RequestBody OrderDTO order){
        return this.orderService.createOrderService(order);
    }

    @DeleteMapping("/order/delete/{orderId}")
    @CrossOrigin("*")
    public ResponseEntity<String> cancelOrder(@PathVariable long orderId){
        this.orderService.deleteOrder(orderId);
        return  ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping("/v1/items")
    @ResponseBody
    public Collection<Item> getAllItems(){
        return orderService.getAllItems();

    }



}
