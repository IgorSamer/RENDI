package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Funcionario;
import model.bean.Licenca;
import model.bean.OrgaoEmissor;
import model.bean.Pessoa;
import model.conexao.Conexao;

public class LicencaDAO {
	public static ArrayList<OrgaoEmissor> getOrgaosEmissores() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<OrgaoEmissor> lista_orgaosEmissores = new ArrayList<OrgaoEmissor>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM orgaos_emissores");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				OrgaoEmissor orgaoEmissor = new OrgaoEmissor(rs.getInt("id"), rs.getString("nome"));
				
				lista_orgaosEmissores.add(orgaoEmissor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_orgaosEmissores;
	}
	
	public static boolean cadastrar(OrgaoEmissor org) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO orgaos_emissores (nome) VALUES (?)");
			stmt.setString(1, org.getNome());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static ArrayList<Licenca> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Licenca> lista_licencas = new ArrayList<Licenca>();
		
		try {		
			stmt = con.prepareStatement("SELECT l.id, l.nome, descricao, anexo, protocolo, data_emissao, data_vencimento, o_e.nome AS nomeOrgaoEmissor, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario "
					+ "FROM licencas l "
					+ "INNER JOIN orgaos_emissores o_e ON l.id_orgao_emissor = o_e.id "
					+ "INNER JOIN funcionarios f ON l.id_funcionario = f.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Licenca licenca = new Licenca(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getString("anexo"), rs.getString("protocolo"), rs.getString("data_emissao"), rs.getString("data_vencimento"), null, new OrgaoEmissor(rs.getString("nomeOrgaoEmissor")), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))));
				
				lista_licencas.add(licenca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_licencas;
	}
	
	public static boolean cadastrar(Licenca lic) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO licencas (nome, descricao, anexo, protocolo, data_emissao, data_vencimento, id_orgao_emissor, id_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, lic.getNome());
			stmt.setString(2, lic.getDescricao());
			stmt.setString(3, lic.getAnexo());
			stmt.setString(4, lic.getProtocolo());
			stmt.setString(5, lic.getData_emissao());
			stmt.setString(6, lic.getData_vencimento());
			stmt.setInt(7, lic.getOrgao_emissor().getId());
			stmt.setInt(8, lic.getFuncionario().getId());
			
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