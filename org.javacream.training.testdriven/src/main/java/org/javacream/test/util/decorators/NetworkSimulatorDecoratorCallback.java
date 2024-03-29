package org.javacream.test.util.decorators;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class NetworkSimulatorDecoratorCallback implements DecoratorCallback{

	private long delay;
	
	
	@Override
	public Object[] prepareArgs(Object[] originalArgs) {
		return SerializationUtils.clone(originalArgs);
	}
	@Override
	public Object prepareResult(Object originalResult) {
		return SerializationUtils.clone((Serializable)originalResult);
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	@Override
	public void before(String methodName, Object[] args) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
