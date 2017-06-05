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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Endereco;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Fornecedor;
import model.bean.Funcao;
import model.bean.Cliente;
import model.bean.Genero;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Setor;
import model.bean.Telefone;
import model.dao.ClienteDAO;
import model.dao.FornecedorDAO;
import model.dao.PessoaFisicaDAO;

public class GerenciarFornecedoresController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Fornecedor> tblFornecedores;

    @FXML
    private JFXButton btnExcluirFornecedor;

    @FXML
    private JFXButton btnAlterarFornecedor;

    @FXML
    private JFXButton btnVisualizarFornecedor;

    @FXML
    private JFXButton btnCadastrarFornecedor;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtRazaoSocial;

    @FXML
    private JFXTextField txtCnpj;

    @FXML
    private JFXTextField txtInscricaoEstadual;

    @FXML
    private JFXTextField txtNomeFantasia;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXButton btnAdicionarTelefone;

    @FXML
    private JFXButton btnRemoverTelefone;

    @FXML
    private JFXComboBox<String> cmbTelefones;
    
    @FXML
    private JFXButton btnCancelarFornecedor, btnCadastrarFornecedorFinal;
    
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
		
		JFXTreeTableColumn<Fornecedor, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colNomeFantasia = new JFXTreeTableColumn<>("Nome Fantasia");
		colNomeFantasia.setPrefWidth(150);
		colNomeFantasia.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().nome_fantasiaProperty();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colCnpj = new JFXTreeTableColumn<>("CNPJ");
		colCnpj.setPrefWidth(150);
		colCnpj.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().cnpjProperty();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colInscricaoEstadual = new JFXTreeTableColumn<>("Inscrição Estadual");
		colInscricaoEstadual.setPrefWidth(150);
		colInscricaoEstadual.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().inscricao_estadualProperty();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colRazaoSocial = new JFXTreeTableColumn<>("Razão Social");
		colRazaoSocial.setPrefWidth(150);
		colRazaoSocial.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().razao_socialProperty();
			}
		});
		
		ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();
		fornecedores.addAll(FornecedorDAO.listar());
		TreeItem<Fornecedor> root = new RecursiveTreeItem<Fornecedor>(fornecedores, RecursiveTreeObject::getChildren);
		
		tblFornecedores.getColumns().setAll(colId, colNomeFantasia, colCnpj, colInscricaoEstadual, colRazaoSocial);
		tblFornecedores.setRoot(root);
		tblFornecedores.setShowRoot(false);
		
		btnCadastrarFornecedor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarFornecedor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Adicionando / Removendo Telefones
		ArrayList<String> lstTelefones = new ArrayList<String>();
				
		btnAdicionarTelefone.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String textoTelefone = txtTelefone.getText().trim();
				
				if(!textoTelefone.isEmpty()) {
					lstTelefones.add(textoTelefone);
					
					atualizaTelefones(lstTelefones);
					
					txtTelefone.setText("");
				}
			}
		});
		
		btnRemoverTelefone.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(lstTelefones.size() != 0) {
					lstTelefones.remove(lstTelefones.size() - 1);
					
					atualizaTelefones(lstTelefones);
				}
			}
		});
		
		btnCadastrarFornecedorFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				ArrayList<Telefone> telefones = new ArrayList<Telefone>();
				
				for(String telefone : cmbTelefones.getItems()) {
					Telefone tel = new Telefone(0, telefone);
					
					telefones.add(tel);
				}
				
				Fornecedor fornecedor = new Fornecedor(0, txtEmail.getText().trim(), txtRazaoSocial.getText().trim(), txtCnpj.getText().trim(), txtInscricaoEstadual.getText().trim(), txtNomeFantasia.getText().trim(), 1, telefones);
			
				if(FornecedorDAO.cadastrar(fornecedor)) {
					mensagem.show("Cliente cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar cliente!", 2000);
				}
			}
		});
	}
	
	protected void atualizaTelefones(ArrayList<String> lstTelefones) {
		cmbTelefones.valueProperty().set(null);
		
		if(lstTelefones.size() != 0) {
			ObservableList<String> obsListaTelefones = FXCollections.observableArrayList();
			obsListaTelefones.addAll(lstTelefones);
			
			cmbTelefones.setItems(obsListaTelefones);
			
			if(!cmbTelefones.isVisible()) {
				cmbTelefones.setVisible(true);
			}
		} else {
			if(cmbTelefones.isVisible()) {
				cmbTelefones.setVisible(false);
			}
		}
	}
}