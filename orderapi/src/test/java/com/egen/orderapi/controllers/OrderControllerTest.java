package com.egen.orderapi.controllers;

import com.egen.orderapi.dto.OrderDTO;
import com.egen.orderapi.models.Orders;
import com.egen.orderapi.services.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private OrderService orderService;

    private final Long ORDER_ID = 1L;
    private final String ORDER_STATUS = "testStatus";

    @BeforeAll()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

   // returns 201
    @Test
    void createOrder() throws Exception {
        Orders order = new Orders();
        order.setOrderId(any());
        order.setItems(anyList());
        when(orderService.createOrderService(new OrderDTO())).thenReturn(order);

        mockMvc.perform(post("/api/v1/order/"))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.order").isArray());

        verify(orderService, times(1)).createOrderService(any(OrderDTO.class));
        verifyNoMoreInteractions(orderService);
    }


}