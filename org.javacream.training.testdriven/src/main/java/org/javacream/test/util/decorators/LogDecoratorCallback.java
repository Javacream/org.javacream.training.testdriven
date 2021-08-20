package org.javacream.test.util.decorators;

public class LogDecoratorCallback implements DecoratorCallback {

	@Override
	public void before(String methodName, Object[] args) {
		System.out.println("will call " + methodName);
	}

	@Override
	public void returning(String methodName, Object[] args, Object result) {
		System.out.println("returning from " + methodName + ", result=" + result);
	}

	@Override
	public void throwing(String methodName, Object[] args, Throwable t) {
		System.out.println("throwing from " + methodName + ", throwable=" + t);
	}

}
