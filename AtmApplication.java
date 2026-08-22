package com.example.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.atm.entity.Account;
import com.example.atm.repository.AccountRepository;

@SpringBootApplication
public class AtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);
    }

    // Ye ek starting/default account create kar deta hai jab app run hoti hai,
    // taaki testing ke liye database mein turant data mile
    @Bean
    CommandLineRunner initData(AccountRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Account acc = new Account();
                acc.setAccountHolder("Default User");
                acc.setPin(1234);
                acc.setBalance(20000f);
                repository.save(acc);
                System.out.println("Default account created with id=1, pin=1234, balance=20000");
            }
        };
    }
}
