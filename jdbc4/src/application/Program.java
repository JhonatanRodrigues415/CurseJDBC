package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = DB.getConnection();
			ps = conn.prepareStatement("UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ?"
					+ "WHERE (DepartmentId = ?)");
			
			ps.setDouble(1, 200.0);
			ps.setInt(2, 2);
			
			
			int linesAffected = ps.executeUpdate();
			
			System.out.println("Done! Lines Affected: " + linesAffected);
			
			ps = conn.prepareStatement("SELECT * FROM seller WHERE DepartmentId = ?");
			
			ps.setInt(1, 2);
			
			rs = ps.executeQuery();
			System.out.println("Sellers with updated salaries:");
			while(rs.next()) {
			System.out.println("Name: " + rs.getString("Name") + " Salary Update: " + rs.getDouble("BaseSalary"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.StatementClose(ps);
			DB.ResultSetClose(rs);
			DB.closeConnection();
			
		}
	}

}
