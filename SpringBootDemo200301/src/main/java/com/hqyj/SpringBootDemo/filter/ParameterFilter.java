package com.hqyj.SpringBootDemo.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

@WebFilter(filterName = "ParameterFilter", urlPatterns = "/**")
public class ParameterFilter implements Filter {

	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("ParameterFilter init() !!!!!!!!!!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if (StringUtils.isNotBlank(value) && value.contains("fuck")) {
					return value.replace("fuck", "***");
				}
				return super.getParameter(name);
			}
			
			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpRequest.getParameterValues(name);
				for (int i = 0; i < values.length; i++) {
					String temp = values[i];
					if (StringUtils.isNotBlank(temp) && temp.contains("fuck")) {
						values[i] = temp.replace("fuck", "***");
					}

				}
				return values;
			}

		};

		chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		LOGGER.debug("ParameterFilter destory() !!!!!!!!!!");
	}

}
