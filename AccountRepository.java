package com.example.atm.repository;

import com.example.atm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository extend karne se hume free mein save(), findById(), findAll(),
// delete() jaise methods mil jaate hain - inko implement karne ki zaroorat nahi.
// Spring Boot khud iska implementation banata hai (runtime pe).
public interface AccountRepository extends JpaRepository<Account, Long> {
}
