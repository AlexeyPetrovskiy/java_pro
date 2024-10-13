package ru.innotech.education.product;

public class Product {

    private Long id;
    private Long bankAccount;
    private Double balance;
    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getBankAccount() {
        return bankAccount;
    }

    public Double getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }
}
