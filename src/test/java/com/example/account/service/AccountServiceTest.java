package com.example.account.service;

import com.example.account.dto.AccountCustomerDto;
import com.example.account.dto.AccountDto;
import com.example.account.dto.TransactionDto;
import com.example.account.dto.converter.AccountDtoConverter;
import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountDtoConverter accountDtoConverter;
    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccountTest() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1", BigDecimal.valueOf(1000));

        Customer customer = new Customer("1", "name", "surname", Set.of());

        AccountCustomerDto accountCustomerDto = new AccountCustomerDto("1", "name", "surname");

        Account account = new Account("1",BigDecimal.valueOf(1000), LocalDateTime.now(), customer, Set.of());


        Transaction transaction = new Transaction(null, TransactionType.INITIAL, BigDecimal.valueOf(1000), LocalDateTime.now(), account);


        TransactionDto transactionDto = new TransactionDto("", TransactionType.INITIAL, createAccountRequest.getInitialCredit(),LocalDateTime.now());

        AccountDto expected = new AccountDto("1", BigDecimal.valueOf(1000), LocalDateTime.now(), accountCustomerDto, Set.of(transactionDto));

        when(customerService.findCustomerById("1")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);

        when(accountDtoConverter.convert(account)).thenReturn(expected);

        AccountDto result = accountService.createAccount(createAccountRequest);

    }
}
