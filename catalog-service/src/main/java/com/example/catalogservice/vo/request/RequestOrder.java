package com.example.catalogservice.vo.request;

import lombok.Getter;

@Getter
public class RequestOrder {
    private String userId;
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}