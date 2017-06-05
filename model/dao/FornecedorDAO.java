package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Cliente;
import model.bean.Endereco;
import model.bean.Fornecedor;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Telefone;
import model.conexao.Conexao;

public class FornecedorDAO {
	public static ArrayList<Fornecedor> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		try {
			stmt = con.prepareStatement("SELECT id, email, razao_social, cnpj, inscricao_estadual, nome_fantasia, status FROM fornecedores");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor(rs.getInt("id"), rs.getString("email"), rs.getString("razao_social"), rs.getString("cnpj"), rs.getString("inscricao_estadual"), rs.getString("nome_fantasia"), rs.getInt("status"), null);
				
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
			stmt = con.prepareStatement("INSERT INTO fornecedores (email, razao_social, cnpj, inscricao_estadual, nome_fantasia) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, forn.getEmail());
			stmt.setString(2, forn.getRazao_social());
			stmt.setString(3, forn.getCnpj());
			stmt.setString(4, forn.getInscricao_estadual());
			stmt.setString(5, forn.getNome_fantasia());
			
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