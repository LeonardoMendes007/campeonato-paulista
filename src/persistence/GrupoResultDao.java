package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Grupo;
import model.GrupoResult;
import model.Time;

public class GrupoResultDao {

	private Connection c;

	public GrupoResultDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<GrupoResult> buscarGrupo(String grupo) throws SQLException {

		String sqlGrupo = "select * from fn_Grupos(?) ORDER BY pontos desc, vitorias desc, gols_marcados desc, saldo_gols desc";

		PreparedStatement ps = c.prepareStatement(sqlGrupo);
		ps.setString(1, grupo);

		ResultSet rs = ps.executeQuery();

		List<GrupoResult> grupos = new ArrayList<>();

		while (rs.next()) {

			GrupoResult g = new GrupoResult();
			g.setNome_time(rs.getString(1));
			g.setNum_jogos_disputados(rs.getInt(2));
			g.setVitorias(rs.getInt(3));
			g.setEmpates(rs.getInt(4));
			g.setDerrotas(rs.getInt(5));
			g.setGols_marcados(rs.getInt(6));
			g.setGols_sofridos(rs.getInt(7));
			g.setSaldo_gols(rs.getInt(8));
			g.setPontos(rs.getInt(9));

			grupos.add(g);
		}
		ps.close();

		return grupos;

	}

	public List<GrupoResult> buscarTodosOrdenados() throws SQLException {

		String sqlGrupo = "(select * from fn_Grupos('A') Union select * from fn_Grupos('B') Union select * from fn_Grupos('C') Union select * from fn_Grupos('D')) Order by pontos , vitorias , gols_marcados , saldo_gols OFFSET 0 ROWS FETCH NEXT 2 ROWS ONLY";

		PreparedStatement ps = c.prepareStatement(sqlGrupo);

		ResultSet rs = ps.executeQuery();

		List<GrupoResult> grupos = new ArrayList<>();

		while (rs.next()) {

			GrupoResult g = new GrupoResult();
			g.setNome_time(rs.getString(1));
			g.setNum_jogos_disputados(rs.getInt(2));
			g.setVitorias(rs.getInt(3));
			g.setEmpates(rs.getInt(4));
			g.setDerrotas(rs.getInt(5));
			g.setGols_marcados(rs.getInt(6));
			g.setGols_sofridos(rs.getInt(7));
			g.setSaldo_gols(rs.getInt(8));
			g.setPontos(rs.getInt(9));

			grupos.add(g);
		}
		ps.close();

		return grupos;

	}

}
