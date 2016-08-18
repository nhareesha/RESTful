package com.RESTful.Intigration.Interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BasicInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("After View rendering");

	}

	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("Before view is called");

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2) throws Exception {
		System.out.println("Before controller is called");
		System.out.println(req.getRequestURL());
		if("POST".equals(req.getMethod()))
			return true;
		else
			return false;
	}

}
