package ru.go2java.servlets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Простой сервлет, который выводит текст лог файла
 * @author Alexey G
 *
 */
public class LogServlet extends HttpServlet {

	private static final long serialVersionUID = -6770687948842456860L;

	/** Размер буффера для чтения/записи лог файла по умолчанию*/
	private static final int BUFF_SIZE = 1024;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// Создаем ссылку на файл myApp.log
		File logFile = new File("myApp.log");
		
		// Получаем writer для записи ответа
		PrintWriter writer = response.getWriter();
		
		// Если файл сушествует выводим его содержимое в поток
		if (logFile.exists()) {
			FileReader reader = new FileReader(logFile);
			
			int len = 0;
			char [] buff = new char[BUFF_SIZE];
			
			while ((len = reader.read(buff, 0, BUFF_SIZE)) != -1) {
				writer.write(buff, 0, len);
			}
			
			// Закрываем ридер файла
			reader.close();
		}
		
		// Закрываем ридер потока
		writer.close();
	}
}
