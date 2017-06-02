package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Genero;
import model.bean.Setor;
import model.dao.FuncionarioDAO;
import model.dao.PessoaFisicaDAO;

public class GerenciarFuncionariosController implements Initializable {
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Funcionario> tblFuncionarios;

    @FXML
    private JFXButton btnExcluirFuncionario;

    @FXML
    private JFXButton btnAlterarFuncionario;

    @FXML
    private JFXButton btnVisualizarFuncionario;

    @FXML
    private JFXButton btnCadastrarFuncionario;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtSobrenome;

    @FXML
    private JFXTextField txtRg;

    @FXML
    private JFXTextField txtCpf;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCep;

    @FXML
    private JFXTextField txtUf;

    @FXML
    private JFXTextField txtCidade;

    @FXML
    private JFXTextField txtBairro;

    @FXML
    private JFXTextField txtRua;

    @FXML
    private JFXTextField txtNumero;

    @FXML
    private JFXDatePicker datAdmissao;

    @FXML
    private JFXDatePicker datDemissao;

    @FXML
    private JFXDatePicker datNascimento;

    @FXML
    private JFXTextField txtSalario;

    @FXML
    private JFXComboBox<EstadoCivil> cmbEstadoCivil;

    @FXML
    private JFXComboBox<Genero> cmbGenero;

    @FXML
    private JFXComboBox<Escolaridade> cmbEscolaridade;

    @FXML
    private JFXComboBox<Funcao> cmbFuncao;

    @FXML
    private JFXComboBox<Setor> cmbSetor;
    
    @FXML
    private JFXButton btnCancelarFuncionario, btnCadastrarFuncionarioFinal;
    
    public static boolean mostra = false;
	
    Timeline timeline;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		JFXTreeTableColumn<Funcionario, String> colSalario = new JFXTreeTableColumn<>("Sal�rio");
		colSalario.setPrefWidth(150);
		colSalario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().salarioProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colFuncao = new JFXTreeTableColumn<>("Fun��o");
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
		
		tblFuncionarios.getColumns().setAll(colId, colNome, colRg, colCpf, colCidade, colSalario, colFuncao, colSetor);
		tblFuncionarios.setRoot(root);
		tblFuncionarios.setShowRoot(false);
		
		btnCadastrarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Estado Civil
		ObservableList<EstadoCivil> obsListaEstadoCivil = FXCollections.observableArrayList();
		obsListaEstadoCivil.addAll(PessoaFisicaDAO.getEstadosCivis());
		
		cmbEstadoCivil.setItems(obsListaEstadoCivil);
		
		// Populando combobox G�nero
		ObservableList<Genero> obsListaGenero = FXCollections.observableArrayList();
		obsListaGenero.addAll(PessoaFisicaDAO.getGeneros());
		
		cmbGenero.setItems(obsListaGenero);
		
		// Populando combobox Escolaridade
		ObservableList<Escolaridade> obsListaEscolaridade = FXCollections.observableArrayList();
		obsListaEscolaridade.addAll(PessoaFisicaDAO.getEscolaridades());
		
		cmbEscolaridade.setItems(obsListaEscolaridade);
		
		// Populando combobox Fun��o
		ObservableList<Funcao> obsListaFuncao = FXCollections.observableArrayList();
		obsListaFuncao.addAll(FuncionarioDAO.getFuncoes());
		
		cmbFuncao.setItems(obsListaFuncao);
		
		// Populando combobox Setor
		ObservableList<Setor> obsListaSetor = FXCollections.observableArrayList();
		obsListaSetor.addAll(FuncionarioDAO.getSetores());
		
		cmbSetor.setItems(obsListaSetor);
	}
}