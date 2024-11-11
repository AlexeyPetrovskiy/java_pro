package ru.innotech.education.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_limit", schema = "file_transfer", catalog = "file_transfer")
public class UserLimit implements Serializable {

    @Id
    @Column(name = "id_user", nullable = false)
    private long id_user;
    @Column(name = "\"limit\"", nullable = false)
    private long limit;

    public UserLimit(long id_user, long limit) {
        this.id_user = id_user;
        this.limit = limit;
    }

    public UserLimit() {
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
