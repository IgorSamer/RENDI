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
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
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
import model.bean.Produto;
import model.bean.Setor;
import model.bean.UnidadeMedida;
import model.dao.FuncionarioDAO;
import model.dao.ProdutoDAO;

public class GerenciarEstoqueController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Produto> tblEstoque;
	
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
    private JFXButton btnImagem;
    
    @FXML
    private ImageView imgProdutoImg;
    
    @FXML
    private JFXButton btnCancelarEstoque, btnCadastrarEstoqueFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    @FXML
    private ImageView imgProduto;
    
    private File arquivo;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarEstoque.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				
			}
		});
		
		JFXTreeTableColumn<Produto, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Produto, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Produto, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Produto, String> param) {
				return param.getValue().getValue().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Produto, String> colPreco = new JFXTreeTableColumn<>("Preço");
		colPreco.setPrefWidth(150);
		colPreco.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Produto, String> param) {
				return param.getValue().getValue().precoProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Produto, String> colQuantidade = new JFXTreeTableColumn<>("Quantidade");
		colQuantidade.setPrefWidth(150);
		colQuantidade.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Produto, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Produto, String> param) {
				return param.getValue().getValue().quantidadeProperty().asString();
			}
		});
		
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		produtos.addAll(ProdutoDAO.listar());
		TreeItem<Produto> root = new RecursiveTreeItem<Produto>(produtos, RecursiveTreeObject::getChildren);
		
		tblEstoque.getColumns().setAll(colId, colNome, colPreco, colQuantidade);
		tblEstoque.setRoot(root);
		tblEstoque.setShowRoot(false);
		
		tblEstoque.setRowFactory(tv -> {
			JFXTreeTableRow<Produto> linha = new JFXTreeTableRow<>();
			
			linha.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount() == 2 && !linha.isEmpty()) {
						String fotoProduto = tblEstoque.getSelectionModel().getSelectedItem().getValue().getFoto();
						
						Image fotoProdutoImg = new Image(getClass().getResource(Produto.getCaminhoFoto(fotoProduto)).toExternalForm());
						
						imgProduto.setImage(fotoProdutoImg);
						imgProduto.setFitWidth(150);
						imgProduto.setFitHeight(150);
						
						DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
				        
				        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
				        
			            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
			            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
			            divTimeline.play();
					}
				}
			});
			
			return linha;
		});
		
		tblEstoque.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Produto>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<Produto>> arg0, TreeItem<Produto> arg1,
					TreeItem<Produto> arg2) {
				if(splConteudo.getDividers().get(0).positionProperty().get() > 0.7) {
					KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), 1.0);
		            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
		            divTimeline.play();
				}
			}
		});
		
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
		
		btnImagem.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				FileChooser.ExtensionFilter arquivoFiltro = new FileChooser.ExtensionFilter("Apenas Imagens", "*.jpg", "*.jpeg", "*.png");
				
				FileChooser fcEscolher = new FileChooser();
				fcEscolher.setTitle("Escolher arquivo");
				fcEscolher.getExtensionFilters().add(arquivoFiltro);
				
				arquivo = fcEscolher.showOpenDialog(null);
				
				if(arquivo != null) {
					Image imagemAnexo = new Image(arquivo.toURI().toString());
					
					imgProdutoImg.setImage(imagemAnexo);
				}
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
		            	
		            	ImageIO.write(imagemSla, imagemExtensao, new File("C:\\Users\\Igor PC\\workspace\\RENDI\\src\\view\\img\\produtos\\" + imagemNomeNovo + "." + imagemExtensao));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
				
				Produto produto = new Produto(0, txtNome.getText().trim(), txtDescricao.getText().trim(), Double.valueOf(txtPreco.getText().trim()), Float.valueOf(txtQuantidade.getText().trim()), imagemNomeNovoFinal, new Setor(cmbSetor.getSelectionModel().getSelectedItem().getId(), null), new UnidadeMedida(cmbUnidadeMedida.getSelectionModel().getSelectedItem().getId(), null), new Funcionario(PainelController.idFuncionario));
				
				if(ProdutoDAO.cadastrar(produto)) {
					mensagem.show("Produto cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar produto!", 2000);
				}
			}
		});
	}
}