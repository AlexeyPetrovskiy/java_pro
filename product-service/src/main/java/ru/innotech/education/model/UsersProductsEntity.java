package ru.innotech.education.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_products", schema = "file_transfer", catalog = "file_transfer")
@IdClass(UsersProductsEntityPK.class)
public class UsersProductsEntity implements Serializable {

    private int idUser;
    private int idProduct;

    private Product productByIdProduct;

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

    @OneToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Product getProductByIdProduct() {
        return productByIdProduct;
    }


    public void setProductByIdProduct(Product productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}
