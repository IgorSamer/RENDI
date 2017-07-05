package controller;

import java.net.URL;
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
import model.bean.Servico;
import model.bean.Setor;
import model.dao.ServicoDAO;

public class GerenciarServicosControleController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Servico> tblServicos;
	
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
    private JFXTextField txtNome;
    
    @FXML
    private JFXTextField txtPreco;

    @FXML
    private JFXComboBox<Setor> cmbSetor;

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
		
		JFXTreeTableColumn<Servico, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Servico, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Servico, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Servico, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Servico, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Servico, String> param) {
				return param.getValue().getValue().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Servico, String> colPreco = new JFXTreeTableColumn<>("Preço");
		colPreco.setPrefWidth(150);
		colPreco.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Servico, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Servico, String> param) {
				return Bindings.concat("R$", param.getValue().getValue().precoProperty().asString());
			}
		});
		
		JFXTreeTableColumn<Servico, String> colSetor = new JFXTreeTableColumn<>("Setor");
		colSetor.setPrefWidth(150);
		colSetor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Servico, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Servico, String> param) {
				return param.getValue().getValue().getSetor().nomeProperty();
			}
		});
		
		ObservableList<Servico> servicos = FXCollections.observableArrayList();
		servicos.addAll(ServicoDAO.listar());
		TreeItem<Servico> root = new RecursiveTreeItem<Servico>(servicos, RecursiveTreeObject::getChildren);
		
		tblServicos.getColumns().setAll(colId, colNome, colPreco, colSetor);
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
		
		// Populando combobox Setor
		ObservableList<Setor> obsListaSetor = FXCollections.observableArrayList();
		obsListaSetor.addAll(ServicoDAO.getSetores());
		
		cmbSetor.setItems(obsListaSetor);
		
		btnCadastrarServicoFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				Servico servico = new Servico(0, txtNome.getText().trim(), Double.valueOf(txtPreco.getText().trim()), new Setor(cmbSetor.getSelectionModel().getSelectedItem().getId()));
			
				if(ServicoDAO.cadastrar(servico)) {
					mensagem.show("Serviço cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar serviço!", 2000);
				}
			}
		});
	}
}