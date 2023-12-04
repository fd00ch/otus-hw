package otus.study.cashmachine.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardPinRequestDTO {
    private String cardNumber;
    private String pin;
}
