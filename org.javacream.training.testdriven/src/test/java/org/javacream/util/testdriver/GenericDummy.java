package org.javacream.util.testdriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * GenericDummy benutzt Java Reflection
 * 
 * @author Rainer Sawitzki
 *
 */
public class GenericDummy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType == String.class) {
			return "Hugo";
		} else if (returnType == Integer.class || returnType == int.class) {
			return 42;
		} else if (returnType == Double.class || returnType == double.class) {
			return 47.11;
		} else if (returnType == Boolean.class || returnType == boolean.class) {
			return true;
		} else {
			try {
				returnType.newInstance();
			} catch (Exception e) {
				// No () constructor
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createDummy(Class<T> interfaceToImplement) {
		GenericDummy dummy = new GenericDummy();
		ClassLoader cl = GenericDummy.class.getClassLoader();
		Class<?>[] toImplement = { interfaceToImplement };
		return (T) Proxy.newProxyInstance(cl, toImplement, dummy);
	}
}
