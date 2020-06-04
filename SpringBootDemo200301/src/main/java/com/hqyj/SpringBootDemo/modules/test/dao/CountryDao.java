package com.hqyj.SpringBootDemo.modules.test.dao;

import java.util.List;


import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.hqyj.SpringBootDemo.modules.test.entity.Country;

@Mapper
public interface CountryDao {
	
	@Select("select * from m_country where country_id=#{countryId}")
	@Results(id="countryResults", value={
			@Result(column="country_id",property="countryId"),
			@Result(column="country_id",property="cities",javaType=List.class,
					many=@Many(select="com.hqyj.SpringBootDemo.modules.test.dao.CityDao.getCitiesByCountryId"))}
	)
	Country getCountryByCountryId(int countryId);
	
	@Select("select * from m_country where country_name=#{countryName}")
	@ResultMap(value="countryResults")
	Country getCountryByCountryName(String countryName);
	
	
}
