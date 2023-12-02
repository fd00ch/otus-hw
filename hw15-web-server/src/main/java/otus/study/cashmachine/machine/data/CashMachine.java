package otus.study.cashmachine.machine.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashMachine {
    private final MoneyBox moneyBox;
    public MoneyBox getMoneyBox() {
        return moneyBox;
    }
}
