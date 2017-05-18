package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GerenciarFuncionariosController implements Initializable {
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<?> tblFuncionarios;

    @FXML
    private JFXButton btnExcluirFuncionario;

    @FXML
    private JFXButton btnAlterarFuncionario;

    @FXML
    private JFXButton btnVisualizarFuncionario;

    @FXML
    private JFXButton btnCadastrarFuncionario;
    
    @FXML
    private JFXButton btnCancelarFuncionario, btnCadastrarFuncionarioFinal;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnCadastrarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnCancelarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				funCadastrar.setVisible(false);
				funGerenciar.setVisible(true);
			}
		});
	}
}