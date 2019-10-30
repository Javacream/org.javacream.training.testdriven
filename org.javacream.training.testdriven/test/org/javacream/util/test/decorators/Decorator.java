package org.javacream.util.test.decorators;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class Decorator implements InvocationHandler {

	private Object delegate;
	private DecoratorCallback decoratorCallback;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		decoratorCallback.before(method.getName(), args);
		args = decoratorCallback.prepareArgs(args);
		try {
			Object result = method.invoke(delegate, args);
			decoratorCallback.returning(method.getName(), args, result);
			result = decoratorCallback.prepareResult(result);
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException) t).getTargetException();
			}
			decoratorCallback.throwing(method.getName(), args, t);
			t = decoratorCallback.prepareThrowable(t);
			throw t;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T decorate(T delegate, DecoratorCallback decoratorCallback) {
		Decorator decorator = new Decorator();
		decorator.setDelegate(delegate);
		decorator.setDecoratorCallback(decoratorCallback);
		ClassLoader classLoader = Decorator.class.getClassLoader();
		Class<?>[] interfaces = delegate.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

	public void setDecoratorCallback(DecoratorCallback decoratorCallback) {
		this.decoratorCallback = decoratorCallback;
	}
}
