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

import model.Confronto;
import model.Grupo;
import model.GrupoResult;
import model.Jogo;
import model.Time;
import persistence.ConfrontoDao;
import persistence.GrupoDao;
import persistence.GrupoResultDao;
import persistence.JogoDao;

@WebServlet("/mata-mata")
public class QuartasController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String param = req.getParameter("data");

		List<Confronto> confrontos = new ArrayList<>();

		


		RequestDispatcher rd = req.getRequestDispatcher("quartas.jsp");

		try {
			ConfrontoDao confrontoDao = new ConfrontoDao();
			
			confrontos = confrontoDao.getQuartas();
			

		} catch (ClassNotFoundException | SQLException e) {

			req.setAttribute("database", "Erro ao acessar banco de dados");

		}  finally {
			req.setAttribute("confrontos", confrontos);

			rd.forward(req, res);
		}
	}
}