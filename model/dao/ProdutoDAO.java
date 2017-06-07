package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Fornecedor;
import model.bean.Funcionario;
import model.bean.Pessoa;
import model.bean.Produto;
import model.bean.Setor;
import model.bean.Telefone;
import model.bean.UnidadeMedida;
import model.conexao.Conexao;

public class ProdutoDAO {
	public static ArrayList<UnidadeMedida> getUnidadesMedida() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<UnidadeMedida> lista_unidadesMedida = new ArrayList<UnidadeMedida>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM unidades_medidas");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				UnidadeMedida unidadeMedida = new UnidadeMedida(rs.getInt("id"), rs.getString("nome"));
				
				lista_unidadesMedida.add(unidadeMedida);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_unidadesMedida;
	}
	
	public static boolean cadastrar(Produto prod) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO produtos (nome, descricao, preco, quantidade, id_setor, id_unidade_medida, id_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, prod.getNome());
			stmt.setString(2, prod.getDescricao());
			stmt.setDouble(3, prod.getPreco());
			stmt.setFloat(4, prod.getQuantidade());
			stmt.setInt(5, prod.getSetor().getId());
			stmt.setInt(6, prod.getUnidade_medida().getId());
			stmt.setInt(7, prod.getFuncionario().getId());
			
			if(stmt.executeUpdate() > 0) {
				cadastrou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt);
		
		return cadastrou;
	}
	
	public static ArrayList<Produto> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		try {
			stmt = con.prepareStatement("SELECT p.id, p.nome, p.descricao, p.preco, p.quantidade, s.nome AS nomeSetor, u.nome AS nomeUnidadeMedida, f.nome AS nomeFuncionario, f.sobrenome AS sobrenomeFuncionario "
					+ "FROM produtos p "
					+ "INNER JOIN setores s ON p.id_setor = s.id "
					+ "INNER JOIN unidades_medidas u ON p.id_unidade_medida = u.id "
					+ "INNER JOIN funcionarios f ON p.id_funcionario = f.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getDouble("preco"), rs.getFloat("quantidade"), new Setor(0, rs.getString("nomeSetor")), new UnidadeMedida(0, rs.getString("nomeUnidadeMedida")), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))));
				
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return produtos;
	}
}