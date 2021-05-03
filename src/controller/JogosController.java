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
import model.GrupoResult;
import model.Jogo;
import model.Time;
import persistence.GrupoDao;
import persistence.GrupoResultDao;
import persistence.JogoDao;

@WebServlet("/jogos")
public class JogosController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String param = req.getParameter("data");

		List<Grupo> grupos = new ArrayList<>();

		List<Jogo> jogos = new ArrayList<>();
		List<GrupoResult> todosGrupos = new ArrayList<>();
		List<GrupoResult> grupoA = new ArrayList<>();
		List<GrupoResult> grupoB = new ArrayList<>();
		List<GrupoResult> grupoC = new ArrayList<>();
		List<GrupoResult> grupoD = new ArrayList<>();
		List<GrupoResult> grA = new ArrayList<>();
		List<GrupoResult> grB = new ArrayList<>();
		List<GrupoResult> grC = new ArrayList<>();
		List<GrupoResult> grD = new ArrayList<>();
		
		RequestDispatcher rd = req.getRequestDispatcher("jogos.jsp");

		try {
			GrupoDao gdao = new GrupoDao();

			grupos = gdao.selectGrupos();

			JogoDao jdao = new JogoDao();

			int temJogo = jdao.temJogo();

			req.setAttribute("temJogo", temJogo);

			jogos = jdao.selectJogos(param);

			GrupoResultDao grupoResultDao = new GrupoResultDao();
			
			grupos = gdao.selectGrupos();
			todosGrupos = grupoResultDao.buscarTodosOrdenados();


			grupoA = grupoResultDao.buscarGrupo("A");
			grupoB = grupoResultDao.buscarGrupo("B");
			grupoC = grupoResultDao.buscarGrupo("C");
			grupoD = grupoResultDao.buscarGrupo("D");
			
			for (GrupoResult gr : todosGrupos) {

				grupoA.stream().filter(x -> gr.getNome_time().equals(x.getNome_time())).filter(x -> {
					grA.add(gr);
					return true;
				}).collect(Collectors.toList());

				grupoB.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grB.add(gr);
					return true;
				}).collect(Collectors.toList());
				
			    grupoC.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grC.add(gr);
					return true;
				}).collect(Collectors.toList());
				
				grupoD.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grD.add(gr);
					return true;
				}).collect(Collectors.toList());
				
				
				
				grupoA = grupoA.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());

				grupoB = grupoB.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
				grupoC = grupoC.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
				grupoD = grupoD.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
			
			}

			req.setAttribute("dataSemJogos", "Não há jogos para a data: " + param);
			req.setAttribute("class-warning", "alert-warning");
		} catch (ClassNotFoundException | SQLException e) {

			req.setAttribute("database", "Erro ao acessar banco de dados");

		} catch (ParseException e) {

			req.setAttribute("data",
					"Erro: verifique o formato de data no campo de pesquisa. O padrão aceita é \"dd/mm/yyyy\"");
		} finally {
			req.setAttribute("grupos", grupos);
			req.setAttribute("grupoA", grupoA);
			req.setAttribute("grupoB", grupoB);
			req.setAttribute("grupoC", grupoC);
			req.setAttribute("grupoD", grupoD);
			req.setAttribute("grA", grA);
			req.setAttribute("grB", grB);
			req.setAttribute("grC", grC);
			req.setAttribute("grD", grD);

			req.setAttribute("jogos", jogos);

			rd.forward(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<Grupo> grupos = new ArrayList<>();

		List<Jogo> jogos = new ArrayList<>();
		List<GrupoResult> todosGrupos = new ArrayList<>();

		List<GrupoResult> grupoA = new ArrayList<>();
		List<GrupoResult> grupoB = new ArrayList<>();
		List<GrupoResult> grupoC = new ArrayList<>();
		List<GrupoResult> grupoD = new ArrayList<>();
		List<GrupoResult> grA = new ArrayList<>();
		List<GrupoResult> grB = new ArrayList<>();
		List<GrupoResult> grC = new ArrayList<>();
		List<GrupoResult> grD = new ArrayList<>();

		RequestDispatcher rd = req.getRequestDispatcher("jogos.jsp");

		try {
			GrupoDao gdao = new GrupoDao();

			grupos = gdao.selectGrupos();

			GrupoResultDao grupoResultDao = new GrupoResultDao();

			JogoDao jdao = new JogoDao();

			jogos = jdao.gerarJogos();

			int temJogo = jdao.temJogo();

			grupoResultDao = new GrupoResultDao();

			grupos = gdao.selectGrupos();
			todosGrupos = grupoResultDao.buscarTodosOrdenados();

			grupoA = grupoResultDao.buscarGrupo("A");
			grupoB = grupoResultDao.buscarGrupo("B");
			grupoC = grupoResultDao.buscarGrupo("C");
			grupoD = grupoResultDao.buscarGrupo("D");
			
			for (GrupoResult gr : todosGrupos) {

				grupoA.stream().filter(x -> gr.getNome_time().equals(x.getNome_time())).filter(x -> {
					grA.add(gr);
					return true;
				}).collect(Collectors.toList());

				grupoB.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grB.add(gr);
					return true;
				}).collect(Collectors.toList());
				
			    grupoC.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grC.add(gr);
					return true;
				}).collect(Collectors.toList());
				
				grupoD.stream().filter(x -> (gr.getNome_time().equals(x.getNome_time()))).filter(x -> {
					grD.add(gr);
					return true;
				}).collect(Collectors.toList());
				
				
				
				grupoA = grupoA.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());

				grupoB = grupoB.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
				grupoC = grupoC.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
				grupoD = grupoD.stream().filter(x -> !(gr.getNome_time().equals(x.getNome_time()))).collect(Collectors.toList());
				
			
			}

			req.setAttribute("temJogo", temJogo);
			req.setAttribute("grupos", grupos);
			req.setAttribute("grupoA", grupoA);
			req.setAttribute("grupoB", grupoB);
			req.setAttribute("grupoC", grupoC);
			req.setAttribute("grupoD", grupoD);
			req.setAttribute("grA", grA);
			req.setAttribute("grB", grB);
			req.setAttribute("grC", grC);
			req.setAttribute("grD", grD);
			req.setAttribute("jogos", jogos);

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
			req.setAttribute("database", "Erro ao acessar banco de dados");
		} finally {
			rd.forward(req, res);
		}

	}

}
