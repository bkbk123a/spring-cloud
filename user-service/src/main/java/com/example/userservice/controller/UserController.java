package com.example.userservice.controller;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserServiceImpl;
import com.example.userservice.vo.request.CreateUserReq;
import com.example.userservice.vo.response.CreateUserRes;
import com.example.userservice.vo.response.GetUserRes;
import com.example.userservice.vo.Order;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserServiceImpl userService;

    private final OrderServiceClient orderServiceClient;

    @Autowired
    public UserController(UserServiceImpl userService, OrderServiceClient orderServiceClient) {
        this.userService = userService;
        this.orderServiceClient = orderServiceClient;
    }

    // 유저 생성
    @PostMapping("/users/create")
    public ResponseEntity<CreateUserRes> createUser(@RequestBody CreateUserReq user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        CreateUserRes responseUser = mapper.map(userDto, CreateUserRes.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseUser);
    }

    // 전체 유저 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserRes>> getUsers() {
        Iterable<UserEntity> userList = userService.getAllUsers();

        List<GetUserRes> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, GetUserRes.class));
        });

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    // 특정 유저 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserRes> getUser(@PathVariable("userId") String userId) {
        // 특정 유저 기본 정보 조회
        UserEntity user = userService.getUserByIdOrElseThrow(userId);
        // 특정 유저 주문 정보 조회
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderServiceClient.getOrders(userId);
        } catch (FeignException ex) {
            log.error(ex.getMessage());
        }
        // 응답 설정
        GetUserRes res = new ModelMapper().map(user, GetUserRes.class);
        res.setOrders(orders);
        
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}