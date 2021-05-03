package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Confronto;
import model.Grupo;
import model.Time;

public class ConfrontoDao {
	private Connection c;

	public ConfrontoDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<Confronto> getQuartas() throws SQLException {

        String sqlGrupo = "select * from fn_QuartasDeFinal()";

        PreparedStatement ps = c.prepareStatement(sqlGrupo);

		ResultSet rs = ps.executeQuery();
        		
		List<Confronto> confrontos = new ArrayList<>();

		while (rs.next()) {
			
			Confronto c = new Confronto();
			c.setTimeA(rs.getString(1));
			c.setTimeB(rs.getString(2));

			confrontos.add(c);
		}
		ps.close();

		return confrontos;

	}
}
