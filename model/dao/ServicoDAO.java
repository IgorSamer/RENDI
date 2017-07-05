package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Cliente;
import model.bean.Funcionario;
import model.bean.Pessoa;
import model.bean.Servico;
import model.bean.ServicoVenda;
import model.bean.Setor;
import model.conexao.Conexao;

public class ServicoDAO {
	public static ArrayList<Servico> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		
		try {
			stmt = con.prepareStatement("SELECT s.id, s.nome, s.preco, se.nome AS nomeSetor "
					+ "FROM servicos s "
					+ "INNER JOIN setores se ON s.id_setor = se.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Servico servico = new Servico(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), new Setor(rs.getString("nomeSetor")));
				
				servicos.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return servicos;
	}
	
	public static boolean cadastrar(ServicoVenda serv) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO servicos_vendas (data, placa, quilometragem, id_servico, id_funcionario, id_cliente) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, serv.getData());
			stmt.setString(2, serv.getPlaca());
			stmt.setDouble(3, serv.getQuilometragem());
			stmt.setInt(4, serv.getServico().getId());
			stmt.setInt(5, serv.getFuncionario().getId());
			stmt.setInt(6, serv.getCliente().getId());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static ArrayList<ServicoVenda> getServicosVendas() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ServicoVenda> servicos = new ArrayList<ServicoVenda>();
		
		try {
			stmt = con.prepareStatement("SELECT s_v.id, s_v.data, s_v.placa, s_v.quilometragem, s.nome AS nomeServico, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario, c.nome AS nomeCliente, c.sobrenome AS sobrenomeCliente "
					+ "FROM servicos_vendas s_v "
					+ "INNER JOIN servicos s ON s_v.id_servico = s.id "
					+ "INNER JOIN funcionarios f ON s_v.id_funcionario = f.id "
					+ "INNER JOIN clientes c ON s_v.id_cliente = c.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ServicoVenda servico = new ServicoVenda(rs.getInt("id"), rs.getString("data"), rs.getString("placa"), rs.getDouble("quilometragem"), new Servico(0, rs.getString("nomeServico"), 0.0), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))), new Cliente(0, new Pessoa(rs.getString("nomeCliente"), rs.getString("sobrenomeCliente"))));
				
				servicos.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return servicos;
	}
	
	public static ArrayList<ServicoVenda> getServicosVendasCaixa() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ServicoVenda> servicos = new ArrayList<ServicoVenda>();
		
		try {
			stmt = con.prepareStatement("SELECT s_v.id, s_v.data, s_v.placa, s_v.quilometragem, s.nome AS nomeServico, s.preco, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario, c.nome AS nomeCliente, c.sobrenome AS sobrenomeCliente "
					+ "FROM servicos_vendas s_v "
					+ "INNER JOIN servicos s ON s_v.id_servico = s.id "
					+ "INNER JOIN funcionarios f ON s_v.id_funcionario = f.id "
					+ "INNER JOIN clientes c ON s_v.id_cliente = c.id "
					+ "WHERE YEAR(s_v.data) = YEAR(CURDATE()) AND MONTH(s_v.data) = MONTH(CURDATE()) AND DAY(s_v.data) = DAY(CURDATE())");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ServicoVenda servico = new ServicoVenda(rs.getInt("id"), rs.getString("data"), rs.getString("placa"), rs.getDouble("quilometragem"), new Servico(0, rs.getString("nomeServico"), rs.getDouble("preco")), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))), new Cliente(0, new Pessoa(rs.getString("nomeCliente"), rs.getString("sobrenomeCliente"))));
				
				servicos.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return servicos;
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
	
	public static boolean cadastrar(Servico serv) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO servicos (nome, preco, id_setor) VALUES (?, ?, ?)");
			stmt.setString(1, serv.getNome());
			stmt.setDouble(2, serv.getPreco());
			stmt.setInt(3, serv.getSetor().getId());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
}