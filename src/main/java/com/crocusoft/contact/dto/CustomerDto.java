package com.crocusoft.contact.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class CustomerDto {

    String name;
    String email;
    String subject;
    String message;
}
