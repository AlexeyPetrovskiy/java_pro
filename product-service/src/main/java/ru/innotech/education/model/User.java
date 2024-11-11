package ru.innotech.education.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "file_transfer", catalog = "file_transfer")
public class User implements Serializable {

    private long id;

    private String username;

    private Set<UsersProductsEntity> usersProductsByIdUser;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Set<UsersProductsEntity> getUsersProductsByIdUser() {
        return usersProductsByIdUser;
    }

    public void setUsersProductsByIdUser(Set<UsersProductsEntity> usersProductsByIdUser) {
        this.usersProductsByIdUser = usersProductsByIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
