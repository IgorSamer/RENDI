package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Bico;
import model.bean.Cliente;
import model.bean.CombustivelVenda;
import model.bean.Funcionario;
import model.dao.ClienteDAO;
import model.dao.TanqueDAO;

public class GerenciarAbastecimentosController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<CombustivelVenda> tblAbastecimentos;
	
	@FXML
    private JFXButton btnPesquisarAbastecimento;

    @FXML
    private JFXButton btnAlterarAbastecimento;

    @FXML
    private JFXButton btnVisualizarAbastecimento;

    @FXML
    private JFXButton btnCadastrarAbastecimento;
    
    @FXML
    private JFXButton btnExcluirAbastecimento;
    
    @FXML
    private JFXTextField txtLitros;

    @FXML
    private JFXComboBox<Cliente> cmbCliente;

    @FXML
    private JFXComboBox<Bico> cmbBico;

    @FXML
    private JFXButton btnCancelarAbastecimento, btnCadastrarAbastecimentoFinal;
    
    @FXML
    private SplitPane splConteudo;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarAbastecimento.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colData = new JFXTreeTableColumn<>("Data");
		colData.setPrefWidth(150);
		colData.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().dataProperty();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colLitros = new JFXTreeTableColumn<>("Litros");
		colLitros.setPrefWidth(150);
		colLitros.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().litrosProperty().asString();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colStatus = new JFXTreeTableColumn<>("Status");
		colStatus.setPrefWidth(150);
		colStatus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().statusProperty().asString();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colCliente = new JFXTreeTableColumn<>("Cliente");
		colCliente.setPrefWidth(150);
		colCliente.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return Bindings.concat(param.getValue().getValue().getCliente().getPessoa().getNome(), " ", param.getValue().getValue().getCliente().getPessoa().getSobrenome());
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colFuncionario = new JFXTreeTableColumn<>("Funcionário");
		colFuncionario.setPrefWidth(150);
		colFuncionario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return Bindings.concat(param.getValue().getValue().getFuncionario().getPessoa().getNome(), " ", param.getValue().getValue().getFuncionario().getPessoa().getSobrenome());
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colBico = new JFXTreeTableColumn<>("Bico");
		colBico.setPrefWidth(150);
		colBico.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().getBico().idProperty().asString();
			}
		});
		
		ObservableList<CombustivelVenda> abastecimentos = FXCollections.observableArrayList();
		abastecimentos.addAll(TanqueDAO.getAbastecimentos());
		TreeItem<CombustivelVenda> root = new RecursiveTreeItem<CombustivelVenda>(abastecimentos, RecursiveTreeObject::getChildren);
		
		tblAbastecimentos.getColumns().setAll(colId, colData, colLitros, colStatus, colCliente, colFuncionario, colBico);
		tblAbastecimentos.setRoot(root);
		tblAbastecimentos.setShowRoot(false);
		
		btnCadastrarAbastecimento.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarAbastecimento.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Cliente
		ObservableList<Cliente> obsListaCliente = FXCollections.observableArrayList();
		obsListaCliente.addAll(ClienteDAO.listar());
		
		cmbCliente.setItems(obsListaCliente);
		
		// FxUtilTest.autoCompleteComboBoxPlus(cmbCliente, (typedText, itemToCompare) -> itemToCompare.getPessoa().getNome().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getPessoa().getSobrenome().toLowerCase().contains(typedText.toLowerCase()));
		
		// Populando combobox Bico
		ObservableList<Bico> obsListaBico = FXCollections.observableArrayList();
		obsListaBico.addAll(TanqueDAO.getBicos());
		
		cmbBico.setItems(obsListaBico);
		
		btnCadastrarAbastecimentoFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				CombustivelVenda abastecimento = new CombustivelVenda(0, formatoData.format(new Date()), Float.valueOf(txtLitros.getText().trim()), 1, new Cliente(cmbCliente.getSelectionModel().getSelectedItem().getId()), new Funcionario(PainelController.idFuncionario), new Bico(cmbBico.getSelectionModel().getSelectedItem().getId()));
			
				if(TanqueDAO.cadastrar(abastecimento)) {
					mensagem.show("Venda cadastrada com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar venda!", 2000);
				}
			}
		});
	}
}