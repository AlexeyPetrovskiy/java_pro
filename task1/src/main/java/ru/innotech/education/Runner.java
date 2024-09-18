package ru.innotech.education;

import ru.innotech.education.annotation.*;

public class Runner {

    @BeforeSuite
    public static void first(){
        System.out.println("Запуск метода first");
    }

    @Test(priority = 8)
    public void second(){
        System.out.println("Запуск метода second");
    }

    @Test
    public void third(){
        System.out.println("Запуск метода third");
    }

    @Test(priority = 10)
    public void fourth(){
        System.out.println("Запуск метода fourth");
    }

    @AfterSuite
    public static void fifth(){
        System.out.println("Запуск метода fifth");
    }

    @BeforeTest
    public static void beforeTest(){
        System.out.println("Запуск метода beforeTest");
    }

    @AfterTest
    public static void afterTest(){
        System.out.println("Запуск метода afterTest");
    }
}
