package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Connection conn = null;
			PreparedStatement ps = null;
			
			try {
				
				conn = DB.getConnection();
				ps = conn.prepareStatement("INSERT INTO seller"
						+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
						+ "VALUES "
						+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, "Pelé");
				ps.setString(2, "PeléKingsoccer@gmail.com");
				ps.setDate(3, new java.sql.Date(sdf.parse("02/03/1949").getTime()));
				ps.setDouble(4, 3.900);
				ps.setInt(5, 1);
				
				int linesAffected = ps.executeUpdate();
				if(linesAffected > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					while (rs.next()) {
						int id = rs.getInt(1);
						System.out.println("Lines affected: " + linesAffected + " ID: " + id);
					}
				}else {
					System.out.println("No affected Lines!");
				}
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(ParseException e) {
				e.printStackTrace();
			}
			finally {
				DB.StatementClose(ps);
				DB.getConnection();
				
			}
	}

}
