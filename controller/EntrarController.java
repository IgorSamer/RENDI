package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bean.Funcionario;
import model.dao.FuncionarioDAO;

public class EntrarController implements Initializable {
	@FXML
	private StackPane stackPane;
	
	@FXML
    private ImageView imgLogo;
	
	@FXML
	private JFXTextField txtUsuario;
	
	@FXML
	private JFXPasswordField txtSenha;
	
	@FXML
    private JFXButton btnEntrar;
	
	@FXML
	private JFXButton btnSair;
	
	boolean entrouUsuario = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		txtSenha.toBack();
		
		FadeTransition fadeInLogo = new FadeTransition(Duration.seconds(2), imgLogo);
		fadeInLogo.setFromValue(0);
		fadeInLogo.setToValue(1);
		fadeInLogo.play();
		
		fadeInLogo.setOnFinished((e) -> {
			txtUsuario.requestFocus();
			
			ScaleTransition logoTamanho = new ScaleTransition(Duration.seconds(0.5), imgLogo);
			logoTamanho.setToX(0.7);
			logoTamanho.setToY(0.7);
			logoTamanho.play();
			
			TranslateTransition logoY = new TranslateTransition(Duration.seconds(0.5), imgLogo);
			logoY.setToY(-150);
			logoY.play();
			
			FadeTransition fadeInUsuario = new FadeTransition(Duration.seconds(0.5), txtUsuario);
			fadeInUsuario.setFromValue(0);
			fadeInUsuario.setToValue(1);
			fadeInUsuario.play();

			FadeTransition fadeInEntrar1 = new FadeTransition(Duration.seconds(0.5), btnEntrar);
			fadeInEntrar1.setFromValue(0);
			fadeInEntrar1.setToValue(1);
			fadeInEntrar1.play();
			
			FadeTransition fadeInEntrar2 = new FadeTransition(Duration.seconds(0.5), btnSair);
			fadeInEntrar2.setFromValue(0);
			fadeInEntrar2.setToValue(1);
			fadeInEntrar2.play();
		});
		
		txtUsuario.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				entrar();
			}
		});
		
		txtSenha.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				entrar();
			}
		});
		
		btnEntrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				entrar();
			}
		});
		
		btnSair.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Platform.exit();
			}
		});
	}

	protected void entrar() {
		if(!entrouUsuario) {
			String entrou = FuncionarioDAO.entrar(txtUsuario.getText().trim());
			
			if(!entrou.equals("")) {
				entrouUsuario = true;
				
				Image foto = new Image(getClass().getResource(Funcionario.getCaminhoFoto(entrou)).toExternalForm());
				
				imgLogo.setImage(foto);
				imgLogo.setFitWidth(300);
				imgLogo.setFitHeight(300);
				
				TranslateTransition translateTxtUsuario = new TranslateTransition(Duration.seconds(0.5), txtUsuario);
				translateTxtUsuario.setToY(-55);
				translateTxtUsuario.play();
				
				FadeTransition fadeTxtUsuario = new FadeTransition(Duration.seconds(0.5), txtSenha);
				fadeTxtUsuario.setFromValue(0);
				fadeTxtUsuario.setToValue(1);
				fadeTxtUsuario.play();
				
				txtUsuario.setEditable(false);
				
				txtSenha.requestFocus();
			} else {
				JFXSnackbar mensagem = new JFXSnackbar(stackPane);
				mensagem.show("Usuário incorreto!", 2000);
			}
		} else {
			Funcionario funcionario = FuncionarioDAO.entrar(txtUsuario.getText().trim(), txtSenha.getText().trim());
			
			if(funcionario != null) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fxml/Painel.fxml"));
					
					Parent root = loader.load();
				
					PainelController painelController = loader.getController();
					painelController.setFuncionario(funcionario);
					
					Scene telaEntrar = (Scene) stackPane.getScene();
					
					Scene cena = new Scene(root, telaEntrar.getWidth(), telaEntrar.getHeight());
					
					Stage stage = (Stage) stackPane.getScene().getWindow();
					stage.setScene(cena);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				JFXSnackbar mensagem = new JFXSnackbar(stackPane);
				mensagem.show("Senha incorreta!", 2000);
			}
		}
	}
}