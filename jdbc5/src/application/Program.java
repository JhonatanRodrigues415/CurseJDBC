package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = DB.getConnection();
			ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE " 
					+ "Id = ?");
		
			ps.setInt(1, 5);
			
			int linesAffected = ps.executeUpdate();
			
			System.out.println("Done! Lines Affected: " + linesAffected);
		
		}
		catch(SQLException e) {
			throw new DbException("Error Integrity: " + e.getMessage());
		}
		finally {
			DB.StatementClose(ps);
			DB.closeConnection();
			
		}
	}

}
