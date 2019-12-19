package com.loyaltyone.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loyaltyone.ExceptionHandler.TemperatureException;
import com.loyaltyone.config.ConfigProperties;

@Service
public class Temperature {
	@Autowired Gson gson;
	@Autowired
	private ConfigProperties configProp;

	Logger logger = LoggerFactory.getLogger(Temperature.class);
	public String getTemperatureFromApi(String city) throws IOException
	{
		StringBuilder result =  new StringBuilder();
		String API_KEY = configProp.getKey();
		String location = city;
		String urlstring= "http://api.openweathermap.org/data/2.5/weather?q="+ location +"&appid=" + API_KEY + "&units ="+configProp.getMetric();
			
		    URL url = new URL(urlstring);	
		    logger.info("url is"+url);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = rd.readLine())!=null)
			{
				result.append(line);
			}
				rd.close();
			Map<String, Object> resMap = jsonToMap(result.toString());
			Map<String, Object> mainMap = jsonToMap(resMap.get("main").toString());
			logger.info("currentTemperature"+mainMap.get("temp").toString());
			String kelvin = mainMap.get("temp").toString();
			logger.info("kelvin"+kelvin);
			
		   return temperatureconversion(kelvin);
	}
	private  Map<String, Object> jsonToMap(String str)
	 {
		    Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		    return map;
		 
	 }
	private String temperatureconversion(String kelvin)
	{
		     String celsius="";
		     double d=Double.parseDouble(kelvin) - 273;
		     celsius = Double.toString(d);
		     logger.info("kkk"+celsius);
		     return celsius;
	}
}
