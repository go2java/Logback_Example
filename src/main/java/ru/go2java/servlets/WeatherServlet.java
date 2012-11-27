package ru.go2java.servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ru.go2java.weather.WeatherService;

/**
 * Простой сервлет, который получает на вход GET запрос с параметром zipcode. 
 * Параметр должен содержать индекс города (напоимер 60202)
 * Далее сервлет использует класс {@link ru.go2java.weather.WeatherService} для получения и отображения погоды 
 * @author Alexey G
 *
 */
public class WeatherServlet extends HttpServlet {

	/**
	 * Просто UID для сериализации.
	 */
	private static final long serialVersionUID = 8701070637459096473L;
	
	/**
	* Объявление логера
	*/
	private Logger log = LoggerFactory.getLogger(WeatherService.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		 
		// Получаем параметр zipcode из запроса
		String zipcode = req.getParameter("zipcode");		
		
		Writer writer = response.getWriter();

		if (zipcode == null || zipcode.length() < 4) {
			writer.write("Введите индекс");
			log.warn("Неверный индекс {}", zipcode);
		} else {
			// Кладем индекс в MDC что бы в дальнейшем следить за обработкой этого запроса
			MDC.put("ZIPCODE", zipcode);
			
			log.info("Запускаю сервис");
			WeatherService weatherService = new WeatherService();
			
			try {				
				writer.write(weatherService.retriveForecast(zipcode));
			} catch (Exception e) {
				log.error("Ошибка вывода погоды!", e);
			} finally {
				// На всякий случай почистим MDC			
				log.info("Готово!");
				MDC.clear();
			}
			
		}
		
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
