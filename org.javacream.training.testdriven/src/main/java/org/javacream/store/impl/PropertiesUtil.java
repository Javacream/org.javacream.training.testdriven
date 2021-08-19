package org.javacream.store.impl;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	public Properties getProperties(String name) {
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getResourceAsStream(name));
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}

	}
}
