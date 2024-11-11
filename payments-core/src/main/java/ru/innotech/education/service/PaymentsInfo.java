package ru.innotech.education.service;

public class PaymentsInfo {
    private long clientId;
    private long productId;
    private long amount;

    public PaymentsInfo(Long clientId, Long productId, Long amount) {
        this.clientId = clientId;
        this.productId = productId;
        this.amount = amount;
    }

    public PaymentsInfo() {
    }

    public long getClientId() {
        return clientId;
    }

    public long getProductId() {
        return productId;
    }

    public long getAmount() {
        return amount;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
