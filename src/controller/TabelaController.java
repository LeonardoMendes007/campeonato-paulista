package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Grupo;
import model.GrupoResult;
import model.Jogo;
import model.Time;
import persistence.GrupoDao;
import persistence.JogoDao;
import persistence.JogoResultDao;
import persistence.TimeDao;

@WebServlet("/")
public class TabelaController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
		
		String error = "";
		
		List<GrupoResult> times = new ArrayList<>();
		
		List<Grupo> grupos = new ArrayList<>();
		
		List<Jogo> jogos = new ArrayList<>();
		
		try {
			
			JogoResultDao tDao = new JogoResultDao();
			
			times = tDao.getTabelaGeral();
			
			GrupoDao gDao = new GrupoDao();
			
			grupos = gDao.selectGrupos();
			
			JogoDao jDao = new JogoDao();
			
			jogos = jDao.selectJogos("");
			
			req.setAttribute("jogos", jogos);
			req.setAttribute("grupos", grupos);
			req.setAttribute("times", times);
			
		} catch (SQLException | ClassNotFoundException | ParseException e) {
			req.setAttribute("database", "Erro ao acessar banco de dados");
		} finally {
			rd.forward(req, res);
		}
		
		
		
		
		
	}

	
	
}
