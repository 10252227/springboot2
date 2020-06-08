package com.hqyj.SpringBootDemo.modules.test.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;
import com.hqyj.SpringBootDemo.modules.common.vo.Result.ResultStatus;
import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.dao.CityDao;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.utils.RedisUtils;

@Service
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityDao cityDao;
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public City getCityByName(String cityName, String localCityName) {
		return cityDao.getCityByName(cityName, localCityName);
	}

	@Override
	public PageInfo<City> getCityByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage, pageSize);
		return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList()));
	}

	@Override
	public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<City>(
				Optional.ofNullable(cityDao.getCitiesBySearchVo(searchVo))
				.orElse(Collections.emptyList()));

	}

	@Override
	public Result<City> insertCity(City city) {
		city.setDateCreated(new Date());
		cityDao.insertCity(city);
		return new Result<>(ResultStatus.SUCCESS.status,"Insert SUCCESS",city);
	}

	@Override
	@Transactional
	public Result<City> updateCity(City city) {
		cityDao.updateCity(city);
	//	int a = 1/0;
		return new Result<City>(ResultStatus.SUCCESS.status,"update success",city);
	}

	@Override
	public Result<Object> deleteCity(int  cityId) {
		cityDao.deleteCity(cityId);
		return new Result<Object>(ResultStatus.SUCCESS.status,"delete success");
	}

	@Override
	public Object migrateCitiesByCountryId(int countryId) {
		List<City> cities = getCitiesByCountryId(countryId);
		redisUtils.set("cities", cities);
		return redisUtils.get("cities");
	}

	
}
