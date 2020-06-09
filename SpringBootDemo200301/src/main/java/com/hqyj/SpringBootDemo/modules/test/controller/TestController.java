package com.hqyj.SpringBootDemo.modules.test.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.SpringBootDemo.config.ResourceConfigBean;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.modules.test.service.CountryService;
import com.hqyj.SpringBootDemo.modules.test.vo.applicationTest;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
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
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	/**
	 * 127.0.0.1/test/index
	 */
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
		modelMap.addAttribute("shopLogo", "/upload/1111.png");
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("cities", cities);
		modelMap.addAttribute("updateCityUri", "/api/city");
//		modelMap.addAttribute("template", "test/index");
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
	
	/**
	 * 文件上传
	 */
	@PostMapping(value="/file",consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file,RedirectAttributes redirectAttributes){
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","plase select file");
			return "redirect:/test/index";
		}
		String resourcePath = resourceConfigBean.getResourcePath()+file.getOriginalFilename();
		try {
			File destFile = new File(ResourceUtils.getURL(resourcePath).getPath());
			file.transferTo(destFile);
		} catch (IllegalStateException  | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message","Upload fail");
			return "redirect:/test/index";
		}
		redirectAttributes.addFlashAttribute("resourcePath", resourcePath);
		redirectAttributes.addFlashAttribute("message","Upload success");
		return "redirect:/test/index";
	}
	
	@PostMapping(value="/files" , consumes="multipart/form-data") 
	public String uploadFiles(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes){
		boolean isEmpty = true;
		for(MultipartFile file : files){
			if (file.isEmpty()) {
				continue;
			}
			
			try {
				String destFilePath = "D:\\upload" + File.separator + file.getOriginalFilename();
				File destFile = new File(destFilePath);
				file.transferTo(destFile);
				isEmpty = false;
			} catch (IllegalStateException |IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message","UPload fail");
				return "redirect:/test/index";
			}
		}
		
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message","Please select file");
		}else {
			redirectAttributes.addFlashAttribute("message","upload success");
		}
		return "redirect:/test/index";
	}
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam String fileName) {

		try {
			String resourcePath = resourceConfigBean.getResourcePath() + fileName;
			Resource resource = new UrlResource(ResourceUtils.getURL(resourcePath));

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
					.body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
