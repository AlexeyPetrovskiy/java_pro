package ru.innotech.education;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

/**
 * Тестовое задание номер 4
 */
@ComponentScan
public class App {

    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        UserService userService = context.getBean(UserService.class);

        userService.putUser(10, "user10");
        System.out.println(userService.getUser(10));
        userService.deleteUser(10);

        userService.getUsers().stream().forEach(System.out::println);
    }
}
