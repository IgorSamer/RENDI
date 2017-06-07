package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.bean.Fornecedor;
import model.bean.Funcionario;
import model.bean.OrdemCompra;
import model.bean.Pessoa;
import model.bean.Produto;
import model.bean.ProdutoOrdemCompra;
import model.bean.Telefone;
import model.bean.TipoPagamento;
import model.conexao.Conexao;

public class OrdemCompraDAO {
	public static ArrayList<OrdemCompra> listar() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<OrdemCompra> ordensCompra = new ArrayList<OrdemCompra>();
		
		try {
			stmt = con.prepareStatement("SELECT o.*, f.nome_fantasia AS nomeFornecedor, fu.nome AS nomeFuncionario, fu.sobrenome AS sobrenomeFuncionario, t.nome AS nomeTipoPagamento "
					+ "FROM ordens_compra o "
					+ "INNER JOIN fornecedores f ON o.id_fornecedor = f.id "
					+ "INNER JOIN funcionarios fu ON o.id_funcionario = fu.id "
					+ "INNER JOIN tipos_pagamentos t ON o.id_tipo_pagamento = t.id");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				OrdemCompra ordemCompra = new OrdemCompra(rs.getInt("id"), rs.getString("data"), rs.getString("data_prevista"), rs.getInt("status"), rs.getString("observacoes"), rs.getDouble("valor"), rs.getInt("parcelas"), rs.getInt("distancia_pagamento"), new Fornecedor(rs.getString("nomeFornecedor")), new Funcionario(new Pessoa(rs.getString("nomeFuncionario"), rs.getString("sobrenomeFuncionario"))), new TipoPagamento(0, rs.getString("nomeTipoPagamento")), null);
				
				ordensCompra.add(ordemCompra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Conexao.fecharConexao(con, stmt, rs);
		
		return ordensCompra;
	}
	
	public static boolean cadastrar(OrdemCompra orde) {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		
		boolean cadastrou = false;
		
		try {
			stmt = con.prepareStatement("INSERT INTO ordens_compra (data, data_prevista, status, observacoes, valor, parcelas, distancia_pagamento, id_fornecedor, id_funcionario, id_tipo_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, orde.getData());
			stmt.setString(2, orde.getData_prevista());
			stmt.setInt(3, orde.getStatus());
			stmt.setString(4, orde.getObservacoes());
			stmt.setDouble(5, orde.getValor());
			stmt.setFloat(6, orde.getParcelas());
			stmt.setInt(7, orde.getDistancia_pagamento());
			stmt.setInt(8, orde.getFornecedor().getId());
			stmt.setInt(9, orde.getFuncionario().getId());
			stmt.setInt(10, orde.getTipo_pagamento().getId());
			
			if(stmt.executeUpdate() > 0) {
				ResultSet rsId = stmt.getGeneratedKeys();
				
				if(rsId.first()) {
					PreparedStatement stmtProd = null;
					
					for(ProdutoOrdemCompra produto : orde.getProdutos()) {
						stmtProd = con.prepareStatement("INSERT INTO produtos_ordem_compra (quantidade, id_ordem_compra, id_produto) VALUES (?, ?, ?)");
						stmtProd.setInt(1, produto.getQuantidade());
						stmtProd.setInt(2, rsId.getInt(1));
						stmtProd.setInt(3, produto.getProduto().getId());
						
						stmtProd.executeUpdate();
					}
					
					Conexao.fecharConexao(null, stmtProd, rsId);
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