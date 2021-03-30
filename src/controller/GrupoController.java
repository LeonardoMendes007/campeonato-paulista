package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Grupo;
import model.Time;
import persistence.GrupoDao;

@WebServlet("/grupos")
public class GrupoController extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Grupo> grupos = new ArrayList<>();

		RequestDispatcher rd = req.getRequestDispatcher("grupos.jsp");
		try {

			GrupoDao dao = new GrupoDao();

			grupos = dao.selectGrupos();
			
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("database", "Erro ao acessar banco de dados");
		} finally {
			req.setAttribute("grupos", grupos);
			req.setAttribute("grupoA", grupos.stream().filter(x -> x.getGrupo().trim().equals("A")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoB", grupos.stream().filter(x -> x.getGrupo().trim().equals("B")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoC", grupos.stream().filter(x -> x.getGrupo().trim().equals("C")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoD", grupos.stream().filter(x -> x.getGrupo().trim().equals("D")).map(x -> x.getTime()).collect(Collectors.toList()));
	
			rd.forward(req, res);
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Grupo> grupos = new ArrayList<>();
		
		RequestDispatcher rd = req.getRequestDispatcher("grupos.jsp");
		try {

			GrupoDao dao = new GrupoDao();

			grupos = dao.gerarGrupos();

		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("database", "Erro ao acessar banco de dados");
		} finally {
			
			req.setAttribute("grupoA", grupos.stream().filter(x -> x.getGrupo().trim().equals("A")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoB", grupos.stream().filter(x -> x.getGrupo().trim().equals("B")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoC", grupos.stream().filter(x -> x.getGrupo().trim().equals("C")).map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoD", grupos.stream().filter(x -> x.getGrupo().trim().equals("D")).map(x -> x.getTime()).collect(Collectors.toList()));

			rd.forward(req, res);
		}

	}

}