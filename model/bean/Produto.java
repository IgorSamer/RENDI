package model.bean;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto extends RecursiveTreeObject<Produto> {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	private FloatProperty quantidade = new SimpleFloatProperty();
	private StringProperty foto = new SimpleStringProperty();
	private Setor setor;
	private UnidadeMedida unidade_medida;
	private Funcionario funcionario;
	
	public Produto(Integer Id, String Nome, String Descricao, Double Preco, Float Quantidade, String Foto, Setor Setor, UnidadeMedida Unidade_medida, Funcionario Funcionario) {
		this.setId(Id);
		this.setNome(Nome);
		this.setDescricao(Descricao);
		this.setPreco(Preco);
		this.setQuantidade(Quantidade);
		this.setFoto(Foto);
		this.setSetor(Setor);
		this.setUnidade_medida(Unidade_medida);
		this.setFuncionario(Funcionario);
	}
	
	public Produto(Integer Id, String Nome, Double Preco) {
		this.setId(Id);
		this.setNome(Nome);
		this.setPreco(Preco);
	}
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer Id) {
		id.set(Id);
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public final String getNome() {
		return nome.get();
	}
	
	public final void setNome(String Nome) {
		nome.set(Nome);
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public final String getDescricao() {
		return descricao.get();
	}
	
	public final void setDescricao(String Descricao) {
		descricao.set(Descricao);
	}
	
	public StringProperty descricaoProperty() {
		return descricao;
	}
	
	public final Float getQuantidade() {
		return quantidade.get();
	}
	
	public final void setQuantidade(Float Quantidade) {
		quantidade.set(Quantidade);
	}
	
	public FloatProperty quantidadeProperty() {
		return quantidade;
	}
	
	public final Double getPreco() {
		return preco.get();
	}
	
	public final void setPreco(Double Preco) {
		preco.set(Preco);
	}
	
	public DoubleProperty precoProperty() {
		return preco;
	}
	
	public Setor getSetor() {
		return setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public UnidadeMedida getUnidade_medida() {
		return unidade_medida;
	}
	
	public void setUnidade_medida(UnidadeMedida Unidade_medida) {
		this.unidade_medida = Unidade_medida;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario Funcionario) {
		this.funcionario = Funcionario;
	}
	
	public final String getFoto() {
		return foto.get();
	}
	
	public final void setFoto(String Foto) {
		foto.set(Foto);
	}
	
	public StringProperty fotoProperty() {
		return foto;
	}
	
	public static String getCaminhoFoto(String foto) {
		return "/view/img/produtos/" + foto;
	}
	
	@Override
	public String toString() {
		return this.getNome() + " (" + this.getDescricao() + ")";
	}
}