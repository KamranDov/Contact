package com.crocusoft.contact.service;

import com.crocusoft.contact.domain.Customer;
import com.crocusoft.contact.dto.CustomerDto;
import com.crocusoft.contact.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final JavaMailSender javaMailSender;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public void saveCustomer(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        sendMail(customerDto);
        customerRepository.save(customer);
        log.info("Customer saved successfully: {}", customerDto);

    }

    private void sendMail(CustomerDto customerDto) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(customerDto.getEmail());//göndərənin mail addressini database save edir
            simpleMailMessage.setTo("");//göndərəcəyin şəxsin mail-ini qeyd edirsən
            simpleMailMessage.setSubject(customerDto.getSubject());
            simpleMailMessage.setText(customerDto.getMessage());

            javaMailSender.send(simpleMailMessage);
            log.info("Customer saved successfully: {}", customerDto);
        } catch (Exception e) {
            log.error("Error occurred while sending mail for customer: {}", customerDto, e);
        }
    }
}
