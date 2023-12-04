package otus.study.cashmachine.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutCashRequestDTO {
    private String cardNumber;
    private String pin;
    private int amount100;
    private int amount500;
    private int amount1000;
    private int amount5000;
}
