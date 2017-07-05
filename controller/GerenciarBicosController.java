package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.bean.Bico;
import model.bean.Bomba;
import model.bean.TanqueReparticao;
import model.dao.TanqueDAO;

public class GerenciarBicosController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	//@FXML
    //private JFXTreeTableView<Funcionario> tblFuncionarios;
	
    @FXML
    private JFXButton btnAlterarBico;

    @FXML
    private JFXButton btnVisualizarBico;

    @FXML
    private JFXButton btnCadastrarBico;
    
    @FXML
    private JFXButton btnExcluirBico;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXComboBox<Bomba> cmbBomba;
    
    @FXML
    private JFXComboBox<TanqueReparticao> cmbReparticao;
    
    @FXML
    private JFXButton btnCancelarBico, btnCadastrarBicoFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    @FXML
    private HBox areaBicos;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarBico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		btnCadastrarBico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarBico.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		//ImageView bombaImg = new ImageView(new Image(getClass().getResource("/view/img/auxiliares/Bomba.png").toExternalForm()));
		
		//for(Bomba bomb : TanqueDAO.getBombas()) {
		//	areaBicos.getStyleClass().add("bomba");
		//}
		
		// Populando combobox Bomba
		ObservableList<Bomba> obsListaBomba = FXCollections.observableArrayList();
		obsListaBomba.addAll(TanqueDAO.getBombas());
		
		cmbBomba.setItems(obsListaBomba);
		
		cmbBomba.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!cmbBomba.getSelectionModel().isEmpty()) {
					cmbReparticao.setDisable(false);
					
					ObservableList<TanqueReparticao> obsListaReparticao = FXCollections.observableArrayList();
					obsListaReparticao.addAll(TanqueDAO.getReparticoes(cmbBomba.getSelectionModel().getSelectedItem().getTanque().getId()));
					
					cmbReparticao.valueProperty().set(null);
					cmbReparticao.setItems(obsListaReparticao);
				}
			}
		});
		
		btnCadastrarBicoFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				Bico bico = new Bico(0, txtNome.getText().trim(), new Bomba(cmbBomba.getSelectionModel().getSelectedItem().getId()), new TanqueReparticao(cmbReparticao.getSelectionModel().getSelectedItem().getId()));

				if(TanqueDAO.cadastrar(bico)) {
					mensagem.show("Bico cadastrado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar bico!", 2000);
				}
			}
		});
	}
}