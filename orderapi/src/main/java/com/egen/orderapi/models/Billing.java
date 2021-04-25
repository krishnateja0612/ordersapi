package com.egen.orderapi.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Billing {

    @Column
    private String billingAddressline1;
    @Column
    private String billingAddressline2;
    @Column
    private String billingCity;
    @Column
    private String billingState;
    @Column
    private int billingZip;

}
