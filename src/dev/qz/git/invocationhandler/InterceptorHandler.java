package dev.qz.git.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import dev.qz.git.interceptor.conf.ConfFileParserInt;

public class InterceptorHandler implements InvocationHandler {

	private Map<String, Class<?>> InterceptorClass = null;

	private String beforeActionMethodNameString = null;

	private String afterActionMethodNameString = null;

	private Object object = null;

	public InterceptorHandler() {

		InterceptorClass = ConfFileParserInt.getInstance().LoadConf();

		beforeActionMethodNameString = "beforeAction";

		afterActionMethodNameString = "afterAction";

	}

	public void setObject(Object object) {

		this.object = object;

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub

		Object result = null;

		Vector<Object> objects = null;

		Iterator<Map.Entry<String, Class<?>>> iterator = InterceptorClass
				.entrySet().iterator();

		objects = new Vector<Object>();

		// before action intercepter method execute
		while (iterator.hasNext()) {

			Map.Entry<String, Class<?>> entry = iterator.next();

			objects.addElement(entry.getValue().newInstance());

		}

		for (Object interceptor : objects) {

			interceptor.getClass().getMethod(beforeActionMethodNameString)
					.invoke(interceptor);

		}

		result = method.invoke(object, args);

		// after action intercepter method execute
		for (Object interceptor : objects) {

			interceptor.getClass().getMethod(afterActionMethodNameString)
					.invoke(interceptor);

		}

		return result;
	}

}
