package ru.innotech.education.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "file_transfer", catalog = "file_transfer")
public class Product implements Serializable {

    @Id
    private long id;
    private long bankaccount;
    private long balance;
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(long bankaccount) {
        this.bankaccount = bankaccount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id == that.id && bankaccount == that.bankaccount && Objects.equals(balance, that.balance) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankaccount, balance, type);
    }
}
