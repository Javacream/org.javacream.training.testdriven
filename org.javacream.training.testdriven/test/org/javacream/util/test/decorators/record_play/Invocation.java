package org.javacream.util.test.decorators.record_play;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Simple container class to hold a method invocation.
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainersawitzki@javacream.org
 *
 */
public class Invocation implements Serializable{
	private static final long serialVersionUID = 1L;
	private String interfaceName;
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	private String methodName;
	private Object[] params;
	private Object result;
	private Throwable throwable;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result + Arrays.hashCode(params);
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result
				+ ((throwable == null) ? 0 : throwable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Invocation other = (Invocation) obj;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (!Arrays.equals(params, other.params))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (throwable == null) {
			if (other.throwable != null)
				return false;
		} else if (!throwable.equals(other.throwable))
			return false;
		return true;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
	public boolean compareInvocationParams(Invocation invocation){
		boolean equals = methodName.equals(invocation.methodName);
		if (equals){
			equals = Arrays.deepEquals(params, invocation.params);
		}
		return equals;
	}
	public boolean compareInvocationResult(Invocation invocation){
		if (throwable != null){
			return throwable.equals(invocation.throwable);
		}else{
			return result.equals(invocation.result);
		}
	}
	
	public static Invocation create(Method method, Object[] params){
		Invocation invocation = new Invocation();
		invocation.setMethodName(method.getName());
		invocation.setInterfaceName(method.getDeclaringClass().getName());
		invocation.setParams(params);
		return invocation;
}
}