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
import model.bean.Cliente;
import model.bean.Funcionario;
import model.bean.Servico;
import model.bean.ServicoVenda;
import model.dao.ClienteDAO;
import model.dao.ServicoDAO;

public class GerenciarServicosController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<ServicoVenda> tblServicos;
	
	@FXML
    private JFXButton btnPesquisarServico;

    @FXML
    private JFXButton btnAlterarServico;

    @FXML
    private JFXButton btnVisualizarServico;

    @FXML
    private JFXButton btnCadastrarServico;
    
    @FXML
    private JFXButton btnExcluirServico;
    
    @FXML
    private JFXTextField txtPlaca;
    
    @FXML
    private JFXTextField txtQuilometragem;

    @FXML
    private JFXComboBox<Servico> cmbServico;
    
    @FXML
    private JFXComboBox<Cliente> cmbCliente;

    @FXML
    private JFXButton btnCancelarServico, btnCadastrarServicoFinal;
    
    @FXML
    private SplitPane splConteudo;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarServico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colData = new JFXTreeTableColumn<>("Data");
		colData.setPrefWidth(150);
		colData.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().dataProperty();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colPlaca = new JFXTreeTableColumn<>("Placa");
		colPlaca.setPrefWidth(150);
		colPlaca.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().placaProperty();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colQuilometragem = new JFXTreeTableColumn<>("Quilometragem");
		colQuilometragem.setPrefWidth(150);
		colQuilometragem.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().quilometragemProperty().asString();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colServico = new JFXTreeTableColumn<>("Serviço");
		colServico.setPrefWidth(150);
		colServico.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().getServico().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colCliente = new JFXTreeTableColumn<>("Cliente");
		colCliente.setPrefWidth(150);
		colCliente.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return Bindings.concat(param.getValue().getValue().getCliente().getPessoa().getNome(), " ", param.getValue().getValue().getCliente().getPessoa().getSobrenome());
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colFuncionario = new JFXTreeTableColumn<>("Funcionário");
		colFuncionario.setPrefWidth(150);
		colFuncionario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return Bindings.concat(param.getValue().getValue().getFuncionario().getPessoa().getNome(), " ", param.getValue().getValue().getFuncionario().getPessoa().getSobrenome());
			}
		});
		
		ObservableList<ServicoVenda> servicos = FXCollections.observableArrayList();
		servicos.addAll(ServicoDAO.getServicosVendas());
		TreeItem<ServicoVenda> root = new RecursiveTreeItem<ServicoVenda>(servicos, RecursiveTreeObject::getChildren);
		
		tblServicos.getColumns().setAll(colId, colData, colPlaca, colQuilometragem, colServico, colCliente, colFuncionario);
		tblServicos.setRoot(root);
		tblServicos.setShowRoot(false);
		
		btnCadastrarServico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarServico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		// Populando combobox Serviço
		ObservableList<Servico> obsListaServico = FXCollections.observableArrayList();
		obsListaServico.addAll(ServicoDAO.listar());
		
		cmbServico.setItems(obsListaServico);
		
		// Populando combobox Cliente
		ObservableList<Cliente> obsListaCliente = FXCollections.observableArrayList();
		obsListaCliente.addAll(ClienteDAO.listar());
		
		cmbCliente.setItems(obsListaCliente);
		
		btnCadastrarServicoFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				ServicoVenda servico = new ServicoVenda(0, formatoData.format(new Date()), txtPlaca.getText().trim(), Double.valueOf(txtQuilometragem.getText().trim()), new Servico(cmbServico.getSelectionModel().getSelectedItem().getId()), new Funcionario(PainelController.idFuncionario), new Cliente(cmbCliente.getSelectionModel().getSelectedItem().getId()));
			
				if(ServicoDAO.cadastrar(servico)) {
					mensagem.show("Serviço cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar serviço!", 2000);
				}
			}
		});
	}
}