package ru.innotech.education;

/**
 * Тестовое задание номер 3
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        final CustomThreadPool threadPool = new CustomThreadPool(4);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Task());
        }
        Thread.sleep(1*15000);
        threadPool.shutdown();
        Thread.sleep(2000);
        System.out.println(threadPool.awaitTermination());
        threadPool.execute(new Task());
    }
}
