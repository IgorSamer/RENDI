package controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Endereco;
import model.bean.Fornecedor;
import model.bean.JSONException;
import model.bean.JSONObject;
import model.bean.JSONTokener;
import model.bean.Telefone;
import model.bean.TextFieldFormatter;
import model.dao.FornecedorDAO;

public class GerenciarFornecedoresController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Fornecedor> tblFornecedores;

	@FXML
    private JFXButton btnPesquisarFornecedor;
	
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
    private JFXTextField txtNomeEmpresarial;

    @FXML
    private JFXTextField txtCnpj;

    @FXML
    private JFXTextField txtNomeFantasia;
    
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
		
		JFXTreeTableColumn<Fornecedor, String> colCnpj = new JFXTreeTableColumn<>("CNPJ");
		colCnpj.setPrefWidth(150);
		colCnpj.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().cnpjProperty();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colNomeEmpresarial = new JFXTreeTableColumn<>("Nome Empresarial");
		colNomeEmpresarial.setPrefWidth(300);
		colNomeEmpresarial.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().nome_empresarialProperty();
			}
		});
		
		JFXTreeTableColumn<Fornecedor, String> colNomeFantasia = new JFXTreeTableColumn<>("Nome Fantasia");
		colNomeFantasia.setPrefWidth(300);
		colNomeFantasia.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Fornecedor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Fornecedor, String> param) {
				return param.getValue().getValue().nome_fantasiaProperty();
			}
		});
		
		ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();
		fornecedores.addAll(FornecedorDAO.listar());
		TreeItem<Fornecedor> root = new RecursiveTreeItem<Fornecedor>(fornecedores, RecursiveTreeObject::getChildren);
		
		tblFornecedores.getColumns().setAll(colId, colCnpj, colNomeEmpresarial, colNomeFantasia);
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
		
		txtCnpj.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(KeyEvent event) {
				String cnpj = txtCnpj.getText().trim();
				
				if(cnpj.length() == 14) {
					try {
						JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL("https://www.receitaws.com.br/v1/cnpj/"+ cnpj).openStream())).nextValue();
					    
					    txtEmail.setText(jo.getString("email"));
					    txtNomeEmpresarial.setText(jo.getString("nome"));
					    txtNomeFantasia.setText(jo.getString("fantasia"));
					    
					    String cep = jo.getString("cep").replace(".", "");
					    cep = cep.replace("-", "");
					    
					    JSONObject jo2 = (JSONObject) new JSONTokener(IOUtils.toString(new URL("https://viacep.com.br/ws/"+ cep +"/json").openStream())).nextValue();
					    
					    txtCep.setText(cep);
					    txtUf.setText(jo2.getString("uf"));
					    txtCidade.setText(jo2.getString("localidade"));
					    txtBairro.setText(jo2.getString("bairro"));
					    txtRua.setText(jo2.getString("logradouro"));
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		txtTelefone.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				TextFieldFormatter tff = new TextFieldFormatter();
				tff.setMask("#### ### ###");
				tff.setCaracteresValidos("0123456789");
				tff.setTf(txtTelefone);
				tff.formatter();
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
				
				Fornecedor fornecedor = new Fornecedor(0, txtEmail.getText().trim(), txtNomeEmpresarial.getText().trim(), txtCnpj.getText().trim(), txtNomeFantasia.getText().trim(), 1, telefones, new Endereco(txtUf.getText().trim(), txtCidade.getText().trim(), txtCep.getText().trim(), txtBairro.getText().trim(), txtRua.getText().trim(), Integer.valueOf(txtNumero.getText().trim())));
			
				if(FornecedorDAO.cadastrar(fornecedor)) {
					mensagem.show("Fornecedor cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar fornecedor!", 2000);
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