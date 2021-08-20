package org.javacream.test.util.decorators;

public class PerformanceDecoratorCallback implements DecoratorCallback {

	private long start;
	@Override
	public void before(String methodName, Object[] args) {
		start = System.currentTimeMillis();
	}

	@Override
	public void returning(String methodName, Object[] args, Object result) {
		System.out.println("returning from " + methodName + " took " + (System.currentTimeMillis() - start) + "msec");
	}

	@Override
	public void throwing(String methodName, Object[] args, Throwable t) {
		System.out.println("throwing from " + methodName + " took " + (System.currentTimeMillis() - start) + "msec");
	}

}
