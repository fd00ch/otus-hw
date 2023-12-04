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
    public static final String INDEX_PAGE = "index";
    public static final String ERROR_PAGE = "error";
    public static final String ERROR_MSG_ATTRIBUTE = "errormsg";
    public static final String CHANGE_PIN_PAGE = "change-pin";
    public static final String CHANGE_PIN_RESULT_PAGE = "change-pin-result";
    public static final String GET_BALANCE_PAGE = "get-balance";
    public static final String GET_BALANCE_RESULT_PAGE = "get-balance-result";
    public static final String GET_MONEY_PAGE = "get-money";
    public static final String GET_MONEY_RESULT_PAGE = "get-money-result";
    public static final String PUT_MONEY_PAGE = "put-money";
    public static final String PUT_MONEY_RESULT_PAGE = "put-money-result";

    private final CashMachineService cashMachineService;

    @GetMapping("")
    public String index() {
        return INDEX_PAGE;
    }

    @GetMapping("/change-pin")
    public String changePin(@ModelAttribute("changepin") ChangePinRequestDTO changePinRequestDTO) {
        return CHANGE_PIN_PAGE;
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
            return CHANGE_PIN_RESULT_PAGE;
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
            return ERROR_PAGE;
        }
    }

    @GetMapping("/get-balance")
    public String getBalance(@ModelAttribute("cardpin") CardPinRequestDTO cardPinRequestDTO) {
        return GET_BALANCE_PAGE;
    }

    @PostMapping("/get-balance-result")
    public String getBalanceResult(@ModelAttribute("cardpin") CardPinRequestDTO cardPinRequestDTO, Model model) {
        try {
            var balance = cashMachineService.checkBalance(
                    cardPinRequestDTO.getCardNumber(),
                    cardPinRequestDTO.getPin());
            model.addAttribute("balance", balance);
            return GET_BALANCE_RESULT_PAGE;
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
            return ERROR_PAGE;
        }
    }

    @GetMapping("/get-money")
    public String getMoney(@ModelAttribute("withdraw") WithdrawCashRequestDTO withdrawCashRequestDTO) {
        return GET_MONEY_PAGE;
    }

    @PostMapping("/get-money-result")
    public String getMoneyResult(@ModelAttribute("withdraw") WithdrawCashRequestDTO withdrawCashRequestDTO, Model model) {
        try {
            cashMachineService.getMoney(
                    withdrawCashRequestDTO.getCardNumber(),
                    withdrawCashRequestDTO.getPin(),
                    new BigDecimal(withdrawCashRequestDTO.getAmount()));
            return GET_MONEY_RESULT_PAGE;
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
            return ERROR_PAGE;
        }
    }

    @GetMapping("/put-money")
    public String putMoney(@ModelAttribute("putcash") PutCashRequestDTO putCashRequestDTO) {
        return PUT_MONEY_PAGE;
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
            return PUT_MONEY_RESULT_PAGE;
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
            return ERROR_PAGE;
        }
    }

    public void putMoney(String cardNum, String pin, List<Integer> notes) {
        cashMachineService.putMoney(cardNum, pin, notes);
    }


}
