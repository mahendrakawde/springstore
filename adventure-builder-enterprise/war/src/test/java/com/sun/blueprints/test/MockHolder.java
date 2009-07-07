package com.sun.blueprints.test;

import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MockHolder {

	private static final Log logger = LogFactory.getLog(MockHolder.class);
	private static final ThreadLocal holder = new ThreadLocal();
	
	public static void setMock(Class key, Object mock) {
		Map registry = (Map) holder.get();
		if (registry == null) {
			logger.debug("Created Mock registry.");
			registry = new WeakHashMap();
			holder.set(registry);
		}
		registry.put(key, mock);
		if (logger.isDebugEnabled()) {
			logger.debug("Added '"+mock+"' to registry as '"+key+"'");
		}
		
	}
	
	public static <T> T getMock(Class<T> key) {
		T mock = null;
		Map registry = (Map) holder.get();
		if (registry != null) {
			mock = (T) registry.get(key);
		}
		return mock;		
	}
	
	public static void clear() {
		holder.set(null);
	}
	
}
