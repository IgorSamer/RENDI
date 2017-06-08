package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Combustivel;
import model.bean.Endereco;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Genero;
import model.bean.OrdemCompra;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Produto;
import model.bean.ProdutoOrdemCompra;
import model.bean.Setor;
import model.bean.Tanque;
import model.bean.TanqueReparticao;
import model.bean.Telefone;
import model.bean.UnidadeMedida;
import model.dao.FuncionarioDAO;
import model.dao.PessoaFisicaDAO;
import model.dao.ProdutoDAO;
import model.dao.TanqueDAO;

public class GerenciarTanquesController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	//@FXML
    //private JFXTreeTableView<Funcionario> tblFuncionarios;
	
    @FXML
    private JFXButton btnAlterarTanque;

    @FXML
    private JFXButton btnVisualizarTanque;

    @FXML
    private JFXButton btnCadastrarTanque;
    
    @FXML
    private JFXButton btnExcluirTanque;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtCapacidade;

    @FXML
    private JFXComboBox<Combustivel> cmbCombustivel;
    
    @FXML
    private JFXButton btnAdicionarReparticao, btnRemoverReparticao;
    
    @FXML
    private JFXTreeTableView<TanqueReparticao> tblReparticoes;
    
    @FXML
    private JFXColorPicker clrTanque;
    
    @FXML
    private JFXButton btnCancelarTanque, btnCadastrarTanqueFinal;
    
    @FXML
    private SplitPane splConteudo, splTanque;
    
    @FXML
    private PieChart grfTanque;
    
    private ArrayList<TanqueReparticao> lstReparticoes = new ArrayList<TanqueReparticao>();
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarTanque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return Bindings.concat(param.getValue().getValue().getPessoa().nomeProperty(), " ", param.getValue().getValue().getPessoa().sobrenomeProperty());
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colRg = new JFXTreeTableColumn<>("RG");
		colRg.setPrefWidth(150);
		colRg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().rgProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colCpf = new JFXTreeTableColumn<>("CPF");
		colCpf.setPrefWidth(150);
		colCpf.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().cpfProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colCidade = new JFXTreeTableColumn<>("Cidade");
		colCidade.setPrefWidth(150);
		colCidade.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getEndereco().cidadeProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colSalario = new JFXTreeTableColumn<>("Salário");
		colSalario.setPrefWidth(150);
		colSalario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().salarioProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colFuncao = new JFXTreeTableColumn<>("Função");
		colFuncao.setPrefWidth(150);
		colFuncao.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getFuncao().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colSetor = new JFXTreeTableColumn<>("Setor");
		colSetor.setPrefWidth(150);
		colSetor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getSetor().nomeProperty();
			}
		});
		
		ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
		funcionarios.addAll(FuncionarioDAO.listar());
		TreeItem<Funcionario> root = new RecursiveTreeItem<Funcionario>(funcionarios, RecursiveTreeObject::getChildren);
		
		//tblFuncionarios.getColumns().setAll(colId, colNome, colRg, colCpf, colCidade, colSalario, colFuncao, colSetor);
		//tblFuncionarios.setRoot(root);
		//tblFuncionarios.setShowRoot(false);
		
		btnCadastrarTanque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarTanque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		JFXTreeTableColumn<TanqueReparticao, String> colReparticaoCombustivel = new JFXTreeTableColumn<>("Combustível");
		colReparticaoCombustivel.setPrefWidth(150);
		colReparticaoCombustivel.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TanqueReparticao, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<TanqueReparticao, String> param) {
				return param.getValue().getValue().getCombustivel().nomeProperty();
			}
		});
		
		ObservableList<TanqueReparticao> reparticoes = FXCollections.observableArrayList();
		reparticoes.addAll(lstReparticoes);
		TreeItem<TanqueReparticao> rootReparticoes = new RecursiveTreeItem<TanqueReparticao>(reparticoes, RecursiveTreeObject::getChildren);
		
		tblReparticoes.getColumns().setAll(colReparticaoCombustivel);
		tblReparticoes.setRoot(rootReparticoes);
		tblReparticoes.setShowRoot(false);
		
		// Populando combobox Combustível
		ObservableList<Combustivel> obsListaCombustivel = FXCollections.observableArrayList();
		obsListaCombustivel.addAll(TanqueDAO.getCombustiveis());
		
		cmbCombustivel.setItems(obsListaCombustivel);
		
		btnAdicionarReparticao.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!cmbCombustivel.getSelectionModel().isEmpty()) {
					lstReparticoes.add(new TanqueReparticao(new Combustivel(cmbCombustivel.getSelectionModel().getSelectedItem().getId(), cmbCombustivel.getSelectionModel().getSelectedItem().getNome(), cmbCombustivel.getSelectionModel().getSelectedItem().getPreco(), cmbCombustivel.getSelectionModel().getSelectedItem().getCor())));
					
					if(lstReparticoes.size() > 0 && splTanque.getDividers().get(0).positionProperty().get() > 0.6) {
						animaTanque();
					}
					
					atualizaReparticoes();
				}
			}
		});
		
		btnRemoverReparticao.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				TanqueReparticao repaRemover = null;
				
				for(TanqueReparticao repa : lstReparticoes) {
					if(cmbCombustivel.getSelectionModel().getSelectedItem().getNome().equals(repa.getCombustivel().getNome())) {
						repaRemover = repa;
					}
				}
				
				if(repaRemover != null) {
					lstReparticoes.remove(repaRemover);
					
					if(lstReparticoes.size() == 0) {
						animaTanque();
					}
					
					atualizaReparticoes();
				}
			}
		});
		
		btnCadastrarTanqueFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				String tanqueCor = clrTanque.getValue().toString().substring(2);
				tanqueCor = tanqueCor.substring(0, tanqueCor.length() -2);
				
				Tanque tanque = new Tanque(0, txtNome.getText().trim(), Float.valueOf(txtCapacidade.getText().trim()), tanqueCor, lstReparticoes);

				if(TanqueDAO.cadastrar(tanque)) {
					mensagem.show("Tanque cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar tanque!", 2000);
				}
			}
		});
	}
	
	protected void atualizaReparticoes() {
		ObservableList<TanqueReparticao> reparticoes = FXCollections.observableArrayList();
		reparticoes.addAll(lstReparticoes);
		TreeItem<TanqueReparticao> rootReparticoes = new RecursiveTreeItem<TanqueReparticao>(reparticoes, RecursiveTreeObject::getChildren);
		
		tblReparticoes.setRoot(rootReparticoes);
		
		ObservableList<Data> itensGrafico = FXCollections.observableArrayList();
		
		for(TanqueReparticao repa : lstReparticoes) {
			itensGrafico.add(new PieChart.Data(repa.getCombustivel().getNome(), lstReparticoes.size()));
		}
		
		grfTanque.setData(itensGrafico);
		
		int a = 0;
		
		for(Data itemGrafico : itensGrafico) {
			itemGrafico.getNode().setStyle("-fx-pie-color: #" + lstReparticoes.get(a).getCombustivel().getCor());
			
			a++;
		}
	}
	
	protected void animaTanque() {
		DoubleProperty divPosTanque = splTanque.getDividers().get(0).positionProperty();
        
        double novaPosTanque = divPosTanque.get() > 0.6 ? 0.5 : 1.0;
        
        KeyValue keyValueTanque = new KeyValue(splTanque.getDividers().get(0).positionProperty(), novaPosTanque);
        Timeline divTimelineTanque = new Timeline(new KeyFrame(Duration.millis(300), keyValueTanque));
        divTimelineTanque.play();
	}
}