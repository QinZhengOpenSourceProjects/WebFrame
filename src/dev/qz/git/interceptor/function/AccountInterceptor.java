package dev.qz.git.interceptor.function;

import dev.qz.git.interceptor.abs.InterceptorAbs;

public class AccountInterceptor implements InterceptorAbs {

	public AccountInterceptor() {
		// TODO Auto-generated constructor stub

		initInterceptor();
	}

	@Override
	public void beforeAction() {
		// TODO Auto-generated method stub

		System.out.println("before");

	}

	@Override
	public void afterAction() {
		// TODO Auto-generated method stub

		System.out.println("after");

	}

	@Override
	public void initInterceptor() {
		// TODO Auto-generated method stub
		
		System.out.println("account interceptor init");

	}

}
