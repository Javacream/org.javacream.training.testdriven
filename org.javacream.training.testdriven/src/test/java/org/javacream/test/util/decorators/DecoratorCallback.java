package org.javacream.test.util.decorators;

public interface DecoratorCallback {

	default void before(String methodName, Object[] args) {};
	default void returning(String methodName, Object[] args, Object result) {};
	default void throwing(String methodName, Object[] args, Throwable t) {};
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
