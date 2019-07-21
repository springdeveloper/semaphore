package com.app.navneet.semaphore;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class WorkerPool implements Runnable {
	private final String name;
	private final Semaphore semaphore;

	public WorkerPool(String name, Semaphore semaphore) {
		this.name = name;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			System.out.println(" availablePermits : " + this.semaphore.availablePermits());
            if(name.equals("Blue")) {
    			semaphore.acquire(5);

            }else {
    			semaphore.acquire();
            }
			System.out.println("Thread Acquired Lock" + this.name);
			IntStream.range(0, 10).forEach(it -> {
				System.out.println("Start Processing Thread " + this.name);
			});
			System.out.println("Thread Release Lock " + this.name);
			System.out.println("hasQueuedThreads:  " + this.semaphore.hasQueuedThreads());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

	}

}
