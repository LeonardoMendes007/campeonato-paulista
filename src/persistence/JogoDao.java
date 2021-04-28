package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Jogo;

public class JogoDao {
	private Connection c;

	private final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public JogoDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<Jogo> gerarJogos() throws SQLException {

		CallableStatement cs;

		String sqlGeraJogos = "{CALL sp_insereJogosData}";

		cs = c.prepareCall(sqlGeraJogos);

		cs.execute();

		String sqlJogos;

		PreparedStatement ps;

		sqlJogos = "select (Select NomeTime from Times where CodigoTime = CodigoTimeA) As TimeA, CodigoTimeA, GolsTimeA ,(Select NomeTime from Times where CodigoTime = CodigoTimeB) As TimeB ,CodigoTimeB, GolsTimeA, Data_Jogo from Jogos ORDER BY Data_Jogo";
		ps = c.prepareStatement(sqlJogos);

		ResultSet rs = ps.executeQuery();

		List<Jogo> jogos = new ArrayList<>();

		while (rs.next()) {

			Jogo jogo = new Jogo();
			jogo.setTimeA(rs.getString(1));
			jogo.setCodigoTimeA(rs.getInt(2));
			jogo.setGolsA(rs.getInt(3));
			jogo.setTimeB(rs.getString(4));
			jogo.setCodigoTimeB(rs.getInt(5));
			jogo.setGolsB(rs.getInt(6));
			jogo.setData(df.format(rs.getDate(7)));
			jogos.add(jogo);
		}
		ps.close();

		return jogos;

	}

	public List<Jogo> selectJogos(String param) throws SQLException, ParseException {

		String sqlJogos;

		PreparedStatement ps;

		if (param == null || param.isEmpty()) {
			sqlJogos = "select (Select NomeTime from Times where CodigoTime = CodigoTimeA) As TimeA, CodigoTimeA, GolsTimeA ,(Select NomeTime from Times where CodigoTime = CodigoTimeB) As TimeB ,CodigoTimeB, GolsTimeB, Data_Jogo from Jogos ORDER BY Data_Jogo";
			ps = c.prepareStatement(sqlJogos);
		} else {
			Date data = df.parse(param);
			sqlJogos = "select (Select NomeTime from Times where CodigoTime = CodigoTimeA) As TimeA, CodigoTimeA, GolsTimeA ,(Select NomeTime from Times where CodigoTime = CodigoTimeB) As TimeB ,CodigoTimeB, GolsTimeB, Data_Jogo from Jogos Where Data_Jogo = ?";
			ps = c.prepareStatement(sqlJogos);
			ps.setDate(1, new java.sql.Date(data.getTime()));
		}

		ResultSet rs = ps.executeQuery();

		List<Jogo> jogos = new ArrayList<>();

		while (rs.next()) {

			Jogo jogo = new Jogo();
			jogo.setTimeA(rs.getString(1));
			jogo.setCodigoTimeA(rs.getInt(2));
			jogo.setGolsA(rs.getInt(3));
			jogo.setTimeB(rs.getString(4));
			jogo.setCodigoTimeB(rs.getInt(5));
			jogo.setGolsB(rs.getInt(6));
			jogo.setData(df.format(rs.getDate(7)));

			jogos.add(jogo);
		}
		ps.close();

		return jogos;

	}

	public int temJogo() throws SQLException, ParseException {

		String sqlJogos = "select COUNT(*) as jogos from Jogos";
		PreparedStatement ps = c.prepareStatement(sqlJogos);

		ResultSet rs = ps.executeQuery();

		rs.next();
		int jogos = rs.getInt(1);
		ps.close();

		return jogos;

	}
	
	public void updateJogo(Jogo jogo) throws SQLException {

		String sqlJogos;

		PreparedStatement ps;

		sqlJogos = "Update Jogos SET GolsTimeA = ?,GolsTimeB = ? WHERE CodigoTimeA = ? AND CodigoTimeB = ?";
		ps = c.prepareStatement(sqlJogos);
		ps.setInt(1, jogo.getGolsA());
		ps.setInt(2, jogo.getGolsB());
		ps.setInt(3, jogo.getCodigoTimeA());
		ps.setInt(4, jogo.getCodigoTimeB());

		

		ps.execute();
		
		ps.close();
		
		System.out.println("Jogo inserida com sucesso");

	}


}
