package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Grupo;
import model.Jogo;
import model.Time;
import persistence.GrupoDao;
import persistence.JogoDao;

@WebServlet("/jogos")
public class JogosController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String param = req.getParameter("data");

		List<Grupo> grupos = new ArrayList<>();

		List<Jogo> jogos = new ArrayList<>();

		RequestDispatcher rd = req.getRequestDispatcher("jogos.jsp");

		try {
			GrupoDao gdao = new GrupoDao();

			grupos = gdao.selectGrupos();

			JogoDao jdao = new JogoDao();

			jogos = jdao.selectJogos(param);

		} catch (ClassNotFoundException | SQLException e) {

			req.setAttribute("database",  "Erro ao acessar banco de dados");

		} catch (ParseException e) {

			req.setAttribute("data", "Erro: verifique o formato de data no campo de pesquisa. O padrão aceita é \"dd/mm/yyyy\"");
		} finally {
			

			req.setAttribute("grupoA", grupos.stream().filter(x -> x.getGrupo().trim().equals("A"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoB", grupos.stream().filter(x -> x.getGrupo().trim().equals("B"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoC", grupos.stream().filter(x -> x.getGrupo().trim().equals("C"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoD", grupos.stream().filter(x -> x.getGrupo().trim().equals("D"))
					.map(x -> x.getTime()).collect(Collectors.toList()));

			req.setAttribute("jogos", jogos);
			req.setAttribute("grupos", grupos);

			rd.forward(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Grupo> grupos = new ArrayList<>();

		List<Jogo> jogos = new ArrayList<>();

		RequestDispatcher rd = req.getRequestDispatcher("jogos.jsp");

		try {
			GrupoDao gdao = new GrupoDao();

			grupos = gdao.selectGrupos();

			JogoDao jdao = new JogoDao();

			jogos = jdao.gerarJogos();

			req.setAttribute("grupoA", grupos.stream().filter(x -> x.getGrupo().trim().equals("A"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoB", grupos.stream().filter(x -> x.getGrupo().trim().equals("B"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoC", grupos.stream().filter(x -> x.getGrupo().trim().equals("C"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("grupoD", grupos.stream().filter(x -> x.getGrupo().trim().equals("D"))
					.map(x -> x.getTime()).collect(Collectors.toList()));
			req.setAttribute("jogos", jogos);

		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("database", e.getMessage());
		} finally {

			rd.forward(req, res);
		}

	}

}
