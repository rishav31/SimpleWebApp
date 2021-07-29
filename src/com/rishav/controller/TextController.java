package com.rishav.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rishav.model.TextModel;
import com.rishav.repository.TextRepository;
import com.rishav.util.ShortenUrl;

public class TextController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String TEXT_URL = "content/index.jsp";
	public static String UPDATE_URL = "content/update.jsp";
	private TextRepository repository;

	public TextController() {
		super();
		repository = new TextRepository();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(TEXT_URL);
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		TextModel model = new TextModel();
		model.setTitle(request.getParameter("title"));
		model.setText(request.getParameter("textContent"));
		model.setPassword(request.getParameter("password"));
		model.setExposure(request.getParameter("exposure"));
		
		LocalDate date =  LocalDate.now();
		model.setStartDate(Date.valueOf(date));
		model.setEndDate(Date.valueOf(
				date.plusDays(Long.parseLong(request.getParameter("expiration")
						))));
		
		TextModel savedModel = repository.save(model);
		String url = "update?id="+ShortenUrl.idToShortURL(savedModel.getId());
		//out.print("Data saved successfully");
		request.setAttribute("message", url);
		request.setAttribute("url", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher(TEXT_URL);
		dispatcher.include(request, response);
	}

}
