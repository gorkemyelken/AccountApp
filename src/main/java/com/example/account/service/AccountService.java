package com.example.account.service;

import com.example.account.dto.AccountDto;
import com.example.account.dto.converter.AccountDtoConverter;
import com.example.account.dto.request.CreateAccountRequest;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          TransactionService transactionService,
                          AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            account.getTransactions().add(transaction);
        }
        return accountDtoConverter.convert(accountRepository.save(account));
    }

}
