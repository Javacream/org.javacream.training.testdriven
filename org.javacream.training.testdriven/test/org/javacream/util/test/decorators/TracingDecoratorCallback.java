package org.javacream.util.test.decorators;

public class TracingDecoratorCallback implements DecoratorCallback{

	public void before(String methodName, Object[] args) {
		System.out.println("before " + methodName);
	}

	public void returning(String methodName, Object[] args, Object result) {
		System.out.println("returning from " + methodName);

	}

	public void throwing(String methodName, Object[] args, Throwable t) {
		System.out.println("throwing from " + methodName);

	}
}
