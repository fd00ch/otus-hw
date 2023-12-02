package otus.study.cashmachine.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePinRequestDTO {
    private String cardNumber;
    private String currentPin;
    private String newPin;
}
