package controller;

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
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
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
    private JFXButton btnEntrarUsuario;
	
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

			FadeTransition fadeInEntrar1 = new FadeTransition(Duration.seconds(0.5), btnEntrarUsuario);
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
				entrar(txtUsuario.getText().trim());
			}
		});
		
		btnEntrarUsuario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				entrar(txtUsuario.getText().trim());
			}
		});
		
		btnSair.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Platform.exit();
			}
		});
	}

	protected void entrar(String usuario) {
		if(!entrouUsuario) {
			String entrou = FuncionarioDAO.entrar(usuario);
			
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
				
				fadeTxtUsuario.setOnFinished((e) -> {
					txtUsuario.setEditable(false);
					
					txtSenha.requestFocus();
				});
			} else {
				JFXSnackbar mensagem = new JFXSnackbar(stackPane);
				mensagem.show("Usuário incorreto!", 2000);
			}
		}
	}
}