package org.javacream.util.test.decorators;

public class ProfilingDecoratorCallback extends BaseDecoratorCallback{
	private long start;
	public void before(String methodName, Object[] args) {
		start = System.currentTimeMillis();
	}

	public void returning(String methodName, Object[] args, Object result) {
		stopCounter(methodName);
	}

	public void throwing(String methodName, Object[] args, Throwable t) {
		stopCounter(methodName);
	}
	
	private void stopCounter(String methodName) {
		System.out.println("Calling " + methodName + " took " + (System.currentTimeMillis() - start) + "msec");
	}
}
