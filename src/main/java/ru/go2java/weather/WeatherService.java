package ru.go2java.weather;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherService {
	Logger log = LoggerFactory.getLogger(WeatherService.class);
	
	public String retriveForecast(String zip) throws Exception {
		log.info("Получаю данные для индекса{}", zip);
		
		Weather weather = new YahooXmlParser().parse(new YahooRetriver().retrieve(zip));
		
		log.info("Вывожу данные на экран");
		
		VelocityContext context = new VelocityContext();
		context.put("weather", weather);
		StringWriter writer = new StringWriter();
		
		Reader templateReader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("output.vm"));
		
		Velocity.evaluate(context, writer, "", templateReader);
		
		return writer.toString();
	}
}
