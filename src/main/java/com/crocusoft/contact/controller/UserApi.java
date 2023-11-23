package com.crocusoft.contact.controller;

import com.crocusoft.contact.dto.UserDto;
import com.crocusoft.contact.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class UserApi {

    private final UserService userService;

    @PostMapping("send-mail")
    public ResponseEntity<Void> sendMail(@Valid @RequestBody UserDto userDto) {
        userService.saveCustomer(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
