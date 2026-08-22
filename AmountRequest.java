package com.example.atm.dto;

// DTO = Data Transfer Object.
// Jab client (Postman/frontend) withdraw ya deposit ke liye request bhejega,
// wo JSON body mein amount bhejega jaise: { "amount": 500 }
// Ye class us JSON ko Java object mein convert karti hai.
public class AmountRequest {
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
