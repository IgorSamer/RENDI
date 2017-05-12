package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.dao.FuncionarioDAO;

public class EntrarController implements Initializable {
	@FXML
    private ImageView imgLogo;
	
	@FXML
	private JFXTextField txtUsuario;
	
	@FXML
    private JFXButton btnEntrarUsuario;
	
	@FXML
	private JFXButton btnSair;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FadeTransition fadeInLogo = new FadeTransition(Duration.seconds(2), imgLogo);
		fadeInLogo.setFromValue(0);
		fadeInLogo.setToValue(1);
		fadeInLogo.play();
		
		fadeInLogo.setOnFinished((e) -> {
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
		
		btnEntrarUsuario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				if(FuncionarioDAO.entrar(txtUsuario.getText().trim())) {
					System.out.println("ENTROUOOUOUOU");
				} else {
					System.out.println("NINGUEEEEM");
				}
			}
		});
		
		btnSair.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				System.out.println("ENTROUOOUOUOU");
				Platform.exit();
			}
		});
	}
}