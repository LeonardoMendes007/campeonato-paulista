package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Time;

public class TimeDao {

	private Connection c;

	public TimeDao() throws ClassNotFoundException, SQLException {

		GenericDao dao = new GenericDao();
		c = dao.getConnection();
	}

	public List<Time> selectTimes() throws SQLException {

		String sql = "select * from Times";

		PreparedStatement ps = c.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Time> times = new ArrayList<>();

		while (rs.next()) {

			Time time = new Time();
			time.setCodigo(rs.getInt(1));
			time.setNome(rs.getString(2));
			time.setCidade(rs.getString(3));
			time.setEstadio(rs.getString(4));

			times.add(time);

		}
		
		ps.close();

		return times;

	}
	

}
