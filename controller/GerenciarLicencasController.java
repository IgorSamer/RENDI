package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Funcionario;
import model.bean.Licenca;
import model.bean.OrgaoEmissor;
import model.dao.LicencaDAO;

public class GerenciarLicencasController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Licenca> tblLicencas;
	
	@FXML
    private JFXButton btnPesquisarLicenca;

    @FXML
    private JFXButton btnAlterarLicenca;

    @FXML
    private JFXButton btnVisualizarLicenca;

    @FXML
    private JFXButton btnCadastrarLicenca;
    
    @FXML
    private JFXButton btnExcluirLicenca;
    
    @FXML
    private JFXTextField txtNome;
    
    @FXML
    private JFXTextArea txtDescricao;
    
    @FXML
    private JFXTextField txtProtocolo;
    
    @FXML
    private JFXDatePicker datEmissao;
    
    @FXML
    private JFXDatePicker datVencimento;
    
    @FXML
    private JFXButton btnAnexo;
    
    @FXML
    private ImageView imgAnexo;

    @FXML
    private JFXComboBox<OrgaoEmissor> cmbOrgaoEmissor;

    @FXML
    private JFXButton btnCancelarLicenca, btnCadastrarLicencaFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    private File arquivo;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarLicenca.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colDescricao = new JFXTreeTableColumn<>("Descrição");
		colDescricao.setPrefWidth(150);
		colDescricao.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().descricaoProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colProtocolo = new JFXTreeTableColumn<>("Protocolo");
		colProtocolo.setPrefWidth(150);
		colProtocolo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().protocoloProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colDataEmissao = new JFXTreeTableColumn<>("Data de Emissão");
		colDataEmissao.setPrefWidth(150);
		colDataEmissao.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().data_emissaoProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colDataVencimento = new JFXTreeTableColumn<>("Data de Vencimento");
		colDataVencimento.setPrefWidth(150);
		colDataVencimento.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().data_vencimentoProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colOrgaoEmissor = new JFXTreeTableColumn<>("Órgão Emissor");
		colOrgaoEmissor.setPrefWidth(150);
		colOrgaoEmissor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().getOrgao_emissor().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Licenca, String> colFuncionario = new JFXTreeTableColumn<>("Funcionário");
		colFuncionario.setPrefWidth(150);
		colFuncionario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return Bindings.concat(param.getValue().getValue().getFuncionario().getPessoa().nomeProperty(), " ", param.getValue().getValue().getFuncionario().getPessoa().sobrenomeProperty());
			}
		});
		
		ObservableList<Licenca> licencas = FXCollections.observableArrayList();
		licencas.addAll(LicencaDAO.listar());
		TreeItem<Licenca> root = new RecursiveTreeItem<Licenca>(licencas, RecursiveTreeObject::getChildren);
		
		tblLicencas.getColumns().setAll(colId, colNome, colDescricao, colProtocolo, colDataEmissao, colDataVencimento, colOrgaoEmissor, colFuncionario);
		tblLicencas.setRoot(root);
		tblLicencas.setShowRoot(false);
		
		btnCadastrarLicenca.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarLicenca.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		btnAnexo.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				FileChooser.ExtensionFilter arquivoFiltro = new FileChooser.ExtensionFilter("Apenas Imagens", "*.jpg", "*.jpeg", "*.png");
				
				FileChooser fcEscolher = new FileChooser();
				fcEscolher.setTitle("Escolher arquivo");
				fcEscolher.getExtensionFilters().add(arquivoFiltro);
				
				arquivo = fcEscolher.showOpenDialog(null);
				
				if(arquivo != null) {
					Image imagemAnexo = new Image(arquivo.toURI().toString());
					
					imgAnexo.setImage(imagemAnexo);
				}
			}
		});
		
		// Populando combobox Órgão Emissor
		ObservableList<OrgaoEmissor> obsListaOrgaoEmissor = FXCollections.observableArrayList();
		obsListaOrgaoEmissor.addAll(LicencaDAO.getOrgaosEmissores());
		
		cmbOrgaoEmissor.setItems(obsListaOrgaoEmissor);
		
		btnCadastrarLicencaFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				String imagemNomeNovoFinal = "";
				
				if(arquivo != null) {					
		            try {
		            	BufferedImage imagemSla = ImageIO.read(arquivo);
		            	
		            	String imagemNome = arquivo.getName();       
		            	String imagemExtensao = imagemNome.substring(imagemNome.indexOf(".") + 1, arquivo.getName().length());
		            	byte[] imagemNomeByte = imagemNome.getBytes("UTF-8");
		            	
		            	MessageDigest md = MessageDigest.getInstance("MD5");
		            	byte[] retorno = md.digest(imagemNomeByte);
		            	
		            	String imagemNomeNovo = DatatypeConverter.printHexBinary(retorno);
		            	
		            	imagemNomeNovoFinal = imagemNomeNovo + "." + imagemExtensao;
		            	
		            	ImageIO.write(imagemSla, imagemExtensao, new File("C:\\Users\\Igor PC\\workspace\\RENDI\\src\\view\\img\\anexos\\" + imagemNomeNovo + "." + imagemExtensao));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
				
				Licenca licenca = new Licenca(0, txtNome.getText().trim(), txtDescricao.getText().trim(), imagemNomeNovoFinal, txtProtocolo.getText().trim(), String.valueOf(datEmissao.getValue()), String.valueOf(datVencimento.getValue()), null, new OrgaoEmissor(cmbOrgaoEmissor.getSelectionModel().getSelectedItem().getId()), new Funcionario(PainelController.idFuncionario));
			
				if(LicencaDAO.cadastrar(licenca)) {
					mensagem.show("Licença cadastrada com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar licença!", 2000);
				}
			}
		});
	}
}