package ru.innotech.education;
import java.util.Iterator;
import java.util.LinkedList;

public class CustomThreadPool {

    private LinkedList<Runnable> listTask = new LinkedList<>();
    private final Worker[] workers;
    private boolean isShutdown;

    public CustomThreadPool(final int numOfThreads) {
        workers = new Worker[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    private synchronized Runnable getTask() {
        Iterator<Runnable> iterator = listTask.iterator();
            if (iterator.hasNext()){
                Runnable task = iterator.next();
                iterator.remove();
                return task;
             } else {
                return null;
            }
    }

    public synchronized void execute(final Runnable task) {
        if (isShutdown){
            throw new IllegalStateException();
        }
        listTask.add(task);
    }

    public void shutdown() {
        isShutdown = true;
        for (int i = 0; i < workers.length; i++) {
            workers[i].shutdownSignal = true;
        }
    }

    public boolean awaitTermination() {
        for (Thread thread:workers) {
            if (thread.isAlive()){
                return false;
            }
        }
        return true;
    }

    private class Worker extends Thread {
        private Runnable taskToPerform = null;
        boolean shutdownSignal = false;

        @Override
        public void run() {
            while(true && !shutdownSignal) {
                taskToPerform = getTask();
                if (taskToPerform != null) {
                    taskToPerform.run();
                }
                if(shutdownSignal) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
