package dev.qz.git.action.proxy;

/**
 * 
 * ------------------------------------
 * proxy factory of user defined action
 * ------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * action proxy class
 * main function is return the proxy instance of target action
 * decrease the coupling of user defined component and framework
 * 
 */

import java.lang.reflect.Proxy;

import dev.qz.git.common.exception.KeyObjectNullException;
import dev.qz.git.invocationhandler.InterceptorHandler;

public class ActionProxy {

	// getProxy(Object object) create the proxy instance of param object using
	// handler
	public Object getProxy(Object object) {

		if (object != null) {

			InterceptorHandler interceptorHandler = new InterceptorHandler();

			interceptorHandler.setObject(object);

			return Proxy.newProxyInstance(object.getClass().getClassLoader(),
					object.getClass().getInterfaces(), interceptorHandler);

		} else {

			try {

				throw new KeyObjectNullException(
						"the proxy target can not be null");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}

			return null;

		}

	}

}
