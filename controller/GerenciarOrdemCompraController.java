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
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
import model.bean.Funcionario;
import model.bean.Cliente;
import model.bean.Genero;
import model.bean.OrdemCompra;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Produto;
import model.bean.ProdutoOrdemCompra;
import model.bean.Setor;
import model.bean.Telefone;
import model.bean.TipoPagamento;
import model.dao.ClienteDAO;
import model.dao.FornecedorDAO;
import model.dao.FuncionarioDAO;
import model.dao.OrdemCompraDAO;
import model.dao.PessoaFisicaDAO;
import model.dao.ProdutoDAO;
import model.dao.TipoPagamentoDAO;

public class GerenciarOrdemCompraController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<OrdemCompra> tblOrdensCompra;

    @FXML
    private JFXButton btnPesquisarOrdemCompra;

    @FXML
    private JFXButton btnAlterarOrdemCompra;

    @FXML
    private JFXButton btnVisualizarOrdemCompra;

    @FXML
    private JFXButton btnCadastrarOrdemCompra;
    
    @FXML
    private JFXButton btnExcluirOrdemCompra;

    @FXML
    private JFXDatePicker datPrevista;

    @FXML
    private JFXTextField txtParcelas;

    @FXML
    private JFXTextField txtDistanciaPagamento;

    @FXML
    private JFXComboBox<Fornecedor> cmbFornecedor;
    
    @FXML
    private JFXComboBox<TipoPagamento> cmbTipoPagamento;
    
    @FXML
    private JFXComboBox<Produto> cmbProduto;
    
    @FXML
    private JFXButton btnAdicionarProduto;
    
    @FXML
    private JFXButton btnRemoverProduto;
    
    @FXML
    private JFXTreeTableView<ProdutoOrdemCompra> tblProdutos;
    
    @FXML
    private Label lblSubtotal;
    
    @FXML
    private JFXButton btnCancelarOrdemCompra, btnCadastrarOrdemCompraFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    private ArrayList<ProdutoOrdemCompra> lstProdutos = new ArrayList<ProdutoOrdemCompra>();
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarOrdemCompra.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colFornecedor = new JFXTreeTableColumn<>("Fornecedor");
		colFornecedor.setPrefWidth(150);
		colFornecedor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return param.getValue().getValue().getFornecedor().nome_fantasiaProperty();
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colFuncionario = new JFXTreeTableColumn<>("Funcionário");
		colFuncionario.setPrefWidth(150);
		colFuncionario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return Bindings.concat(param.getValue().getValue().getFuncionario().getPessoa().nomeProperty(), " ", param.getValue().getValue().getFuncionario().getPessoa().sobrenomeProperty());
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colDataPrevista = new JFXTreeTableColumn<>("Data Prevista");
		colDataPrevista.setPrefWidth(150);
		colDataPrevista.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return param.getValue().getValue().data_previstaProperty();
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colValor = new JFXTreeTableColumn<>("Valor");
		colValor.setPrefWidth(150);
		colValor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return param.getValue().getValue().valorProperty().asString();
			}
		});
		
		JFXTreeTableColumn<OrdemCompra, String> colStatus = new JFXTreeTableColumn<>("Status");
		colStatus.setPrefWidth(150);
		colStatus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrdemCompra, String> param) {
				return param.getValue().getValue().statusProperty().asString();
			}
		});
		
		ObservableList<OrdemCompra> ordensCompra = FXCollections.observableArrayList();
		ordensCompra.addAll(OrdemCompraDAO.listar());
		TreeItem<OrdemCompra> root = new RecursiveTreeItem<OrdemCompra>(ordensCompra, RecursiveTreeObject::getChildren);

		tblOrdensCompra.getColumns().setAll(colId, colFornecedor, colFuncionario, colDataPrevista, colValor, colStatus);
		tblOrdensCompra.setRoot(root);
		tblOrdensCompra.setShowRoot(false);
		

		btnCadastrarOrdemCompra.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarOrdemCompra.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Fornecedor
		ObservableList<Fornecedor> obsListaFornecedor = FXCollections.observableArrayList();
		obsListaFornecedor.addAll(FornecedorDAO.listar());
		
		cmbFornecedor.setItems(obsListaFornecedor);
		
		// Populando combobox Tipo de Pagamento
		ObservableList<TipoPagamento> obsListaTipoPagamento = FXCollections.observableArrayList();
		obsListaTipoPagamento.addAll(TipoPagamentoDAO.getTiposPagamento());
		
		cmbTipoPagamento.setItems(obsListaTipoPagamento);
		
		// Populando combobox Produto
		ObservableList<Produto> obsListaProduto = FXCollections.observableArrayList();
		obsListaProduto.addAll(ProdutoDAO.listar());
		
		cmbProduto.setItems(obsListaProduto);
		
		btnAdicionarProduto.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(!cmbProduto.getSelectionModel().isEmpty()) {
					boolean existe = false;
					
					for(ProdutoOrdemCompra prod : lstProdutos) {
						if(cmbProduto.getSelectionModel().getSelectedItem().getId().equals(prod.getProduto().getId())) {
							prod.setQuantidade(prod.getQuantidade() + 1);
							
							existe = true;
						}
					}
					
					if(!existe) {
						lstProdutos.add(new ProdutoOrdemCompra(0, 1, new OrdemCompra(0), cmbProduto.getSelectionModel().getSelectedItem()));
					}
					
					atualizaProdutos();
				}
			}
		});
		
		btnRemoverProduto.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				ProdutoOrdemCompra prodRemover = null;
				
				for(ProdutoOrdemCompra prod : lstProdutos) {
					if(cmbProduto.getSelectionModel().getSelectedItem().getId().equals(prod.getProduto().getId())) {
						if(prod.getQuantidade() > 1) {
							prod.setQuantidade(prod.getQuantidade() - 1);
							
							atualizaProdutos();
						} else if(prod.getQuantidade() == 1) {
							prodRemover = prod;
						}
					}
				}
				
				if(prodRemover != null) {
					lstProdutos.remove(prodRemover);
					
					atualizaProdutos();
				}
			}
		});
		
		JFXTreeTableColumn<ProdutoOrdemCompra, String> colIdProdutos = new JFXTreeTableColumn<>("Id");
		colIdProdutos.setPrefWidth(150);
		colIdProdutos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProdutoOrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ProdutoOrdemCompra, String> param) {
				return param.getValue().getValue().getProduto().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<ProdutoOrdemCompra, String> colNomeProdutos = new JFXTreeTableColumn<>("Nome");
		colNomeProdutos.setPrefWidth(150);
		colNomeProdutos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProdutoOrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ProdutoOrdemCompra, String> param) {
				return param.getValue().getValue().getProduto().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<ProdutoOrdemCompra, String> colDescricaoProdutos = new JFXTreeTableColumn<>("Descrição");
		colDescricaoProdutos.setPrefWidth(150);
		colDescricaoProdutos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProdutoOrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ProdutoOrdemCompra, String> param) {
				return param.getValue().getValue().getProduto().descricaoProperty();
			}
		});
		
		JFXTreeTableColumn<ProdutoOrdemCompra, String> colPrecoProdutos = new JFXTreeTableColumn<>("Preço");
		colPrecoProdutos.setPrefWidth(150);
		colPrecoProdutos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProdutoOrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ProdutoOrdemCompra, String> param) {
				return param.getValue().getValue().getProduto().precoProperty().asString();
			}
		});
		
		JFXTreeTableColumn<ProdutoOrdemCompra, String> colQuantidadeProdutos = new JFXTreeTableColumn<>("Quantidade");
		colQuantidadeProdutos.setPrefWidth(150);
		colQuantidadeProdutos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProdutoOrdemCompra, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ProdutoOrdemCompra, String> param) {
				return param.getValue().getValue().quantidadeProperty().asString();
			}
		});
		
		ObservableList<ProdutoOrdemCompra> produtos = FXCollections.observableArrayList();
		produtos.addAll(lstProdutos);
		TreeItem<ProdutoOrdemCompra> rootProdutos = new RecursiveTreeItem<ProdutoOrdemCompra>(produtos, RecursiveTreeObject::getChildren);

		tblProdutos.getColumns().setAll(colIdProdutos, colNomeProdutos, colDescricaoProdutos, colPrecoProdutos, colQuantidadeProdutos);
		tblProdutos.setRoot(rootProdutos);
		tblProdutos.setShowRoot(false);
		
		btnCadastrarOrdemCompraFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				OrdemCompra ordemCompra = new OrdemCompra(0, formatoData.format(new Date()), String.valueOf(datPrevista.getValue()), 1, null, Double.valueOf(lblSubtotal.getText()), Integer.valueOf(txtParcelas.getText().trim()), Integer.valueOf(txtDistanciaPagamento.getText().trim()), new Fornecedor(cmbFornecedor.getSelectionModel().getSelectedItem().getId()), new Funcionario(PainelController.idFuncionario), new TipoPagamento(cmbTipoPagamento.getSelectionModel().getSelectedItem().getId(), null), lstProdutos);
				
				if(OrdemCompraDAO.cadastrar(ordemCompra)) {
					mensagem.show("Ordem de compra cadastrada com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar ordem de compra!", 2000);
				}
			}
		});
	}

	protected void atualizaProdutos() {
		ObservableList<ProdutoOrdemCompra> produtos = FXCollections.observableArrayList();
		produtos.addAll(lstProdutos);
		TreeItem<ProdutoOrdemCompra> rootProdutos = new RecursiveTreeItem<ProdutoOrdemCompra>(produtos, RecursiveTreeObject::getChildren);
		
		tblProdutos.setRoot(rootProdutos);
		
		float subtotal = 0;
		
		for(ProdutoOrdemCompra prod : lstProdutos) {
			subtotal += prod.getQuantidade() * prod.getProduto().getPreco();
		}
		
		lblSubtotal.setText(String.valueOf(subtotal));
	}
}