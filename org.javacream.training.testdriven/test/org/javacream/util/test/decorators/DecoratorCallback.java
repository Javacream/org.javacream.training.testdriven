package org.javacream.util.test.decorators;

public interface DecoratorCallback {

	public void before(String methodName, Object[] args);
	public void returning(String methodName, Object[] args, Object result);
	public void throwing(String methodName, Object[] args, Throwable t);
	default Object[] prepareArgs(Object[] originalArgs) {
		return originalArgs;
	}
	default Object prepareResult(Object originalResult) {
		return originalResult;
	}
	default Throwable prepareThrowable(Throwable originalThrowable) {
		return originalThrowable;
	}
}
