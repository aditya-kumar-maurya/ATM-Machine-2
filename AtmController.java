package com.example.atm.controller;

import com.example.atm.dto.AmountRequest;
import com.example.atm.dto.PinRequest;
import com.example.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/atm/{accountId}")
public class AtmController {

    @Autowired
    private AtmService atmService;

    // POST http://localhost:8080/api/atm/1/login   body: { "pin": 1234 }
    @PostMapping("/login")
    public String login(@PathVariable Long accountId, @RequestBody PinRequest request) {
        boolean valid = atmService.checkPin(accountId, request.getPin());
        return valid ? "PIN correct, login successful" : "Wrong PIN, try again";
    }

    // GET http://localhost:8080/api/atm/1/balance
    @GetMapping("/balance")
    public String checkBalance(@PathVariable Long accountId) {
        float balance = atmService.checkBalance(accountId);
        return "Total Balance = " + balance;
    }

    // POST http://localhost:8080/api/atm/1/withdraw   body: { "amount": 500 }
    @PostMapping("/withdraw")
    public String withdraw(@PathVariable Long accountId, @RequestBody AmountRequest request) {
        return atmService.withdraw(accountId, request.getAmount());
    }

    // POST http://localhost:8080/api/atm/1/deposit   body: { "amount": 500 }
    @PostMapping("/deposit")
    public String deposit(@PathVariable Long accountId, @RequestBody AmountRequest request) {
        return atmService.deposit(accountId, request.getAmount());
    }
}