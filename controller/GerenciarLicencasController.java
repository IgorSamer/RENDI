package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.bean.Funcionario;
import model.bean.Licenca;

public class GerenciarLicencasController implements Initializable {

    @FXML
    private Text lblGerenciarLicencas;

    @FXML
    private JFXTreeTableView<Licenca> tblLicencas;

    @FXML
    private JFXButton btnExcluirFuncionario;

    @FXML
    private JFXButton btnAlterarFuncionario;

    @FXML
    private JFXButton btnVisualizarFuncionario;

    @FXML
    private JFXButton btnCadastrarFuncionario;

    @FXML
    private JFXTextField txtIdLicenca;

    @FXML
    private JFXComboBox<?> cmbTIpoLicenca;

    @FXML
    private JFXComboBox<?> cmbOrgaoExpedidor;

    @FXML
    private JFXDatePicker dateDataEmissao;

    @FXML
    private JFXDatePicker dateDataValidade;

    @FXML
    private JFXComboBox<?> cmbServico;

    @FXML
    private JFXTextField Contato;

    @FXML
    private JFXButton btnInserirDoc;

    @FXML
    private JFXTextArea txtAreaObs;

    @FXML
    private JFXButton btnCadastrarFuncionarioFinal;

    @FXML
    private JFXButton btnCancelarFuncionario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Licenca, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Licenca, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Licenca, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		

		
	});

	}
}
	