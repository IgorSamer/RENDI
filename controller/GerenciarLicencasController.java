package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import model.bean.Licenca;

public class GerenciarLicencasController implements Initializable {
	
	@FXML
    private JFXTreeTableView<Licenca> tblOrdemCompra;

    @FXML
    private JFXButton btnExcluirOrdemCompra;

    @FXML
    private JFXButton btnAlterarOrdemCompra;

    @FXML
    private JFXButton btnVisualizarOrdemCompra;

    @FXML
    private JFXButton btnCadastrarOrdemCompra;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Licenca, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(100);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Licenca, String> parametro) {
				return parametro.getValue().getValue().idProperty().asString();
			}
		});
		
		tblOrdemCompra.getColumns().setAll(colId);
	}
}