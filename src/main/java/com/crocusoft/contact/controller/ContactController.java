package com.crocusoft.contact.controller;

import com.crocusoft.contact.dto.ContactDto;
import com.crocusoft.contact.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("send-mail")
    public ResponseEntity<Void> sendMail(@Valid @RequestBody ContactDto contactDto) {
        contactService.saveContact(contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
