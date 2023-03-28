package com.example.account.service;

import com.example.account.dto.converter.CustomerDtoConverter;
import com.example.account.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;
}