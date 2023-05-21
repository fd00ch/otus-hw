package otus.study.cashmachine.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.study.cashmachine.bank.dao.AccountDao;
import otus.study.cashmachine.bank.data.Account;
import otus.study.cashmachine.bank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    void createAccountMock() {
// @TODO test account creation with mock and ArgumentMatcher
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        ArgumentMatcher<Account> accountMatcher =
                account -> account.getAmount().compareTo(expectedAccount.getAmount()) == 0;
        when(accountDao.saveAccount(argThat(accountMatcher))).thenReturn(expectedAccount);

        Account testAccount = accountServiceImpl.createAccount(BigDecimal.TEN);

        assertEquals(expectedAccount, testAccount);
    }

    @Test
    void createAccountCaptor() {
//  @TODO test account creation with ArgumentCaptor
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        BigDecimal expectedAmount = expectedAccount.getAmount();
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        when(accountDao.saveAccount(accountCaptor.capture())).thenReturn(expectedAccount);

        accountServiceImpl.createAccount(BigDecimal.TEN);

        assertEquals(expectedAmount, accountCaptor.getValue().getAmount());
    }

    @Test
    void addSum() {
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        when(accountDao.getAccount(anyLong())).thenReturn(expectedAccount);
        BigDecimal expectedAmount = BigDecimal.valueOf(11);

        BigDecimal amountAfterAddingSum = accountServiceImpl.putMoney(1L, BigDecimal.ONE);

        assertEquals(expectedAmount, amountAfterAddingSum);
    }

    @Test
    void getSum() {
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        when(accountDao.getAccount(anyLong())).thenReturn(expectedAccount);
        BigDecimal expectedAmount = BigDecimal.valueOf(9);

        BigDecimal amountAfterGettingSum = accountServiceImpl.getMoney(1L, BigDecimal.ONE);

        assertEquals(expectedAmount, amountAfterGettingSum);
    }

    @Test
    void getSumIfNotEnoughMoney() {
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        when(accountDao.getAccount(anyLong())).thenReturn(expectedAccount);

        Exception thrown = assertThrows(IllegalArgumentException.class, () -> {
            accountServiceImpl.getMoney(1L, BigDecimal.valueOf(11L));
        });
        assertEquals("Not enough money", thrown.getMessage());
    }

    @Test
    void getAccount() {
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        when(accountDao.getAccount(anyLong())).thenReturn(expectedAccount);

        Account account = accountServiceImpl.getAccount(1L);

        assertEquals(expectedAccount, account);
    }

    @Test
    void checkBalance() {
        Account expectedAccount = new Account(1L, BigDecimal.TEN);
        BigDecimal expectedAmount = expectedAccount.getAmount();
        when(accountDao.getAccount(anyLong())).thenReturn(expectedAccount);

        BigDecimal amount = accountServiceImpl.checkBalance(1L);

        assertEquals(expectedAmount, amount);
    }
}
