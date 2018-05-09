package com.vehicle.counter.process;

import java.util.concurrent.TimeUnit;

public class Listener implements Runnable {

	private boolean isAllowed = true;
	private int count = 0;
	private boolean isRunning = true;


	@Override
	public void run() {
		try {

			while (isRunning) {

			}


			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (Exception e) {

		}
	}


	public void close() {
		isRunning = false;
	}
}
