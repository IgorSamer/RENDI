package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Endereco;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Setor;
import model.bean.Telefone;
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
			stmt = con.prepareStatement("SELECT f.id, f.nome, f.sobrenome, f.foto, fun.nome AS nomeFuncao "
									+ 	"FROM usuarios u "
									+ 	"INNER JOIN funcionarios f ON u.id_funcionario = f.id "
									+ 	"INNER JOIN funcoes fun ON f.id_funcao = fun.id "
									+ 	"WHERE u.usuario = ? and u.senha = ? and u.ativo = 1 LIMIT 1");
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				funcionario = new Funcionario(rs.getInt("id"), rs.getString("foto"), 0.0, new Funcao(rs.getString("nomeFuncao")), new Setor(null), new Pessoa(rs.getString("nome"), rs.getString("sobrenome")));
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
			stmt = con.prepareStatement("SELECT f.id, f.nome, f.sobrenome, f.foto, f.email, f.rg, f.cpf, f.end_cidade, f.salario, fun.nome AS nomeFuncao, s.nome AS nomeSetor "
									+ 	"FROM funcionarios f "
									+ 	"INNER JOIN funcoes fun ON f.id_funcao = fun.id "
									+ 	"INNER JOIN setores s ON f.id_setor = s.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("foto"), rs.getDouble("salario"), new Funcao(rs.getString("nomeFuncao")), new Setor(rs.getString("nomeSetor")), new Pessoa(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("email"), new PessoaFisica(rs.getString("rg"), rs.getString("cpf"), null), new Endereco(rs.getString("end_cidade"))));
				
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return funcionarios;
	}
	
	public static ArrayList<Funcao> getFuncoes() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Funcao> lista_funcoes = new ArrayList<Funcao>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM funcoes");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Funcao funcao = new Funcao(rs.getInt("id"), rs.getString("nome"));
				
				lista_funcoes.add(funcao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_funcoes;
	}
	
	public static ArrayList<Setor> getSetores() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Setor> lista_setores = new ArrayList<Setor>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM setores");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Setor setor = new Setor(rs.getInt("id"), rs.getString("nome"));
				
				lista_setores.add(setor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_setores;
	}
	
	public static boolean cadastrar(Funcionario func) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO funcionarios (email, nome, sobrenome, rg, cpf, end_cep, end_uf, end_cidade, end_bairro, end_rua, end_numero, data_admissao, data_demissao, data_nascimento, salario, foto, ativo, id_estado_civil, id_funcao, id_genero, id_escolaridade, id_setor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, func.getPessoa().getEmail());
			stmt.setString(2, func.getPessoa().getNome());
			stmt.setString(3, func.getPessoa().getSobrenome());
			stmt.setString(4, func.getPessoa().getPessoa_fisica().getRg());
			stmt.setString(5, func.getPessoa().getPessoa_fisica().getCpf());
			stmt.setString(6, func.getPessoa().getEndereco().getCep());
			stmt.setString(7, func.getPessoa().getEndereco().getUf());
			stmt.setString(8, func.getPessoa().getEndereco().getCidade());
			stmt.setString(9, func.getPessoa().getEndereco().getBairro());
			stmt.setString(10, func.getPessoa().getEndereco().getRua());
			stmt.setInt(11, func.getPessoa().getEndereco().getNumero());
			stmt.setString(12, func.getData_admissao());
			stmt.setString(13, func.getData_demissao());
			stmt.setString(14, func.getPessoa().getData_nascimento());
			stmt.setDouble(15, func.getSalario());
			stmt.setString(16, func.getFoto());
			stmt.setInt(17, func.getAtivo());
			stmt.setInt(18, func.getPessoa().getEstado_civil().getId());
			stmt.setInt(19, func.getFuncao().getId());
			stmt.setInt(20, func.getPessoa().getGenero().getId());
			stmt.setInt(21, func.getPessoa().getPessoa_fisica().getEscolaridade().getId());
			stmt.setInt(22, func.getSetor().getId());
			
			if(stmt.executeUpdate() > 0) {
				ResultSet rsId = stmt.getGeneratedKeys();
				
				if(rsId.first()) {
					PreparedStatement stmtTel = null;
					
					for(Telefone telefone : func.getPessoa().getTelefones()) {
						stmtTel = con.prepareStatement("INSERT INTO funcionarios_telefones (numero, id_funcionario) VALUES (?, ?)");
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