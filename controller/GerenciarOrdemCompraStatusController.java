package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.bean.OrdemCompraStatus;
import model.dao.OrdemCompraDAO;

public class GerenciarOrdemCompraStatusController implements Initializable {
	@FXML
	private JFXComboBox<OrdemCompraStatus> cmbStatus;
	
	@FXML
	private JFXTextArea txtObservacoes;
	
	@FXML
	private JFXButton btnSalvar, btnCancelar;
	
	public static Integer idOrdemCompra;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Populando combobox Status
		ObservableList<OrdemCompraStatus> obsListaStatus = FXCollections.observableArrayList();
		obsListaStatus.addAll(OrdemCompraDAO.getStatus());
		
		cmbStatus.setItems(obsListaStatus);
		
		btnSalvar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(OrdemCompraDAO.atualizarStatus(idOrdemCompra, cmbStatus.getSelectionModel().getSelectedItem().getId(), txtObservacoes.getText().trim())) {
					Stage stage = (Stage) btnCancelar.getScene().getWindow();
				    stage.close();
				}
			}
		});
		
		btnCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Stage stage = (Stage) btnCancelar.getScene().getWindow();
			    stage.close();
			}
		});
	}
}