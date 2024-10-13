package ru.innotech.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

/**
 * Тестовое задание номер 4-5
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(App.class, args);
    }
}
