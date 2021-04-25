package com.egen.orderapi.models;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class Payment implements Serializable {
    @Column
    private String paymentMethod;
    @Column
    private LocalDate paymentDate;
    @Column
    private String paymentConfirmationNumber;
}
