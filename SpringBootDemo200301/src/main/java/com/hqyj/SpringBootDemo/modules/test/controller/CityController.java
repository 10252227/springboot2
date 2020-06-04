package com.hqyj.SpringBootDemo.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;

@RestController  // = @Controller + @ResponseBody
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	/**
	 * 
	 * 127.0.0.1/api/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return cityService.getCitiesByCountryId(countryId);
	}
	
	/**
	 * 
	 * @param cityName
	 * @param localCityName
	 * 127.0.0.1/api/city?cityName=Wuhan&localCityName=222
	 */
	@RequestMapping("/city")
	public City getCityByName(@RequestParam String cityName, @RequestParam(required=false) String localCityName) {
		return cityService.getCityByName(cityName, localCityName);
	}
	
	/**
	 * 
	 * 127.0.0.1/api/cities?currentPage=1&pageSize=5&countryId=522
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCityByPage(@RequestParam int currentPage, 
			@RequestParam int pageSize, @RequestParam int countryId) {
		return cityService.getCityByPage(currentPage, pageSize, countryId);
	}
	
	/**
	 * 127.0.0.1/api/cities
	 */
	@PostMapping(value = "/cities", consumes = "application/json")
	public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
		return cityService.getCitiesBySearchVo(searchVo);
	}
}
