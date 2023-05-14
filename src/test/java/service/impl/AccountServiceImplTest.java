package service.impl;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.entity.Account;
import com.telran.bankingapp.mapper.AccountMapper;
import com.telran.bankingapp.repository.AccountRepository;
import util.DtoCreator;
import util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@DisplayName("Account Service test class")
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountMapper accountMapper;

    @Mock
    AccountRepository accountRepository;



//    @Test
//    @DisplayName("Positive test. Get account by id")
//    void getAccountByIdTest() {
//        Account account = EntityCreator.getAccount();
//        AccountDTO accountDto = DtoCreator.getAccountDto();
//        UUID uuid = UUID.fromString(EntityCreator.UUID_EXAMPLE);
//
//        Mockito.when(accountRepository.findById(uuid)).thenReturn(Optional.ofNullable(account));
//        Mockito.when(accountMapper.toDto(account)).thenReturn(accountDto);
//
//        Mockito.verify(accountRepository).findById(uuid);
//        Mockito.verify(accountMapper).toDto(account);
//    }

    @Test
    void getAllAccountsTest() {
    }


    @Test
    void getNonExistAccountByIdTest() {
    }

    @Test
    void getAllActiveAccountsTest() {
    }

    @Test
    void getAccountsByProudctIdTest() {
    }
}