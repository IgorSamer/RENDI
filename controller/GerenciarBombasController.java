package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Bomba;
import model.bean.Combustivel;
import model.bean.Endereco;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Genero;
import model.bean.OrdemCompra;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Produto;
import model.bean.ProdutoOrdemCompra;
import model.bean.Setor;
import model.bean.Tanque;
import model.bean.TanqueReparticao;
import model.bean.Telefone;
import model.bean.UnidadeMedida;
import model.dao.FuncionarioDAO;
import model.dao.PessoaFisicaDAO;
import model.dao.ProdutoDAO;
import model.dao.TanqueDAO;

public class GerenciarBombasController implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	//@FXML
    //private JFXTreeTableView<Funcionario> tblFuncionarios;
	
    @FXML
    private JFXButton btnAlterarBomba;

    @FXML
    private JFXButton btnVisualizarBomba;

    @FXML
    private JFXButton btnCadastrarBomba;
    
    @FXML
    private JFXButton btnExcluirBomba;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXComboBox<Tanque> cmbTanque;
    
    @FXML
    private JFXButton btnCancelarBomba, btnCadastrarBombaFinal;
    
    @FXML
    private SplitPane splConteudo;
    
    @FXML
    private HBox areaBombas;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarBomba.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		btnCadastrarBomba.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarBomba.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
		
		ImageView bombaImg = new ImageView(new Image(getClass().getResource("/view/img/auxiliares/Bomba.png").toExternalForm()));
		
		for(Bomba bomb : TanqueDAO.getBombas()) {
			areaBombas.getStyleClass().add("bomba");
		}
		
		// Populando combobox Tanque
		ObservableList<Tanque> obsListaTanque = FXCollections.observableArrayList();
		obsListaTanque.addAll(TanqueDAO.getTanques());
		
		cmbTanque.setItems(obsListaTanque);
		
		btnCadastrarBombaFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				Bomba bomba = new Bomba(0, txtNome.getText().trim(), new Tanque(cmbTanque.getSelectionModel().getSelectedItem().getId()));

				if(TanqueDAO.cadastrar(bomba)) {
					mensagem.show("Bomba cadastrada com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao cadastrar bomba!", 2000);
				}
			}
		});
	}
}