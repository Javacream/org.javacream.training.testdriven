package org.javacream.util.test.decorators;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TracingDecorator implements InvocationHandler {

	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	public void before(String methodName, Object[] args) {
		System.out.println("before " + methodName);
	}

	public void returning(String methodName, Object[] args, Object result) {
		System.out.println("returning from " + methodName);

	}

	public void throwing(String methodName, Object[] args, Throwable t) {
		System.out.println("throwing from " + methodName);

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before(method.getName(), args);
		try {
			Object result = method.invoke(delegate, args);
			returning(method.getName(), args, result);
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException) t).getTargetException();
			}
			throwing(method.getName(), args, t);
			throw t;
		}
	}

	public static <T> T decorate(T delegate, Class<T> clazz) {
		TracingDecorator tracingDecorator = new TracingDecorator();
		tracingDecorator.setDelegate(delegate);
		ClassLoader classLoader = TracingDecorator.class.getClassLoader();
		Class<?>[] interfaces = { clazz };
		return clazz.cast(Proxy.newProxyInstance(classLoader, interfaces, tracingDecorator));

	}
}
