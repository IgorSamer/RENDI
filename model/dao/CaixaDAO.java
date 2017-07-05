package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Caixa;
import model.conexao.Conexao;

public class CaixaDAO {
	public static ArrayList<Caixa> getCaixas() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Caixa> lista_caixa = new ArrayList<Caixa>();
		
		try {		
			stmt = con.prepareStatement("SELECT SUM(valor) AS vendasTotal, MONTHNAME(STR_TO_DATE(MONTH(data), '%m')) AS vendasMes FROM caixa GROUP BY MONTH(data)");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Caixa caixa = new Caixa(rs.getDouble("vendasTotal"), rs.getString("vendasMes"));
				
				lista_caixa.add(caixa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_caixa;
	}
	
	public static boolean cadastrar(Caixa caixa) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO caixa (data, valor) VALUES (?, ?)");
			stmt.setString(1, caixa.getVendasData());
			stmt.setDouble(2, caixa.getVendasTotal());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static boolean fechouCaixa() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		boolean fechou = false;
		
		try {		
			stmt = con.prepareStatement("SELECT id FROM caixa WHERE data = CURDATE()");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				fechou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return fechou;
	}
}