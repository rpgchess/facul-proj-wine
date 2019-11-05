package br.app.wine.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.app.wine.model.Vinho;
import br.app.wine.model.VinhoDAO;

public class Deletar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson json = new Gson();
		Vinho vinho = new Vinho();
		BufferedReader reader = request.getReader();
		vinho = json.fromJson(reader, Vinho.class);
		new VinhoDAO().delete(vinho.getId());
		response.setStatus(HttpServletResponse.SC_OK);
	}
}