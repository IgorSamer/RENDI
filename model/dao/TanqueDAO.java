package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Bico;
import model.bean.Bomba;
import model.bean.Cliente;
import model.bean.Combustivel;
import model.bean.CombustivelVenda;
import model.bean.Funcionario;
import model.bean.Pessoa;
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
	
	public static ArrayList<Tanque> getTanques() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Tanque> lista_tanques = new ArrayList<Tanque>();
		
		try {		
			stmt = con.prepareStatement("SELECT t.id, t.nome, t.capacidade, t.cor, GROUP_CONCAT(c.nome) AS nomeCombustivel, GROUP_CONCAT(c.cor) AS corCombustivel "
					+ "FROM tanques t "
					+ "INNER JOIN tanques_reparticoes t_r ON t.id = t_r.id_tanque "
					+ "INNER JOIN combustiveis c ON t_r.id_combustivel = c.id "
					+ "GROUP BY t_r.id_tanque");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ArrayList<TanqueReparticao> lista_reparticoes = new ArrayList<TanqueReparticao>();
				
				String[] reparts_nome = rs.getString("nomeCombustivel").split(",");
				String[] reparts_cor = rs.getString("corCombustivel").split(",");
				
				for(int i = 0; i < reparts_nome.length; i++) {
					lista_reparticoes.add(new TanqueReparticao(new Combustivel(0, reparts_nome[i], 0.0, reparts_cor[i])));
				}
				
				Tanque tanque = new Tanque(rs.getInt("id"), rs.getString("nome"), rs.getFloat("capacidade"), rs.getString("cor"), lista_reparticoes);
				
				lista_tanques.add(tanque);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_tanques;
	}
	
	public static boolean cadastrar(Bomba bomb) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO bombas (nome, id_tanque) VALUES (?, ?)");
			stmt.setString(1, bomb.getNome());
			stmt.setInt(2, bomb.getTanque().getId());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static ArrayList<Bomba> getBombas() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Bomba> lista_bombas = new ArrayList<Bomba>();
		
		try {		
			//stmt = con.prepareStatement("SELECT b.id, b.nome, t.nome AS nomeTanque, COUNT(t_r.id) AS numeroReparticoes, GROUP_CONCAT(c.nome) AS nomeCombustivel "
			//		+ "FROM bombas b "
			//		+ "INNER JOIN tanques t ON b.id_tanque = t.id "
			//		+ "INNER JOIN tanques_reparticoes t_r ON b.id_tanque = t_r.id_tanque "
			//		+ "INNER JOIN combustiveis c ON t_r.id_combustivel = c.id "
			//		+ "GROUP BY t_r.id");
			
			stmt = con.prepareStatement("SELECT b.id, b.nome, t.id AS idTanque, t.nome AS nomeTanque "
					+ "FROM bombas b "
					+ "INNER JOIN tanques t ON b.id_tanque = t.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Bomba bomba = new Bomba(rs.getInt("id"), rs.getString("nome"), new Tanque(rs.getInt("idTanque"), rs.getString("nomeTanque")));
				
				lista_bombas.add(bomba);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_bombas;
	}
	
	public static ArrayList<TanqueReparticao> getReparticoes(Integer id) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<TanqueReparticao> lista_reparticoes = new ArrayList<TanqueReparticao>();
		
		try {
			stmt = con.prepareStatement("SELECT t_r.id, c.nome "
					+ "FROM tanques_reparticoes t_r "
					+ "INNER JOIN combustiveis c ON t_r.id_combustivel = c.id "
					+ "WHERE t_r.id_tanque = " + id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TanqueReparticao reparticao = new TanqueReparticao(rs.getInt("id"), new Combustivel(rs.getString("nome")));
				
				lista_reparticoes.add(reparticao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_reparticoes;
	}
	
	public static boolean cadastrar(Bico bic) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO bicos (nome, id_bomba, id_reparticao) VALUES (?, ?, ?)");
			stmt.setString(1, bic.getNome());
			stmt.setInt(2, bic.getBomba().getId());
			stmt.setInt(3, bic.getReparticao().getId());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static ArrayList<Bico> getBicos() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Bico> lista_bicos = new ArrayList<Bico>();
		
		try {		
			stmt = con.prepareStatement("SELECT b.id, b.nome, c.nome AS nomeCombustivel, c.preco "
					+ "FROM bicos b "
					+ "INNER JOIN tanques_reparticoes t_r ON b.id_reparticao = t_r.id "
					+ "INNER JOIN combustiveis c ON t_r.id_combustivel = c.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Bico bico = new Bico(rs.getInt("id"), rs.getString("nome"), new TanqueReparticao(new Combustivel(rs.getString("nomeCombustivel"), rs.getDouble("preco"))));
				
				lista_bicos.add(bico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_bicos;
	}
	
	public static ArrayList<CombustivelVenda> getAbastecimentos() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<CombustivelVenda> lista_abastecimentos = new ArrayList<CombustivelVenda>();
		
		try {		
			stmt = con.prepareStatement("SELECT c_v.id, c_v.data, c_v.litros, c_v.status, c_v.id_bico, c.nome AS nomeCliente, c.sobrenome AS sobrenomeCliente, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario "
					+ "FROM combustiveis_vendas c_v "
					+ "INNER JOIN clientes c ON c_v.id_cliente = c.id "
					+ "INNER JOIN funcionarios f ON c_v.id_funcionario = f.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CombustivelVenda abastecimento = new CombustivelVenda(rs.getInt("id"), rs.getString("data"), rs.getFloat("litros"), rs.getInt("status"), new Cliente(0, new Pessoa(rs.getString("nomeCliente"), rs.getString("sobrenomeCliente"))), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))), new Bico(rs.getInt("id_bico")));
				
				lista_abastecimentos.add(abastecimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_abastecimentos;
	}
	
	public static ArrayList<CombustivelVenda> getAbastecimentosCaixa() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<CombustivelVenda> lista_abastecimentos = new ArrayList<CombustivelVenda>();
		
		try {		
			stmt = con.prepareStatement("SELECT co.nome AS nomeCombustivel, co.preco, b.nome AS nomeBico, c_v.id, c_v.data, c_v.litros, c_v.status, c_v.id_bico, c.nome AS nomeCliente, c.sobrenome AS sobrenomeCliente, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario "
										+ "FROM combustiveis_vendas c_v "
										+ "INNER JOIN clientes c ON c_v.id_cliente = c.id "
										+ "INNER JOIN funcionarios f ON c_v.id_funcionario = f.id "
										+ "INNER JOIN bicos b ON c_v.id_bico = b.id "
										+ "INNER JOIN tanques_reparticoes t_r ON b.id_reparticao = t_r.id "
										+ "INNER JOIN combustiveis co ON t_r.id_combustivel = co.id "
										+ "WHERE YEAR(c_v.data) = YEAR(CURDATE()) AND MONTH(c_v.data) = MONTH(CURDATE()) AND DAY(c_v.data) = DAY(CURDATE())");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CombustivelVenda abastecimento = new CombustivelVenda(rs.getInt("id"), rs.getString("data"), rs.getFloat("litros"), rs.getInt("status"), new Cliente(0, new Pessoa(rs.getString("nomeCliente"), rs.getString("sobrenomeCliente"))), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))), new Bico(rs.getInt("id_bico"), rs.getString("nomeBico"), new TanqueReparticao(new Combustivel(rs.getString("nomeCombustivel"), rs.getDouble("preco")))));
				
				lista_abastecimentos.add(abastecimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_abastecimentos;
	}
	
	public static boolean cadastrar(CombustivelVenda venda) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO combustiveis_vendas (data, litros, status, id_cliente, id_funcionario, id_bico) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, venda.getData());
			stmt.setFloat(2, venda.getLitros());
			stmt.setInt(3, venda.getStatus());
			stmt.setInt(4, venda.getCliente().getId());
			stmt.setInt(5, venda.getFuncionario().getId());
			stmt.setInt(6, venda.getBico().getId());
			
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