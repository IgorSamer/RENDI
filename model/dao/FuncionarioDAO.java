package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Pessoa;
import model.conexao.Conexao;

public class FuncionarioDAO {
	public static String entrar(String usuario) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String entrou = "";
		
		try {
			stmt = con.prepareStatement("SELECT f.foto FROM usuarios u INNER JOIN funcionarios f ON u.id_funcionario = f.id WHERE u.usuario = ? and u.ativo = 1 LIMIT 1");
			
			stmt.setString(1, usuario);
			
			rs = stmt.executeQuery();
			
			if(rs.first()) {
				entrou = rs.getString("foto");
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
			stmt = con.prepareStatement("SELECT f.id, f.nome, f.sobrenome, f.foto, fun.id AS idFuncao, fun.nome AS nomeFuncao "
									+ 	"FROM usuarios u "
									+ 	"INNER JOIN funcionarios f ON u.id_funcionario = f.id "
									+ 	"INNER JOIN funcoes fun ON f.id_funcao = fun.id "
									+ 	"WHERE u.usuario = ? and u.senha = ? and u.ativo = 1 LIMIT 1");
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				funcionario = new Funcionario(rs.getInt("id"), rs.getString("foto"), new Funcao(rs.getInt("idFuncao"), rs.getString("nomeFuncao")), new Pessoa(rs.getString("nome"), rs.getString("sobrenome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return funcionario;
	}
	
	public static ArrayList<Funcionario> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			stmt = con.prepareStatement("SELECT f.id, f.nome, f.sobrenome, f.email, f.rg, f.cpf, f.salario, fun.nome AS nomeFuncao, s.nome AS nomeSetor "
									+ 	"FROM funcionarios f "
									+ 	"INNER JOIN funcoes fun ON f.id_funcao = fun.id "
									+ 	"INNER JOIN setores s ON f.id_setor = s.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getInt("id"), null, new Funcao(rs.getString("nomeFuncao")), new Pessoa(rs.getString("nome"), rs.getString("sobrenome")));
				
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return funcionarios;
	}
}