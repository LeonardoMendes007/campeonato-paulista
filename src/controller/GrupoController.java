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
import model.GrupoResult;
import model.Time;
import persistence.GrupoDao;
import persistence.GrupoResultDao;

@WebServlet("/grupos")
public class GrupoController extends HttpServlet {

	List<GrupoResult> todosGrupos = new ArrayList<>();
	List<Grupo> grupos = new ArrayList<>();
	
	
	List<GrupoResult> grupoA = new ArrayList<>();
	List<GrupoResult> grupoB = new ArrayList<>();
	List<GrupoResult> grupoC = new ArrayList<>();
	List<GrupoResult> grupoD = new ArrayList<>();
	List<GrupoResult> grA = new ArrayList<>();
	List<GrupoResult> grB = new ArrayList<>();
	List<GrupoResult> grC = new ArrayList<>();
	List<GrupoResult> grD = new ArrayList<>();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("grupos.jsp");
	
		try {
			
			


			GrupoDao grupoDao = new GrupoDao();
			GrupoResultDao grupoResultDao = new GrupoResultDao();

			grupos = grupoDao.selectGrupos();
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

		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("database", "Erro ao acessar banco de dados");
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

			rd.forward(req, res);
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		RequestDispatcher rd = req.getRequestDispatcher("grupos.jsp");
		try {

			GrupoDao dao = new GrupoDao();
			GrupoResultDao grupoResultDao = new GrupoResultDao();

			dao.gerarGrupos();
			grupos = dao.selectGrupos();
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
		} catch (ClassNotFoundException | SQLException e) {
			req.setAttribute("database", "Erro ao acessar banco de dados");
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

			rd.forward(req, res);
		}

	}

}