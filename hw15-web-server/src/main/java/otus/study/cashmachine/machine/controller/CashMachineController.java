package otus.study.cashmachine.machine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import otus.study.cashmachine.machine.model.CardPinRequestDTO;
import otus.study.cashmachine.machine.model.ChangePinRequestDTO;
import otus.study.cashmachine.machine.model.PutCashRequestDTO;
import otus.study.cashmachine.machine.model.WithdrawCashRequestDTO;
import otus.study.cashmachine.machine.service.CashMachineService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/atm")
@RequiredArgsConstructor
public class CashMachineController {
    private final CashMachineService cashMachineService;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/change-pin")
    public String changePin(@ModelAttribute("changepin") ChangePinRequestDTO changePinRequestDTO) {
        return "change-pin";
    }

    @PostMapping("/change-pin-result")
    public String changePinResult(@ModelAttribute("changepin") ChangePinRequestDTO changePinRequestDTO, Model model) {
        try {
            var result = cashMachineService.changePin(
                    changePinRequestDTO.getCardNumber(),
                    changePinRequestDTO.getCurrentPin(),
                    changePinRequestDTO.getNewPin());
            if (!result) {
                throw new RuntimeException("Pin change failed");
            }
            return "change-pin-result";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/get-balance")
    public String getBalance(@ModelAttribute("cardpin") CardPinRequestDTO cardPinRequestDTO) {
        return "get-balance";
    }

    @PostMapping("/get-balance-result")
    public String getBalanceResult(@ModelAttribute("cardpin") CardPinRequestDTO cardPinRequestDTO, Model model) {
        try {
            var balance = cashMachineService.checkBalance(
                    cardPinRequestDTO.getCardNumber(),
                    cardPinRequestDTO.getPin());
            model.addAttribute("balance", balance);
            return "get-balance-result";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/get-money")
    public String getMoney(@ModelAttribute("withdraw") WithdrawCashRequestDTO withdrawCashRequestDTO) {
        return "get-money";
    }

    @PostMapping("/get-money-result")
    public String getMoneyResult(@ModelAttribute("withdraw") WithdrawCashRequestDTO withdrawCashRequestDTO, Model model) {
        try {
            cashMachineService.getMoney(
                    withdrawCashRequestDTO.getCardNumber(),
                    withdrawCashRequestDTO.getPin(),
                    new BigDecimal(withdrawCashRequestDTO.getAmount()));
            return "get-money-result";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/put-money")
    public String putMoney(@ModelAttribute("putcash") PutCashRequestDTO putCashRequestDTO) {
        return "put-money";
    }

    @PostMapping("/put-money-result")
    public String putMoneyResult(@ModelAttribute("putcash") PutCashRequestDTO putCashRequestDTO, Model model) {
        try {
            cashMachineService.putMoney(
                    putCashRequestDTO.getCardNumber(),
                    putCashRequestDTO.getPin(),
                    List.of(
                            putCashRequestDTO.getAmount5000(),
                            putCashRequestDTO.getAmount1000(),
                            putCashRequestDTO.getAmount500(),
                            putCashRequestDTO.getAmount100()));
            return "put-money-result";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error";
        }
    }

    public void putMoney(String cardNum, String pin, List<Integer> notes) {
        cashMachineService.putMoney(cardNum, pin, notes);
    }


}
