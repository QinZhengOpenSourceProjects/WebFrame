package dev.qz.git.filter.dispatcher;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import dev.qz.git.action.abs.ActionAbs;
import dev.qz.git.action.proxy.ActionProxy;
import dev.qz.git.dispatcher.conf.ConfFileParser;
import dev.qz.git.dispatcher.entity.DispatcherEntity;

/**
 * Servlet Filter implementation class ActionDispatcher
 */
public class ActionDispatcher implements Filter {

	private Map<String, DispatcherEntity> dispatcherEntities = null;

	private DispatcherEntity dispatcherEntity = null;

	/**
	 * Default constructor.
	 */
	public ActionDispatcher() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		dispatcherEntity = dispatcherEntities.get(((HttpServletRequest) request).getServletPath());

		try {

			ActionAbs proxy = (ActionAbs) new ActionProxy().getProxy(dispatcherEntity.getActionClass().newInstance());

			httpServletRequest.getRequestDispatcher(dispatcherEntity.getViewMap().get((String) proxy.Execute()))
					.forward(request, response);

		} catch (IllegalArgumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * pass the request along the filter chain but this is the last part of
		 * the chain chain.doFilter(request, response);
		 */

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

		dispatcherEntities = ConfFileParser.getInstance().ParseConfFile();

	}

}
