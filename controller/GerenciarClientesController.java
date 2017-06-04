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
import model.bean.Funcao;
import model.bean.Cliente;
import model.bean.Genero;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Setor;
import model.bean.Telefone;
import model.dao.ClienteDAO;
import model.dao.PessoaFisicaDAO;

public class GerenciarClientesController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Cliente> tblClientes;

    @FXML
    private JFXButton btnExcluirCliente;

    @FXML
    private JFXButton btnAlterarCliente;

    @FXML
    private JFXButton btnVisualizarCliente;

    @FXML
    private JFXButton btnCadastrarCliente;
    
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
    private JFXDatePicker datNascimento;

    @FXML
    private JFXComboBox<Genero> cmbGenero;
    
    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXButton btnAdicionarTelefone;

    @FXML
    private JFXButton btnRemoverTelefone;

    @FXML
    private JFXComboBox<String> cmbTelefones;
    
    @FXML
    private JFXButton btnCancelarCliente, btnCadastrarClienteFinal;
    
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
		
		JFXTreeTableColumn<Cliente, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Cliente, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				return Bindings.concat(param.getValue().getValue().getPessoa().nomeProperty(), " ", param.getValue().getValue().getPessoa().sobrenomeProperty());
			}
		});
		
		JFXTreeTableColumn<Cliente, String> colRg = new JFXTreeTableColumn<>("RG");
		colRg.setPrefWidth(150);
		colRg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().rgProperty();
			}
		});
		
		JFXTreeTableColumn<Cliente, String> colCpf = new JFXTreeTableColumn<>("CPF");
		colCpf.setPrefWidth(150);
		colCpf.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().cpfProperty();
			}
		});
		
		JFXTreeTableColumn<Cliente, String> colCidade = new JFXTreeTableColumn<>("Cidade");
		colCidade.setPrefWidth(150);
		colCidade.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
				return param.getValue().getValue().getPessoa().getEndereco().cidadeProperty();
			}
		});
		
		ObservableList<Cliente> Clientes = FXCollections.observableArrayList();
		Clientes.addAll(ClienteDAO.listar());
		TreeItem<Cliente> root = new RecursiveTreeItem<Cliente>(Clientes, RecursiveTreeObject::getChildren);
		
		tblClientes.getColumns().setAll(colId, colNome, colRg, colCpf, colCidade);
		tblClientes.setRoot(root);
		tblClientes.setShowRoot(false);
		
		btnCadastrarCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Gênero
		ObservableList<Genero> obsListaGenero = FXCollections.observableArrayList();
		obsListaGenero.addAll(PessoaFisicaDAO.getGeneros());
		
		cmbGenero.setItems(obsListaGenero);
		
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
		
		btnCadastrarClienteFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				ArrayList<Telefone> telefones = new ArrayList<Telefone>();
				
				for(String telefone : cmbTelefones.getItems()) {
					Telefone tel = new Telefone(0, telefone);
					
					telefones.add(tel);
				}
				
				Cliente cliente = new Cliente(0, new Pessoa(txtNome.getText().trim(), txtSobrenome.getText().trim(), String.valueOf(datNascimento.getValue()), txtEmail.getText().trim(), new Genero(cmbGenero.getSelectionModel().getSelectedItem().getId(), null), null, telefones, new PessoaFisica(txtRg.getText().trim(), txtCpf.getText().trim(), null), new Endereco(txtUf.getText().trim(), txtCidade.getText().trim(), txtCep.getText().trim(), txtBairro.getText().trim(), txtRua.getText().trim(), Integer.valueOf(txtNumero.getText().trim()))));
			
				if(ClienteDAO.cadastrar(cliente)) {
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