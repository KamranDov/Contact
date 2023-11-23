package com.crocusoft.contact.service;

import com.crocusoft.contact.domain.UserEntity;
import com.crocusoft.contact.dto.UserDto;
import com.crocusoft.contact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${spring.mail.username}")
    private String mailSenderUsername;

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public void saveCustomer(UserDto userDto) {
        var customer = mapper.map(userDto, UserEntity.class);
        sendMail(userDto);
        userRepository.save(customer);
        log.info("Customer saved successfully: {}", userDto);

    }

    private void sendMail( UserDto userDto) {
        try {
            var simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(mailSenderUsername);
            simpleMailMessage.setTo(userDto.getEmail());
            simpleMailMessage.setSubject(userDto.getSubject());
            simpleMailMessage.setText(userDto.getMessage());

            javaMailSender.send(simpleMailMessage);
            log.info("Customer saved successfully: {}", userDto);
        } catch (Exception e) {
            log.error("Error occurred while sending mail for customer: {}", userDto, e);
        }
    }
}
