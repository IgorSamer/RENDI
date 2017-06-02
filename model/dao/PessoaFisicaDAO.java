package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Genero;
import model.conexao.Conexao;

public class PessoaFisicaDAO {
	public static ArrayList<EstadoCivil> getEstadosCivis() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<EstadoCivil> lista_estadosCivis = new ArrayList<EstadoCivil>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM estados_civis");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				EstadoCivil estadoCivil = new EstadoCivil(rs.getInt("id"), rs.getString("nome"));
				
				lista_estadosCivis.add(estadoCivil);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_estadosCivis;
	}
	
	public static ArrayList<Genero> getGeneros() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Genero> lista_generos = new ArrayList<Genero>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM generos");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Genero genero = new Genero(rs.getInt("id"), rs.getString("nome"));
				
				lista_generos.add(genero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_generos;
	}
	
	public static ArrayList<Escolaridade> getEscolaridades() {
		Connection con = Conexao.getConexao();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Escolaridade> lista_escolaridades = new ArrayList<Escolaridade>();
		
		try {		
			stmt = con.prepareStatement("SELECT id, nome FROM escolaridades");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Escolaridade escolaridade = new Escolaridade(rs.getInt("id"), rs.getString("nome"));
				
				lista_escolaridades.add(escolaridade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Conexao.fecharConexao(con, stmt, rs);
		
		return lista_escolaridades;
	}
}