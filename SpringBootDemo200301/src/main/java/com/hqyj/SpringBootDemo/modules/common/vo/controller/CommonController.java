package com.hqyj.SpringBootDemo.modules.common.vo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@RequestMapping("/dashbord")
	public String dashbordPage(){
		return "index";
	}

}
