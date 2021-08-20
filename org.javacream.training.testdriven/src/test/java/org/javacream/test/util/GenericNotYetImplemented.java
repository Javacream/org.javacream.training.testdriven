package org.javacream.test.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GenericNotYetImplemented  implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		throw new IllegalStateException("Not implemented");
	}

	public static <T> T createNotYetImplemented(Class<T> clazz) {
		GenericNotYetImplemented genericNotYetImplemented = new GenericNotYetImplemented();
		ClassLoader classLoader = GenericNotYetImplemented.class.getClassLoader();
		Class<?>[] interfaces = {clazz};
		return clazz.cast(Proxy.newProxyInstance(classLoader, interfaces, genericNotYetImplemented));
		
	}
}