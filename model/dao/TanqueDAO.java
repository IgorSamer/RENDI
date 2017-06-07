package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Combustivel;
import model.bean.Funcao;
import model.conexao.Conexao;

public class TanqueDAO {
	public static ArrayList<Combustivel> getCombustiveis() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Combustivel> lista_combustiveis = new ArrayList<Combustivel>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome, preco, cor FROM combustiveis");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Combustivel combustivel = new Combustivel(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getString("cor"));
				
				lista_combustiveis.add(combustivel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_combustiveis;
	}
}