package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Combustivel;
import model.bean.Funcao;
import model.bean.OrdemCompra;
import model.bean.ProdutoOrdemCompra;
import model.bean.Tanque;
import model.bean.TanqueReparticao;
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
	
	public static boolean cadastrar(Tanque tanq) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO tanques (nome, capacidade, cor) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tanq.getNome());
			stmt.setFloat(2, tanq.getCapacidade());
			stmt.setString(3, tanq.getCor());
			
			if(stmt.executeUpdate() > 0) {
				ResultSet rsId = stmt.getGeneratedKeys();
				
				if(rsId.first()) {
					PreparedStatement stmtTanq = null;
					
					for(TanqueReparticao reparticao : tanq.getReparticoes()) {
						stmtTanq = con.prepareStatement("INSERT INTO tanques_reparticoes (id_combustivel, id_tanque) VALUES (?, ?)");
						stmtTanq.setInt(1, reparticao.getCombustivel().getId());
						stmtTanq.setInt(2, rsId.getInt(1));
						
						stmtTanq.executeUpdate();
					}
					
					Conexao.fecharConexao(null, stmtTanq, rsId);
				}
				
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
}