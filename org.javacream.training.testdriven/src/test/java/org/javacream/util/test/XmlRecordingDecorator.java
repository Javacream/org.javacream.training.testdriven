package org.javacream.util.test;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;


/**
 * Test decorator that records all method calls in a Invocation-List. The list
 * will be written to a specified file at shutdown using xml-serialization.
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainersawitzki@javacream.org
 * 
 */

public class XmlRecordingDecorator implements InvocationHandler {

	private Object delegate;
	private LinkedList<Invocation> invocations;

	public XmlRecordingDecorator(final String filename) {
		Runnable writer = new Runnable(){

			@Override
			public void run() {
				try{
					XMLEncoder encoder = new XMLEncoder(new FileOutputStream(filename));
					encoder.writeObject(invocations);
					encoder.close();
				}
				catch(Exception e){
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(new Thread(writer));
		invocations = new LinkedList<Invocation>();
	}

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}

	/**
	 * @param proxy
	 *            Das ist das eigentliche Dekorationsobjekt, wird in der Regel
	 *            nicht benutzt
	 * @param method
	 *            Die auszuführende Methode
	 * @param params
	 *            Die Methoden-Parameter
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] params)
			throws Throwable {
		Invocation invocation = Invocation.create(method, params);
//		invocation.setMethodName(method.getName());
//		invocation.setParams(params);
		try {
			Object result = method.invoke(delegate, params);
			invocation.setResult(result);
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException){
				t = ((InvocationTargetException)t).getTargetException();
			}
			invocation.setThrowable(t);
			throw t;
		}finally{
			invocations.add(invocation);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decorate(T toDecorate, String filename) {
		ClassLoader classLoader = XmlRecordingDecorator.class.getClassLoader();
		Class[] interfaces = toDecorate.getClass().getInterfaces();
		XmlRecordingDecorator decorator = new XmlRecordingDecorator(filename);
		decorator.setDelegate(toDecorate);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, decorator);

	}

}