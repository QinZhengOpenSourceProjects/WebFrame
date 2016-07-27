package dev.qz.git.interceptor.abs;

/**
 * 
 * --------------------- interceptor interface ---------------------
 * 
 * @author Qin Zheng
 *
 */

public interface InterceptorAbs {

	public void beforeAction();

	public void afterAction();
	
	public void initInterceptor();

}
