package com.egen.orderapi.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Shipping {

    @Column
    private float shippingCharges;
    @Column
    private String shippingAddressLine1;
    @Column
    private String shippingAddressLine2;
    @Column
    private String shippingCity;
    @Column
    private String shippingState;
    @Column
    private int shippingZip;

}
