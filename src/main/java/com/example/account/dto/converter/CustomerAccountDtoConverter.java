package com.example.account.dto.converter;

import com.example.account.dto.CustomerAccountDto;
import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convert(Account from){
            return new CustomerAccountDto(
                    Objects.requireNonNull(from.getId()),
                    from.getBalance(),
                    from.getTransactions().stream().map(t -> transactionDtoConverter.convert(t)).collect(Collectors.toSet()),
                    from.getCreationDate());
        }
}
