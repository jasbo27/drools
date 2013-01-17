package pl.boboli.engineer.test.feature;

import java.util.concurrent.CountDownLatch;

public class CritSection {

	public static void waitOnStart(CountDownLatch start, int ruleIndex)
			throws InterruptedException {
		//System.out.println("Rule " + ruleIndex + ": before start");
		start.countDown();
		start.await();
		//System.out.println("Rule " + ruleIndex + ": after start");
	}

	public static void waitOnStop(CountDownLatch stop, int ruleIndex)
			throws InterruptedException {
	//	System.out.println("Rule " + ruleIndex + ": before stop");
		stop.countDown();
		stop.await();
	//	System.out.println("Rule " + ruleIndex + ": after stop");
	}

	public static void sleep(Integer sleepTime) throws InterruptedException {
		if (sleepTime != null && sleepTime > 0) {
			//System.out.println("Thread " + Thread.currentThread().getName()	+ ": sleeping for" + sleepTime + "ms.");
			Thread.sleep(sleepTime);
			//System.out.println("Thread " + Thread.currentThread().getName()	+ ": waking up");
		}
		else {
		//System.out.println("Thread " + Thread.currentThread().getName()	+ ": no sleep.");
		return ;
		}
	}

}
