package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Confronto;
import model.GrupoResult;

public class JogoResultDao {
	private Connection c;

	public JogoResultDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<GrupoResult> getTabelaGeral() throws SQLException {

        String sqlGrupo = "select * from fn_Campeonato() ORDER BY pontos desc, vitorias desc, gols_marcados desc, saldo_gols desc";

        PreparedStatement ps = c.prepareStatement(sqlGrupo);

		ResultSet rs = ps.executeQuery();

		List<GrupoResult> times = new ArrayList<>();

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

			times.add(g);
		}
		ps.close();

		return times;

	}
}
