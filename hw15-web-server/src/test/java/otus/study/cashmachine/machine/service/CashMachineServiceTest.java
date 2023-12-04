package otus.study.cashmachine.machine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import otus.study.cashmachine.TestUtil;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.data.Card;
import otus.study.cashmachine.bank.service.AccountService;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;
import otus.study.cashmachine.machine.data.CashMachine;
import otus.study.cashmachine.machine.data.MoneyBox;
import otus.study.cashmachine.machine.service.impl.CashMachineServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashMachineServiceTest {

    @Spy
    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardsDao cardsDao;

    @Mock
    private AccountService accountService;

    @Mock
    private MoneyBoxService moneyBoxService;

    private CashMachineServiceImpl cashMachineService;

    private CashMachine cashMachine = new CashMachine(new MoneyBox());

    @BeforeEach
    void init() {
        cashMachineService = new CashMachineServiceImpl(cardService, accountService, moneyBoxService, cashMachine);
    }


    @Test
    void getMoney() {
// @TODO create get money test using spy as mock
        doReturn(BigDecimal.TEN).when(cardService).getMoney("0000", "1111", BigDecimal.TEN);
        when(moneyBoxService.getMoney(any(), anyInt())).thenReturn(List.of(1, 1, 1, 1));

        List<Integer> result = cashMachineService.getMoney("0000", "1111", BigDecimal.TEN);

        assertEquals(List.of(1, 1, 1, 1), result);
    }

    @Test
    void putMoney() {
        final String CARD_NUMBER = "4554";
        final String PIN = "1221";
        doReturn(BigDecimal.TEN).when(cardService).getBalance(CARD_NUMBER, PIN);
        doReturn(BigDecimal.valueOf(6610L)).when(cardService).putMoney(CARD_NUMBER, PIN, BigDecimal.valueOf(6600L));

        BigDecimal result = cashMachineService.putMoney(CARD_NUMBER, PIN, List.of(1, 1, 1, 1));

        assertEquals(BigDecimal.valueOf(6610L), result);
    }

    @Test
    void checkBalance() {
        doReturn(BigDecimal.TEN).when(cardService).getBalance("0000", "1111");
        BigDecimal result = cashMachineService.checkBalance("0000", "1111");

        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void changePin() {
// @TODO create change pin test using spy as implementation and ArgumentCaptor and thenReturn
        final String CARD_NUMBER = "4554";
        final String OLD_PIN = "1221";
        final String NEW_PIN = "8998";
        when(cardsDao.getCardByNumber(CARD_NUMBER))
                .thenReturn(new Card(1L, CARD_NUMBER, 1L, TestUtil.getHash(OLD_PIN)));
        ArgumentCaptor<Card> cardCaptor = ArgumentCaptor.forClass(Card.class);
        when(cardsDao.saveCard(cardCaptor.capture())).thenReturn(new Card(1L, "", 1L, ""));

        cashMachineService.changePin(CARD_NUMBER, OLD_PIN, NEW_PIN);

        assertEquals(TestUtil.getHash(NEW_PIN), cardCaptor.getValue().getPinCode());
    }

    @Test
    void changePinWithAnswer() {
// @TODO create change pin test using spy as implementation and mock an thenAnswer
        final String CARD_NUMBER = "4554";
        final String OLD_PIN = "1221";
        final String NEW_PIN = "8998";
        when(cardsDao.getCardByNumber(CARD_NUMBER))
                .thenReturn(new Card(1L, CARD_NUMBER, 1L, TestUtil.getHash(OLD_PIN)));

        List<Card> cards = new ArrayList<>();

        when(cardsDao.saveCard(any())).thenAnswer((Answer<Card>) invocation -> {
            cards.add(invocation.getArgument(0));
            return invocation.getArgument(0);
        });

        cashMachineService.changePin(CARD_NUMBER, OLD_PIN, NEW_PIN);

        assertEquals(cards.get(0).getNumber(), CARD_NUMBER);
    }
}