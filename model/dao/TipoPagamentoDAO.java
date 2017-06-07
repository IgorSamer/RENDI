package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.TipoPagamento;
import model.conexao.Conexao;

public class TipoPagamentoDAO {
	public static ArrayList<TipoPagamento> getTiposPagamento() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<TipoPagamento> lista_tiposPagamentos = new ArrayList<TipoPagamento>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM tipos_pagamentos");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TipoPagamento tipoPagamento = new TipoPagamento(rs.getInt("id"), rs.getString("nome"));
				
				lista_tiposPagamentos.add(tipoPagamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_tiposPagamentos;
	}
}