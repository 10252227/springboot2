package com.hqyj.SpringBootDemo.modules.test.controller;

import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.modules.test.service.CountryService;
import com.hqyj.SpringBootDemo.modules.test.vo.applicationTest;

@Controller
@RequestMapping("/test")
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
	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/index")
	public String indexPage(ModelMap modelMap){
		int countryId = 522;
		List<City> cities = cityService.getCitiesByCountryId(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		Country country = countryService.getCountryByCountryId(countryId);
		
		modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 99);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("baiduUrl", "/test/log");
		modelMap.addAttribute("city", cities.get(0));
		modelMap.addAttribute("shopLogo", 
				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("cities", cities);
		modelMap.addAttribute("updateCityUri", "/api/city");
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
	
	/**
	 * 
	 * 127.0.0.1/test/log
	 */
	@RequestMapping("/log")
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
	 *127.0.0.1/test/config
	 */
	@RequestMapping("/config")
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
	 * 127.0.0.1/test/desc?key=fuck
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc(HttpServletRequest request, @RequestParam String key) {
		String key2 = request.getParameter("key");
		return "This is test module desc.112233" + key + "==" + key2;
	}
}
