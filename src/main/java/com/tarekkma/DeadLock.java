package com.tarekkma;

public class DeadLock {
    public static void main(String[] args) {
        Object resource1 = new Object();
        Object resource2 = new Object();

        new DeadLockTask(resource1, resource2);
        new DeadLockTask(resource1, resource2);
    }

    public static class DeadLockTask implements Runnable {
        final Object resource1, resource2;
        final int id;
        private static int gId = 0;

        public DeadLockTask(Object resource1, Object resource2) {
            this.resource1 = resource1;
            this.resource2 = resource2;
            gId++;
            id = gId;
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            System.out.println("Thread #" + id + " is starting");
            while (true) {
                synchronized (resource1) {
                    System.out.println("Thread #" + id + " have lock on resource 1");
                    synchronized (resource2) {
                        System.out.println("Thread #" + id + " have lock on resource 2");
                    }
                    System.out.println("Thread #" + id + " released the lock on resource 2");
                }
                System.out.println("Thread #" + id + " released the lock on resource 1");
            }
        }
    }
}
