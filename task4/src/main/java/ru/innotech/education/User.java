package ru.innotech.education;

public class User {

    Long id;
    String username;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Id = " + id + " Username = " + username;
    }
}
