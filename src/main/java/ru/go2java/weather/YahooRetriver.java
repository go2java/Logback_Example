package ru.go2java.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YahooRetriver {
	Logger log = LoggerFactory.getLogger(YahooRetriver.class);
	
	static private final String requestUrl = "http://weather.yahooapis.com/forecastrss?p="; 
	
	public InputStream retrieve(String zipcode) throws Exception {
		String url = requestUrl + zipcode;
		
        log.info( "Получаю данные из {}", url);
        URLConnection conn = new URL(url).openConnection();
        
        return conn.getInputStream();
	}
}
