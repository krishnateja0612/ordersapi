package com.egen.orderapi.dto;


import com.egen.orderapi.models.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Orders order;
}
