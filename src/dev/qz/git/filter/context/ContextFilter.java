package dev.qz.git.filter.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import dev.qz.git.context.impl.ContextImpl;

/**
 * Servlet Filter implementation class ContextFilter
 */
public class ContextFilter implements Filter {

	private String requestKeyName = null;

	private String responseKeyName = null;

	private Map<String, Object> map = null;

	/**
	 * Default constructor.
	 */
	public ContextFilter() {
		// TODO Auto-generated constructor stub

		requestKeyName = "request";

		responseKeyName = "response";

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

		ContextImpl.removeMap(requestKeyName);

		ContextImpl.removeMap(responseKeyName);

		ContextImpl.clear();

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		ContextImpl.intiContextMap(map);

		ContextImpl.setThreadLocalMap(requestKeyName, request);

		ContextImpl.setThreadLocalMap(responseKeyName, response);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

		map = new HashMap<String, Object>();

	}

}
