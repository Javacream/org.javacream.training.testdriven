package org.javacream.training.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GenericDummy implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType == String.class) {
			return "HUGO";
		}
		if(returnType == Integer.class || returnType == int.class) {
			return 42;
		}
		if(returnType == Double.class || returnType == double.class) {
			return 47.11;
		}
		if(returnType == Boolean.class || returnType == boolean.class) {
			return true;
		}
		try {
			return returnType.newInstance();
		}
		catch(Exception e) {
			//Dann halt kein Default-Konstruktor...
		}
		//....
		return null;
	}

	public static <T> T createDummy(Class<T> clazz) {
		GenericDummy genericDummy = new GenericDummy();
		ClassLoader classLoader = GenericDummy.class.getClassLoader();
		Class<?>[] interfaces = {clazz};
		return clazz.cast(Proxy.newProxyInstance(classLoader, interfaces, genericDummy));
		
	}
}
