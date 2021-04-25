package com.egen.orderapi.services;

import com.egen.orderapi.dto.OrderDTO;
import com.egen.orderapi.models.Orders;
import com.egen.orderapi.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    OrderDTO orderDTO;
    Orders order;
    OrderService orderService;
    private final Long ORDER_ID = 1L;
    private final String ORDER_STATUS = "testStatus";

    @BeforeAll
    public void setUp(){
        order = new Orders();
        order.setOrderId(ORDER_ID);
        order.setOrderStatus(ORDER_STATUS);
    }
    @Test
    void createOrderService() {
        when(orderRepository.save(any(Orders.class))).thenReturn(order);
        Orders created = orderService.createOrderService(orderDTO);

        assertEquals(created.getOrderId(), ORDER_ID);
        assertEquals(created.getOrderStatus(), ORDER_STATUS);
        verify(orderRepository, times(1)).save(any(Orders.class));
        verifyNoMoreInteractions(orderRepository);

    }
}