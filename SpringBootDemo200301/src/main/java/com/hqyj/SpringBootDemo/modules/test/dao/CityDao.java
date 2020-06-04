package com.hqyj.SpringBootDemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

@Mapper
public interface CityDao {
	
	List<City> getCitiesByCountryId(int countryId);
	
	@Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
	City getCityByName(@Param("cityName") String cityName, @Param("localCityName") String localCityName);
	
	@Select("<script>" + 
			"select * from m_city "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by city_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<City> getCitiesBySearchVo(SearchVo searchVo);
}
