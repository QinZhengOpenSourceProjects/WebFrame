package dev.qz.git.context.impl;

/**
 * 
 * --------------------------------------
 * context variable for the whole request
 * --------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * using threadlocal to store a hashmap to save more data
 * including request object and response object 
 * 
 */

import java.util.Map;

public class ContextImpl {

	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public static void intiContextMap(Map<String, Object> map) {

		threadLocal.set(map);

	}

	public static void removeMap(String key) {

		threadLocal.get().remove(key);

	}

	public static void clear() {

		threadLocal.remove();
	}

	public static void setThreadLocalMap(String key, Object object) {

		threadLocal.get().put(key, object);

	}

	public static Object getThreadLocalMap(String key) {

		return threadLocal.get().get(key);

	}

}
