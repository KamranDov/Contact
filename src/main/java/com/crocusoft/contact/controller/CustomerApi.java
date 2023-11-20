package com.crocusoft.contact.controller;

import com.crocusoft.contact.dto.CustomerDto;
import com.crocusoft.contact.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class CustomerApi {

    private final CustomerService customerService;

    @PostMapping("send-mail")
    public ResponseEntity<Void> sendMail(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return ResponseEntity.ok().build();

    }
}
