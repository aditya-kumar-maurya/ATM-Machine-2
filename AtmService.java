package com.example.atm.service;

import com.example.atm.entity.Account;
import com.example.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Service batata hai ye class business logic rakhti hai.
// Aapke purane ATM class ke methods (checkpin, withdrowmoney, depositmoney)
// yahan aa gaye hain, bas ab Scanner ki jagah database (repository) use hota hai.
@Service
public class AtmService {

    @Autowired
    private AccountRepository accountRepository;

    // PIN check karna
    public boolean checkPin(Long accountId, int enteredPin) {
        Optional<Account> accOpt = accountRepository.findById(accountId);
        if (accOpt.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        return accOpt.get().getPin() == enteredPin;
    }

    // Balance check karna
    public float checkBalance(Long accountId) {
        Account acc = getAccount(accountId);
        return acc.getBalance();
    }

    // Paisa withdraw karna
    public String withdraw(Long accountId, float amount) {
        Account acc = getAccount(accountId);

        if (amount <= 0) {
            return "Amount valid nahi hai";
        }
        if (amount > acc.getBalance()) {
            return "Insufficient Balance";
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepository.save(acc); // database mein update save hota hai
        return "Money withdrawn successfully. New balance: " + acc.getBalance();
    }

    // Paisa deposit karna
    public String deposit(Long accountId, float amount) {
        Account acc = getAccount(accountId);

        if (amount <= 0) {
            return "Amount valid nahi hai";
        }

        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);
        return "Money deposited successfully. New balance: " + acc.getBalance();
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
