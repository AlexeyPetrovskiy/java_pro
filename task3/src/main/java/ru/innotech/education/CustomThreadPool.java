package ru.innotech.education;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool {

    private LinkedList<Runnable> listTask = new LinkedList<>();
    private final Worker[] workers;
    private AtomicBoolean isShutdown = new AtomicBoolean();

    public CustomThreadPool(final int numOfThreads) {
        workers = new Worker[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    private synchronized Runnable getTask() throws InterruptedException {
        Runnable task = null;
        synchronized (listTask) {
            try {
                task = listTask.getFirst();
                listTask.removeFirst();
            } catch (NoSuchElementException e) {
                //ловим ошибку если нет задач на исполнение
                if (!isShutdown.get()) {
                    //если мы еще продолжаем работу, не был вызван shutdown, то ждем
                    listTask.wait();
                    //как только дождались, берем задачу в работу
                    task = listTask.getFirst();
                    listTask.removeFirst();
                }
            }
        }
        return task;
    }

    public void execute(final Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException();
        }
        synchronized (listTask) {
            listTask.add(task);
            //добавили таску, сообщаем ожидаемумому потоку то можно взять в работу
            listTask.notify();
        }
    }

    public void shutdown() {
        isShutdown.set(true);

        for (int i = 0; i < workers.length; i++) {
            workers[i].shutdownSignal = true;
        }
        synchronized (listTask) {
            //чтобы потоки не висели в ожидании после того как вызвали shutdown,
            // сообщаем что нужно продолжить
            listTask.notify();
        }
    }

    public boolean awaitTermination() throws InterruptedException {
        for (Thread thread : workers) {
            //дожидаемся исполнения задач потоками
            thread.join();
        }
        return true;
    }

    private class Worker extends Thread {
        private Runnable taskToPerform = null;
        boolean shutdownSignal = false;

        @Override
        public void run() {
            while (true && !shutdownSignal) {
                try {
                    taskToPerform = getTask();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //если за время ожидания прилетел сигнал о завершении, то приостанавливаем поток
                if (shutdownSignal) {
                    Thread.currentThread().interrupt();
                } else {
                    taskToPerform.run();
                }
            }
        }
    }
}
