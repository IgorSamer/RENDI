package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Funcao;
import model.bean.Funcionario;
import model.conexao.Conexao;

public class FuncionarioDAO {
	public static boolean entrar(String usuario) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		boolean entrou = false;
		
		try {
			stmt = con.prepareStatement("SELECT id FROM usuarios WHERE usuario = ? and ativo = 1 LIMIT 1");
			
			stmt.setString(1, usuario);
			
			rs = stmt.executeQuery();
			
			if(rs.first()) {
				entrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return entrou;
	}
	
	public static Funcionario entrar(String usuario, String senha) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Funcionario funcionario = null;
		
		try {
			stmt = con.prepareStatement("SELECT f.id, f.nome, f.sobrenome, f.foto, fun.nome AS nomeFuncao "
									+ 	"FROM usuarios u INNER JOIN "
									+ 	"FROM funcionarios f "
									+ 	"INNER JOIN funcoes fun ON f.id_funcao = fun.id "
									+ 	"WHERE u.usuario = ? and u.senha = ? and u.ativo = 1 LIMIT 1");
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("foto"), new Funcao(rs.getString("nomeFuncao")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return funcionario;
	}
}