package com.crocusoft.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "Name cannot be blank")
    String name;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String email;
    String subject;
    String message;
}
