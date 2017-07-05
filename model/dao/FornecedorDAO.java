package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Endereco;
import model.bean.Fornecedor;
import model.bean.Telefone;
import model.conexao.Conexao;

public class FornecedorDAO {
	public static ArrayList<Fornecedor> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		try {
			stmt = con.prepareStatement("SELECT id, email, nome_empresarial, cnpj, nome_fantasia, status, end_cidade FROM fornecedores");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor(rs.getInt("id"), rs.getString("email"), rs.getString("nome_empresarial"), rs.getString("cnpj"), rs.getString("nome_fantasia"), rs.getInt("status"), null, new Endereco(rs.getString("end_cidade")));
				
				fornecedores.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return fornecedores;
	}
	
	public static boolean cadastrar(Fornecedor forn) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO fornecedores (email, nome_empresarial, cnpj, nome_fantasia, end_cep, end_uf, end_cidade, end_bairro, end_rua, end_numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, forn.getEmail());
			stmt.setString(2, forn.getNome_empresarial());
			stmt.setString(3, forn.getCnpj());
			stmt.setString(4, forn.getNome_fantasia());
			stmt.setString(5, forn.getEndereco().getCep());
			stmt.setString(6, forn.getEndereco().getUf());
			stmt.setString(7, forn.getEndereco().getCidade());
			stmt.setString(8, forn.getEndereco().getBairro());
			stmt.setString(9, forn.getEndereco().getBairro());
			stmt.setInt(10, forn.getEndereco().getNumero());
			
			if(stmt.executeUpdate() > 0) {
				ResultSet rsId = stmt.getGeneratedKeys();
				
				if(rsId.first()) {
					PreparedStatement stmtTel = null;
					
					for(Telefone telefone : forn.getTelefones()) {
						stmtTel = con.prepareStatement("INSERT INTO fornecedores_telefones (numero, id_fornecedor) VALUES (?, ?)");
						stmtTel.setString(1, telefone.getNumero());
						stmtTel.setInt(2, rsId.getInt(1));
						
						stmtTel.executeUpdate();
					}
					
					Conexao.fecharConexao(null, stmtTel, rsId);
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