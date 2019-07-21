package com.app.navneet.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreWorker {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(10);
		Thread t = new Thread(new WorkerPool("Orange", semaphore), "Orange");
		Thread t2 = new Thread(new WorkerPool("Black", semaphore), "Orange");
		Thread t3 = new Thread(new WorkerPool("Red", semaphore), "Orange");

		Thread t1 = new Thread(new WorkerPool("Blue", semaphore), "Blue");
		t2.start();
		t3.start();
		t.start();
		t1.start();
		
		
	
	}

}
