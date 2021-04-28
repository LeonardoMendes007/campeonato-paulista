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

import model.Jogo;
import persistence.JogoDao;

@WebServlet("/registrar")
public class ResultadoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String param = req.getParameter("data");

		List<Jogo> jogos = new ArrayList<>();

		RequestDispatcher rd = req.getRequestDispatcher("resultado.jsp");

		try {

			JogoDao jdao = new JogoDao();

			jogos = jdao.selectJogos(param);

			req.setAttribute("dataSemJogos", "Não há jogos para a data: " + param);
			req.setAttribute("class-warning", "alert-warning");
		} catch (ClassNotFoundException | SQLException e) {

			req.setAttribute("database", "Erro ao acessar banco de dados");
			req.setAttribute("temJogo", 1);
		} catch (ParseException e) {

			req.setAttribute("data",
					"Erro: verifique o formato de data no campo de pesquisa. O padrão aceita é \"dd/mm/yyyy\"");
			req.setAttribute("temJogo", 1);
		} finally {

			if (param != null && !param.isEmpty() && jogos.size() > 0) {
				req.setAttribute("jogos", jogos);
				req.setAttribute("temJogo", 0);
			}

			rd.forward(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("resultado.jsp");

		String param;
		String[] params;
		List<Jogo> resultJogos = new ArrayList<>();
		
		try {
			
		    JogoDao dao = new JogoDao();
			
			for (int i = 1; i < 9; i++) {
				String codigoA = req.getParameter("codigoA"+i);
				String codigoB = req.getParameter("codigoB"+i);
				String timeA = req.getParameter("timeA"+i);
				String timeB = req.getParameter("timeB"+i);
				String golsA = req.getParameter("golsA"+i);
				String golsB = req.getParameter("golsB"+i);
	            
				
	            Jogo j = new Jogo();
	            j.setCodigoTimeA(Integer.parseInt(codigoA));
	            j.setCodigoTimeB(Integer.parseInt(codigoB));
	            j.setGolsA(Integer.parseInt(golsA));
	            j.setGolsB(Integer.parseInt(golsB));
	            
	            resultJogos.add(j);
	           
			}
			
			for (Jogo jogo : resultJogos) {
				System.out.println(jogo);
				dao.updateJogo(jogo);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rd.forward(req, res);
		}

	}

}
