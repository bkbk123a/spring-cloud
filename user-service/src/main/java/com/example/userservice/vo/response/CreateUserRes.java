package com.example.userservice.vo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRes {
    private String email;
    private String name;
    private String userId;
}