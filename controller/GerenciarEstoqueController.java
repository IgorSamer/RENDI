package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import model.bean.Endereco;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Genero;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Produto;
import model.bean.Setor;
import model.bean.Telefone;
import model.bean.UnidadeMedida;
import model.dao.FuncionarioDAO;
import model.dao.PessoaFisicaDAO;
import model.dao.ProdutoDAO;

public class GerenciarEstoqueController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	//@FXML
    //private JFXTreeTableView<Funcionario> tblFuncionarios;
	
	@FXML
    private JFXButton btnPesquisarEstoque;

    @FXML
    private JFXButton btnAlterarEstoque;

    @FXML
    private JFXButton btnVisualizarEstoque;

    @FXML
    private JFXButton btnCadastrarEstoque;
    
    @FXML
    private JFXButton btnExcluirEstoque;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDescricao;

    @FXML
    private JFXTextField txtPreco;

    @FXML
    private JFXTextField txtQuantidade;

    @FXML
    private JFXComboBox<Setor> cmbSetor;
    
    @FXML
    private JFXComboBox<UnidadeMedida> cmbUnidadeMedida;
    
    @FXML
    private JFXButton btnCancelarEstoque, btnCadastrarEstoqueFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    public static boolean mostra = false;
	
    Timeline timeline;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarEstoque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		mostra = false;
		
		timeline = new Timeline(
			new KeyFrame(Duration.millis(100),
					event -> {
						if(mostra) {
							funGerenciar.setVisible(false);
							funCadastrar.setVisible(true);
							
							mostra = false;
							
							timeline.stop();
						}
			        }
			)
		);
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
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
		
		btnCadastrarEstoque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarEstoque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Setor
		ObservableList<Setor> obsListaSetor = FXCollections.observableArrayList();
		obsListaSetor.addAll(FuncionarioDAO.getSetores());
		
		cmbSetor.setItems(obsListaSetor);
		
		// Populando combobox Unidade de Medida
		ObservableList<UnidadeMedida> obsListaUnidadeMedida = FXCollections.observableArrayList();
		obsListaUnidadeMedida.addAll(ProdutoDAO.getUnidadesMedida());
		
		cmbUnidadeMedida.setItems(obsListaUnidadeMedida);
		
		btnCadastrarEstoqueFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				Produto produto = new Produto(0, txtNome.getText().trim(), txtDescricao.getText().trim(), Double.valueOf(txtPreco.getText().trim()), Float.valueOf(txtQuantidade.getText().trim()), new Setor(cmbSetor.getSelectionModel().getSelectedItem().getId(), null), new UnidadeMedida(cmbUnidadeMedida.getSelectionModel().getSelectedItem().getId(), null), new Funcionario(PainelController.idFuncionario));
				
				if(ProdutoDAO.cadastrar(produto)) {
					mensagem.show("Produto cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar produto!", 2000);
				}
			}
		});
	}
}