package org.javacream.util.test.decorators;

public interface DecoratorCallback {

	public void before(String methodName, Object[] args);
	public void returning(String methodName, Object[] args, Object result);
	public void throwing(String methodName, Object[] args, Throwable t);
}
