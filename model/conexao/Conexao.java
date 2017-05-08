package model.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/rendi";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	
	public static Connection getConexao() {
		try {
			Class.forName(DRIVER);
			
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void fecharConexao(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Igor GAY !
	
	public static void fecharConexao(Connection con, PreparedStatement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		fecharConexao(con);
	}
	
	public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		fecharConexao(con, stmt);
	}
}