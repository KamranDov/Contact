package com.crocusoft.contact.service;

import com.crocusoft.contact.domain.Contact;
import com.crocusoft.contact.dto.ContactDto;
import com.crocusoft.contact.exception.MailSenderException;
import com.crocusoft.contact.mapper.ContactMapper;
import com.crocusoft.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    @Value("${spring.mail.username}")
    private String mailSenderUsername;

    private final JavaMailSender javaMailSender;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public void saveContact(ContactDto contactDto) {
        Contact contact = contactMapper.dtoToEntity(contactDto);
        sendMail(contactDto);
        contactRepository.save(contact);
        log.info("Customer saved successfully: {}", contactDto);
    }


    private void sendMail(ContactDto contactDto) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(mailSenderUsername);
            simpleMailMessage.setTo(contactDto.getEmail());
            simpleMailMessage.setSubject(contactDto.getSubject());
            simpleMailMessage.setText(contactDto.getMessage());

            javaMailSender.send(simpleMailMessage);
            log.info("Customer saved successfully: {}", contactDto);
        } catch (Exception e) {
            log.error("Error occurred while sending mail for customer: {}", contactDto, e);
            throw new MailSenderException("Error while sending email");

        }
    }
}
