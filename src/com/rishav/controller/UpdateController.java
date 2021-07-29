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

public class UpdateController extends HttpServlet {
	
	private TextRepository repository;
	public static String UPDATE_URL = "content/update.jsp";
	int id;
	public UpdateController() {
		super();
		repository = new TextRepository();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("update get...");
		id = ShortenUrl.shortURLtoID(request.getParameter("id"));
		request.setAttribute("textid", id);
		TextModel model = repository.getContentById(id);
		request.setAttribute("title",model.getTitle());
		request.setAttribute("textContent",model.getText());
		request.setAttribute("password",model.getPassword());
		request.setAttribute("exposure",model.getExposure());
		request.setAttribute("expiration", getDateDiff(model.getEndDate(), model.getStartDate()));
		RequestDispatcher dispatcher = request.getRequestDispatcher(UPDATE_URL);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("update...");
		TextModel model = new TextModel();
		model.setId(id);
		
		model.setTitle(request.getParameter("title"));
		model.setText(request.getParameter("textContent"));
		model.setPassword(request.getParameter("password"));
		model.setExposure(request.getParameter("exposure"));
		
		LocalDate date =  LocalDate.now();
		model.setStartDate(Date.valueOf(date));
		model.setEndDate(Date.valueOf(
				date.plusDays(Long.parseLong(request.getParameter("expiration")
						))));
		
		//System.out.println(model.toString());
		int status = repository.update(model);
		if (status>0)
			out.print("Data saved successfully");
		else
			out.print("Update failed");
		RequestDispatcher dispatcher = request.getRequestDispatcher(UPDATE_URL);
		dispatcher.include(request, response);
	}

	private long getDateDiff(Date end, Date start) {
		long days = ((end.getTime()-start.getTime())/(1000*60*60*24))%365;
		return days;
	}
}
