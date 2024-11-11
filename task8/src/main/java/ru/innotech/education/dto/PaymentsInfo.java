package ru.innotech.education.dto;

public class PaymentsInfo {
    private long id_user;
    private Double amount;

    public PaymentsInfo(long id_user, double amount) {
        this.id_user = id_user;
        this.amount = amount;
    }

    public PaymentsInfo() {
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
