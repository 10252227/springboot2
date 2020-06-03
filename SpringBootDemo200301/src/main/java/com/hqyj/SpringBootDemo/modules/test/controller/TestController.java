package com.hqyj.SpringBootDemo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBootDemo.modules.test.vo.applicationTest;

@Controller
public class TestController {
	
	@Value("${com.test.name}")
	private String name;
	@Value("${com.test.age}")
	private int  age;
	@Value("${com.test.desc}")
	private String desc;
	@Value("${com.test.random}")
	private String random;
	
	@Autowired
	private applicationTest applicationTest;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 
	 * 127.0.0.1:8080/test/log
	 */
	@RequestMapping("/test/log")
	@ResponseBody
	public String logTest(){
		// TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace("this is trace log");
		LOGGER.debug("this is debug log");
		LOGGER.info("this is info log");
		LOGGER.warn("this is warn log");
		LOGGER.error("this is error log");
		return "This is log test";
	}
	
	/**
	 * 
	 *127.0.0.1:8080/test/config
	 */
	@RequestMapping("/test/config")
	@ResponseBody
	public String configInfo(){
		StringBuffer sb = new StringBuffer();
		sb
		.append(name).append("==========")
		.append(age).append("==========")
		.append(desc).append("==========")
		.append(random).append("==========").append("</br>");
		
		sb
		.append(applicationTest.getName()).append("----------")
		.append(applicationTest.getAge()).append("----------")
		.append(applicationTest.getDesc()).append("----------")
		.append(applicationTest.getRandom()).append("----------");
		return sb.toString();
	}
	
	/**
	 * 
	 *127.0.0.1:8080/test/desc
	 */
	@RequestMapping("/test/desc")
	@ResponseBody    
	public String testDesc(){
		return "this is test modules desc.";
	}
}
