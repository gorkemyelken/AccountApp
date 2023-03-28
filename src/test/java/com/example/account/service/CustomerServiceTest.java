package com.example.account.service;

import com.example.account.dto.CustomerDto;
import com.example.account.dto.converter.CustomerDtoConverter;
import com.example.account.exception.CustomerNotFoundException;
import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDtoConverter customerDtoConverter;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidCustomerId_whenGetCustomerById_thenReturnCustomerDto() {
        // Given
        Customer customer = new Customer("1", "name", "surname", Set.of());

        CustomerDto customerDto = new CustomerDto("1", "name", "surname", Set.of());

        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        when(customerDtoConverter.convertToCustomerDto(customer)).thenReturn(customerDto);

        // When
        CustomerDto result = customerService.getCustomerById("1");

        // Then
        Assertions.assertEquals(customerDto, result);
        verify(customerRepository, times(1)).findById("1");
        verify(customerDtoConverter, times(1)).convertToCustomerDto(customer);
    }

    @Test
    public void givenInvalidCustomerId_whenGetCustomerById_thenThrowException() {
        String customerId = "1";
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
    }

}
