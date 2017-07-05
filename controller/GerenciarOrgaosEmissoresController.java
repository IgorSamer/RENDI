package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import model.bean.OrgaoEmissor;
import model.dao.LicencaDAO;

public class GerenciarOrgaosEmissoresController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<OrgaoEmissor> tblOrgaosEmissores;
	
	@FXML
    private JFXButton btnPesquisarOrgaoEmissor;

    @FXML
    private JFXButton btnAlterarOrgaoEmissor;

    @FXML
    private JFXButton btnVisualizarOrgaoEmissor;

    @FXML
    private JFXButton btnCadastrarOrgaoEmissor;
    
    @FXML
    private JFXButton btnExcluirOrgaoEmissor;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXButton btnCancelarOrgaoEmissor, btnCadastrarOrgaoEmissorFinal;
    
    @FXML
    private SplitPane splConteudo;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarOrgaoEmissor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		JFXTreeTableColumn<OrgaoEmissor, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrgaoEmissor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrgaoEmissor, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<OrgaoEmissor, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<OrgaoEmissor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<OrgaoEmissor, String> param) {
				return param.getValue().getValue().nomeProperty();
			}
		});
		
		ObservableList<OrgaoEmissor> servicos = FXCollections.observableArrayList();
		servicos.addAll(LicencaDAO.getOrgaosEmissores());
		TreeItem<OrgaoEmissor> root = new RecursiveTreeItem<OrgaoEmissor>(servicos, RecursiveTreeObject::getChildren);
		
		tblOrgaosEmissores.getColumns().setAll(colId, colNome);
		tblOrgaosEmissores.setRoot(root);
		tblOrgaosEmissores.setShowRoot(false);
		
		btnCadastrarOrgaoEmissor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarOrgaoEmissor.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		btnCadastrarOrgaoEmissorFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				OrgaoEmissor orgaoEmissor = new OrgaoEmissor(0, txtNome.getText().trim());
			
				if(LicencaDAO.cadastrar(orgaoEmissor)) {
					mensagem.show("Órgão emissor cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar órgão emissor!", 2000);
				}
			}
		});
	}
}