package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXSpinner;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bean.Funcionario;

public class PainelController implements Initializable {
	@FXML
    private AnchorPane anchorPane;
    
    @FXML
    private StackPane stackIcone;
    
    @FXML
    private Label lblTipoNome, lblData;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private StackPane conteudo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conteudo.toBack();
		
		drawer.open();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		lblData.setText(dateFormat.format(new Date()));
		
		final Timeline timeline = new Timeline(
			new KeyFrame(Duration.millis(1000),
					event -> {
						final Date date = new Date();
						
						lblData.setText(dateFormat.format(date));
			        }
			)
		);

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		JFXListView<String> list = new JFXListView<String>();
		list.getItems().add("Editar Perfil");
		list.setPrefWidth(100);
		list.setPrefHeight(35);
		
		JFXPopup popup = new JFXPopup();
		popup.setContent(list);
		popup.setPopupContainer(anchorPane);
		popup.setSource(lblTipoNome);
		lblTipoNome.setOnMouseClicked((e)-> popup.show(PopupVPosition.TOP, PopupHPosition.RIGHT));
	}
	
	public void setFuncionario(Funcionario func) {
		String tipoFunc = func.getFuncao().getNome();
		String tipoMenu = "";
		
		lblTipoNome.setText(func.getNome() + "(" + tipoFunc + ")");
		
		try {
			switch(tipoFunc) {
				case "Gerente":
					tipoMenu = "PainelMenuGerente";
				break;
				
				case "Caixa":
					tipoMenu = "PainelMenuCaixa";
				break;
			}
			
			HBox menuBox = FXMLLoader.load(getClass().getResource("../view/fxml/"+ tipoMenu +".fxml"));
			
			drawer.setSidePane(menuBox);
			
			for(Node botaoMenu : menuBox.getChildren()) {
				if(botaoMenu.getId() != null) {
					botaoMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if(botaoMenu.getId().equals("sair")) {
								try {
									Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/Entrar.fxml"));
									
									Scene telaConteudo = (Scene) anchorPane.getScene();
									
									Scene cena = new Scene(root, telaConteudo.getWidth(), telaConteudo.getHeight());
									
									Stage stage = (Stage) anchorPane.getScene().getWindow();
									stage.setScene(cena);
									stage.setTitle("RENDI - Soluções Rentáveis.");
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								mudaConteudo(botaoMenu.getId());
								
								for(Node botaoMenuReset : menuBox.getChildren()) {
									botaoMenuReset.getStyleClass().remove("btn_ativo");
								}
								
								botaoMenu.getStyleClass().add("btn_ativo");
								
								Stage stage = (Stage) anchorPane.getScene().getWindow();
								stage.setTitle("RENDI - " + botaoMenu.getAccessibleText());
							}
						}
					});
				} else {
					botaoMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if(botaoMenu.getId().equals("sair")) {
								
							} else {
								
							}
						}
					});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void mudaConteudo(String pagina) {
		stackIcone.getChildren().setAll(new JFXSpinner());
		
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), conteudo);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.play();
		
		fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					StackPane paneConteudo = FXMLLoader.load(getClass().getResource("../view/fxml/"+ pagina +".fxml"));
					
					conteudo.getChildren().setAll(paneConteudo);
					
					FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), conteudo);
					fadeIn.setFromValue(0);
					fadeIn.setToValue(1);
					fadeIn.play();
					
					fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							ImageView icone = new ImageView("/view/img/logo_branco.png");
							icone.setFitWidth(50);
							icone.setFitHeight(50);
							icone.setPreserveRatio(true);
							icone.setPickOnBounds(true);
							
							stackIcone.getChildren().setAll(icone);
						}
					});
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}