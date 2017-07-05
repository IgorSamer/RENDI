package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Cliente;
import model.bean.Endereco;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Telefone;
import model.conexao.Conexao;

public class ClienteDAO {
	public static ArrayList<Cliente> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			stmt = con.prepareStatement("SELECT id, nome, sobrenome, rg, cpf, end_cidade FROM clientes");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id"), new Pessoa(rs.getString("nome"), rs.getString("sobrenome"), null, new PessoaFisica(rs.getString("rg"), rs.getString("cpf"), null), new Endereco(rs.getString("end_cidade"))));
				
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return clientes;
	}
	
	public static boolean cadastrar(Cliente cli) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO clientes (email, nome, sobrenome, rg, cpf, end_cep, end_uf, end_cidade, end_bairro, end_rua, end_numero, data_nascimento, id_genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cli.getPessoa().getEmail());
			stmt.setString(2, cli.getPessoa().getNome());
			stmt.setString(3, cli.getPessoa().getSobrenome());
			stmt.setString(4, cli.getPessoa().getPessoa_fisica().getRg());
			stmt.setString(5, cli.getPessoa().getPessoa_fisica().getCpf());
			stmt.setString(6, cli.getPessoa().getEndereco().getCep());
			stmt.setString(7, cli.getPessoa().getEndereco().getUf());
			stmt.setString(8, cli.getPessoa().getEndereco().getCidade());
			stmt.setString(9, cli.getPessoa().getEndereco().getBairro());
			stmt.setString(10, cli.getPessoa().getEndereco().getRua());
			stmt.setInt(11, cli.getPessoa().getEndereco().getNumero());
			stmt.setString(12, cli.getPessoa().getData_nascimento());
			stmt.setInt(13, cli.getPessoa().getGenero().getId());
			
			if(stmt.executeUpdate() > 0) {
				ResultSet rsId = stmt.getGeneratedKeys();
				
				if(rsId.first()) {
					PreparedStatement stmtTel = null;
					
					for(Telefone telefone : cli.getPessoa().getTelefones()) {
						stmtTel = con.prepareStatement("INSERT INTO clientes_telefones (numero, id_cliente) VALUES (?, ?)");
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
	
	public static ArrayList<Cliente> getAniversariantes() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			stmt = con.prepareStatement("SELECT c.nome, c.email, c_t.numero "
					+ " FROM clientes c "
					+ "INNER JOIN clientes_telefones c_t ON c_t.id_cliente = c.id "
					+ "WHERE MONTH(data_nascimento) = MONTH(CURDATE()) AND DAY(data_nascimento) = DAY(CURDATE())");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ArrayList<Telefone> telefones = new ArrayList<>();
				telefones.add(new Telefone(rs.getString("numero")));
				
				Cliente cliente = new Cliente(0, new Pessoa(rs.getString("nome"), rs.getString("email"), telefones));
				
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return clientes;
	}
}