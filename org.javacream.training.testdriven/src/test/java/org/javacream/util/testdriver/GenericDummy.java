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
	public static interface Defaults{
		String STRING = "Hugo";
		double DOUBLE = 47.11;
		int INTEGER = 42;
		boolean BOOLEAN = true;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType == String.class) {
			return Defaults.STRING;
		} else if (returnType == Integer.class || returnType == int.class) {
			return Defaults.INTEGER;
		} else if (returnType == Double.class || returnType == double.class) {
			return Defaults.DOUBLE;
		} else if (returnType == Boolean.class || returnType == boolean.class) {
			return Defaults.BOOLEAN;
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
