package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Grupo;
import model.Time;

public class GrupoDao {
	private Connection c;

	public GrupoDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<Grupo> gerarGrupos() throws SQLException {

        String sqlGera = "{CALL sp_insereGrupoTimes}";

        CallableStatement cs = c.prepareCall(sqlGera);
        
        cs.execute();
        
        String sqlGrupo = "select p.Grupo, t.* from Times t, Grupos p where p.CodigoTime = t.CodigoTime";

        PreparedStatement ps = c.prepareStatement(sqlGrupo);

		ResultSet rs = ps.executeQuery();
        		
		List<Grupo> grupos = new ArrayList<>();
		
		String grupo = "";

		while (rs.next()) {
			
			grupo = rs.getString(1);

			Time time = new Time();
			time.setCodigo(rs.getInt(2));
			time.setNome(rs.getString(3));
			time.setCidade(rs.getString(4));
			time.setEstadio(rs.getString(5));

			Grupo g = new Grupo(grupo, time);

			grupos.add(g);
		}
		ps.close();

		return grupos;

	}
	
	public List<Grupo> selectGrupos() throws SQLException {

		String sqlGrupo = "select p.Grupo, t.* from Times t, Grupos p where p.CodigoTime = t.CodigoTime";

        PreparedStatement ps = c.prepareStatement(sqlGrupo);

		ResultSet rs = ps.executeQuery();
        		
		List<Grupo> grupos = new ArrayList<>();
		
		String grupo = "";

		while (rs.next()) {
			
			grupo = rs.getString(1);

			Time time = new Time();
			time.setCodigo(rs.getInt(2));
			time.setNome(rs.getString(3));
			time.setCidade(rs.getString(4));
			time.setEstadio(rs.getString(5));

			Grupo g = new Grupo(grupo, time);

			grupos.add(g);
		}
		ps.close();

		return grupos;

	}
}
