package ru.innotech.education;

/**
 * Тестовое задание номер 3
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        final CustomThreadPool threadPool = new CustomThreadPool(10);
        for (int i = 0; i < 27; i++) {
            threadPool.execute(new Task());
        }
        Thread.sleep(15000);
        threadPool.execute(new Task());
        threadPool.shutdown();
        threadPool.awaitTermination();
    }
}
