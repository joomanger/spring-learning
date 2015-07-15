package com.isd;

public class WorkerBean {
	public void doSomeWork(int nOfTimes) {
		for (int i = 0; i < nOfTimes; i++) {
			work();
		}
	}

	private void work() {
		System.out.print("");
	}
}
