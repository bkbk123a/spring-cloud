package com.example.userservice.vo.response;

import com.example.userservice.vo.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserRes {
    private String email;
    private String name;
    private String userId;
    private List<Order> orders;
}

