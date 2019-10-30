package org.javacream.util.test.decorators;

public abstract class BaseDecoratorCallback  implements DecoratorCallback{

	@Override
	public void before(String methodName, Object[] args) {
	}

	@Override
	public void returning(String methodName, Object[] args, Object result) {
	}

	@Override
	public void throwing(String methodName, Object[] args, Throwable t) {
	}

}
