package ru.innotech.education.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UsersProductsEntityPK implements Serializable {

    private int idUser;

    private int idProduct;

    @Id
    @Column(name = "id_user", nullable = true)
    public int getIdUser() {
        return idUser;
    }

    @Id
    @Column(name = "id_product", nullable = true)
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
