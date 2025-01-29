package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserServiceImpl;
import com.example.userservice.vo.CreateUserReq;
import com.example.userservice.vo.CreateUserRes;
import com.example.userservice.vo.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

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
    public ResponseEntity<GetUserRes> getUSer(@PathVariable("userId") String userId) {
        UserEntity user = userService.getUserByIdOrElseThrow(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ModelMapper().map(user, GetUserRes.class));
    }
}