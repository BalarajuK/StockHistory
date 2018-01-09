package com.testjava.thread;


import java.util.concurrent.*;

public class TestTreadPool {

    public static void main(String[] args) throws InterruptedException {
       // testFixedThreadPool();
        testScheduledThreadPool();

        System.out.println("Finished all services");
    }

    private static void testScheduledThreadPool() throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        CountDownLatch wait = new CountDownLatch(10);
        service.scheduleWithFixedDelay(new WorkerThread("TestThread",wait), 500, 50, TimeUnit.MILLISECONDS);

        wait.await();
        service.shutdown();
        while (!service.isTerminated()){

        }
    }


    private static void testFixedThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        CountDownLatch wait = new CountDownLatch(4);
        for(int i=1;i<=4 ; i++){
            Runnable task = new WorkerThread("TestThread"+i, wait);
            service.execute(task);
        }

        wait.await();
        service.shutdown();
        while (!service.isTerminated()){

        }
    }

    private static class WorkerThread implements Runnable{
        private String name;
        private CountDownLatch notifyCompletion;

        public WorkerThread(String name, CountDownLatch notifyCompletion) {
            this.name = name;
            this.notifyCompletion = notifyCompletion;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\n"+name +" = " );
            for(int i=1;i<=10 ; i++){
                System.out.print(i+" ");
            }
            System.out.println();
            notifyCompletion.countDown();
        }
    }
}
