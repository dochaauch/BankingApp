package com.telran.bankingapp.controller;

import com.telran.bankingapp.dto.AccountDTO;
import com.telran.bankingapp.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    /**
     Returns a list of all AccountDTO objects.
     @return a List of AccountDTO objects associated with the provided product_id.
     */


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "Account doesn't exist"),
    })

    @GetMapping("")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{uuid}")
    public AccountDTO getAccountById(@PathVariable String uuid) {
        return accountService.getAccountById(uuid);
    }

    @GetMapping("/active")
    public List<AccountDTO> getAllActiveAccounts() {
        return accountService.getAllActiveAccounts();
    }

    @GetMapping("/by_product/{productId}")
    public List<AccountDTO> getAccountsByProductId(@PathVariable String productId) {
        return accountService.getAccountsByProudctId(productId);
    }
}
