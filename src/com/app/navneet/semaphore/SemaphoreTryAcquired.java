package com.app.navneet.semaphore;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreTryAcquired implements Runnable {
	private final String name;
	private final Semaphore semaphore;

	public SemaphoreTryAcquired(String name, Semaphore semaphore) {
		this.name = name;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			System.out.println("semaphore.drainPermits()"+semaphore.drainPermits());
			System.out.println(" availablePermits : " + this.semaphore.availablePermits());
			boolean acquire = this.semaphore.tryAcquire(1);
			
			System.out.println("Thread Acquired Lock available tryAcquire" + this.name + "---" + acquire);

			if (acquire == true) {
				System.out.println("Thread Acquired Lock available tryAcquire" + this.name + "---" + acquire);
				System.out.println("Thread Acquired Lock" + this.name);
				IntStream.range(0, 10).forEach(it -> {
					System.out.println("Start Processing Thread " + this.name);
				});
				System.out.println("Thread Release Lock " + this.name);
				System.out.println("hasQueuedThreads:  " + this.semaphore.hasQueuedThreads());
			} else {
				System.out.println("elsssssssssssssssssssssss");

				// Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

	}

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		Thread t = new Thread(new SemaphoreTryAcquired("Orange", semaphore), "Orange");
		Thread t2 = new Thread(new SemaphoreTryAcquired("Black", semaphore), "Orange");
		Thread t3 = new Thread(new SemaphoreTryAcquired("Red", semaphore), "Orange");

		Thread t1 = new Thread(new SemaphoreTryAcquired("Blue", semaphore), "Blue");
		t2.start();
		t3.start();
		t.start();
		t1.start();

	}

}
