package com.example.orderservice.vo.request;

import lombok.Getter;

@Getter
public class RequestOrder {
    private String userId;
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}