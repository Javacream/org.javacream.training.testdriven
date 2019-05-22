package org.javacream.util.testdecorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TracingDecorator implements InvocationHandler {

	private Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if (args != null && args.length > 0) {
			System.out.println("entering " + methodName + ", params=" + Arrays.asList(args));
		} else {
			System.out.println("entering " + methodName  + ", no params");

		}
		try {
			Object result = method.invoke(delegate, args);
			System.out.println("returning from " + methodName + ", result=" + result);
			return result;
		} catch (Throwable e) {
			if (e instanceof InvocationTargetException) {
				e = ((InvocationTargetException) e).getTargetException();
			}
			System.out.println("throwing from " + methodName + ", exception=" + e);
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T createDecorator(T toDecorate) {
		TracingDecorator decorator = new TracingDecorator();
		decorator.setDelegate(toDecorate);
		ClassLoader cl = TracingDecorator.class.getClassLoader();
		Class<?>[] toImplement = toDecorate.getClass().getInterfaces();
		return (T) Proxy.newProxyInstance(cl, toImplement, decorator);
	}

}
