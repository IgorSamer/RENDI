package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.bean.Funcionario;

public class PainelController implements Initializable {
	@FXML
    private AnchorPane anchorPane;
    
	@FXML
	private StackPane stackPane, stackPane1;
	
	@FXML
	private HBox boxCarrosel, boxCarrosel1;
	
	@FXML
    private ImageView imgFotoFuncionario, imgLogo;
    
    @FXML
    private Label lblTipoNome, lblData, lblTitulo;
    
    @FXML
    private BorderPane bdrPainel;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private StackPane conteudo;
    
    private Image logo, logo_estatica;
    
    private JFXListView<Label> listaControle = new JFXListView<Label>();
    private JFXListView<Label> listaServicos = new JFXListView<Label>();
    private JFXListView<Label> listaFuncionarios = new JFXListView<Label>();
    private JFXListView<Label> listaLicencas = new JFXListView<Label>();
    
    private JFXPopup popupControle = new JFXPopup();
    private JFXPopup popupServicos = new JFXPopup();
    private JFXPopup popupFuncionarios = new JFXPopup();
    private JFXPopup popupLicencas = new JFXPopup();
    
    public static Integer idFuncionario;
    
    public static void setIdFuncionario(Integer Id) {
    	idFuncionario = Id;
    }
    
    public static Integer getIdFuncionario() {
    	return idFuncionario;
    }
    
    Integer fundoNumero = 1;
    
    double carroselX = 0;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stackPane.setStyle("-fx-background-image: url('/view/img/fundos/1.jpg')");
		
		Timeline fundo = new Timeline(
			new KeyFrame(Duration.millis(1000),
					event -> {
						carroselX -= 30;
						
						TranslateTransition carrosel = new TranslateTransition(Duration.millis(1000), boxCarrosel);
						carrosel.setToX(carroselX);
						carrosel.play();
						
						TranslateTransition carrosel1 = new TranslateTransition(Duration.millis(1000), boxCarrosel1);
						carrosel1.setToX(carroselX);
						carrosel1.play();
						
						if(boxCarrosel.getTranslateX() < -700) {
							boxCarrosel.setTranslateX(0);
							
							carroselX = 0;
						}
						
						//fundoNumero++;
						
						//if(fundoNumero > 6) {
						//	fundoNumero = 1;
						//}
			        }
			)
		);

		fundo.setCycleCount(Animation.INDEFINITE);
		fundo.play();
		
		ArrayList<Node> botoes = new ArrayList<>();
		botoes.addAll(boxCarrosel.getChildren());
		botoes.addAll(boxCarrosel1.getChildren());
		
		for(Node btn : botoes) {
			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					mudaConteudo(btn.getId());
					
					FadeTransition fadeInPainel = new FadeTransition(Duration.millis(500), bdrPainel);
					fadeInPainel.setFromValue(0);
					fadeInPainel.setToValue(1);
					fadeInPainel.play();
					
					FadeTransition fadeInDrawer = new FadeTransition(Duration.millis(500), drawer);
					fadeInDrawer.setFromValue(0);
					fadeInDrawer.setToValue(1);
					fadeInDrawer.play();
					
					fadeInDrawer.setOnFinished(e -> {
						drawer.open();
					});
					
					FadeTransition fadeOutStack = new FadeTransition(Duration.millis(500), stackPane);
					fadeOutStack.setFromValue(1);
					fadeOutStack.setToValue(0);
					fadeOutStack.play();
					
					FadeTransition fadeOutStack1 = new FadeTransition(Duration.millis(500), stackPane1);
					fadeOutStack1.setFromValue(1);
					fadeOutStack1.setToValue(0);
					fadeOutStack1.play();
					
					fadeOutStack.setOnFinished(e -> {
						anchorPane.getChildren().removeAll(stackPane, stackPane1);
					});
				}
			});
			
			btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					if(!fundoNumero.equals(Integer.valueOf(btn.getAccessibleText()))) {
						FadeTransition fadeOut = new FadeTransition(Duration.millis(500), stackPane);
						fadeOut.setFromValue(1);
						fadeOut.setToValue(0);
						fadeOut.play();
						
						fadeOut.setOnFinished(e -> {
							lblTitulo.setText(((Button) btn).getText());
							
							stackPane.setStyle("-fx-background-image: url('/view/img/fundos/"+ btn.getAccessibleText() +".jpg')");
							
							FadeTransition fadeIn = new FadeTransition(Duration.millis(500), stackPane);
							fadeIn.setFromValue(0);
							fadeIn.setToValue(1);
							fadeIn.play();
						});
						
						fundoNumero = Integer.valueOf(btn.getAccessibleText());
					}
					
					for(Node btnDnv : botoes) {
						if(!fundoNumero.equals(Integer.valueOf(btnDnv.getAccessibleText()))) {
							btnDnv.getStyleClass().add("desativado");
						}
					}
					
					fundo.stop();
				}
			});
			
			btn.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					for(Node btnDnv : botoes) {
						btnDnv.getStyleClass().remove("desativado");
					}
					
					fundo.play();
				}
			});
		}
		
		logo = new Image(getClass().getResource("/view/img/Logo_cinza.gif").toExternalForm());
		logo_estatica = new Image(getClass().getResource("/view/img/Logo_cinza_estatico.gif").toExternalForm());
		
		stackPane.toBack();
		conteudo.toBack();
		
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
		
		JFXListView<String> lista = new JFXListView<String>();
		lista.getItems().add("Editar Perfil");
		lista.setPrefWidth(100);
		lista.setPrefHeight(35);
		
		JFXPopup popup = new JFXPopup();
		popup.setContent(lista);
		popup.setPopupContainer(anchorPane);
		popup.setSource(lblTipoNome);
		lblTipoNome.setOnMouseClicked((e)-> popup.show(PopupVPosition.TOP, PopupHPosition.RIGHT));
	}
	
	public void setFuncionario(Funcionario func) {
		setIdFuncionario(func.getId());
		
		String tipoFunc = func.getFuncao().getNome();
		String tipoMenu = "";
		
		Image fotoFuncionarioImg = new Image(getClass().getResource(Funcionario.getCaminhoFoto(func.getFoto())).toExternalForm());
		
		imgFotoFuncionario.setImage(fotoFuncionarioImg);
		lblTipoNome.setText(func.getPessoa().getNome() + "(" + tipoFunc + ")");
		
		try {
			Label itemControle, itemControle2, itemControle3, itemControle10;
			Label itemServico, itemServico2;
			
			switch(tipoFunc) {
				case "Gerente":
					tipoMenu = "PainelMenuGerente";
					
					// Op��es no menu de Controle
					itemControle = new Label("Ordem de Compra");
					itemControle.setId("GerenciarOrdemCompra");
					itemControle.setAccessibleText("Gerenciar Ordem de Compra");
					
					itemControle2 = new Label("Clientes");
					itemControle2.setId("GerenciarClientes");
					itemControle2.setAccessibleText("Clientes");
					
					itemControle3 = new Label("Estoque");
					itemControle3.setId("GerenciarEstoque");
					itemControle3.setAccessibleText("Estoque");
					
					Label itemControle4 = new Label("Fornecedores");
					itemControle4.setId("GerenciarFornecedores");
					itemControle4.setAccessibleText("Fornecedores");
					
					Label itemControle5 = new Label("�rg�os Emissores");
					itemControle5.setId("GerenciarOrgaosEmissores");
					itemControle5.setAccessibleText("�rg�os Emissores");
					
					Label itemControle6 = new Label("Servicos");
					itemControle6.setId("GerenciarServicosControle");
					itemControle6.setAccessibleText("Servicos");
					
					Label itemControle7 = new Label("Tanques");
					itemControle7.setId("GerenciarTanques");
					itemControle7.setAccessibleText("Tanques");
					
					Label itemControle8 = new Label("Bombas");
					itemControle8.setId("GerenciarBombas");
					itemControle8.setAccessibleText("Bombas");
					
					Label itemControle9 = new Label("Bicos");
					itemControle9.setId("GerenciarBicos");
					itemControle9.setAccessibleText("Bicos");
					
					itemControle10 = new Label("Caixa");
					itemControle10.setId("GerenciarCaixa");
					itemControle10.setAccessibleText("Caixa");
					
					listaControle.getItems().addAll(itemControle, itemControle2, itemControle3, itemControle4, itemControle5, itemControle6, itemControle7, itemControle8, itemControle9, itemControle10);
					listaControle.setPrefHeight(350);
					
					//Op��es no menu de Servi�os
					itemServico = new Label("Gerais");
					itemServico.setId("GerenciarServicos");
					itemServico.setAccessibleText("Gerais");
					
					itemServico2 = new Label("Abastecimento");
					itemServico2.setId("GerenciarAbastecimento");
					itemServico2.setAccessibleText("Abastecimento");
					
					listaServicos.getItems().addAll(itemServico, itemServico2);
					listaServicos.setPrefHeight(130);
					
					//Op��es no menu de Funcion�rios
					Label itemFuncionario = new Label("Gerenciar Funcion�rios");
					itemFuncionario.setId("GerenciarFuncionarios");
					itemFuncionario.setAccessibleText("Gerenciar Funcion�rios");
					
					Label itemFuncionario2 = new Label("Cadastrar Funcion�rios");
					itemFuncionario2.setId("GerenciarFuncionarios1");
					itemFuncionario2.setAccessibleText("Cadastrar Funcion�rios");
					
					listaFuncionarios.getItems().addAll(itemFuncionario, itemFuncionario2);
					listaFuncionarios.setPrefHeight(100);
					
					//Op��es no menu de Licen�as
					Label itemLicenca = new Label("Gerenciar Licen�as");
					itemLicenca.setId("GerenciarLicencas");
					itemLicenca.setAccessibleText("Gerenciar Licen�as");
					
					Label itemLicenca2 = new Label("Cadastrar Licen�as");
					itemLicenca2.setId("GerenciarLicencas1");
					itemLicenca2.setAccessibleText("Cadastrar Licen�as");
					
					listaLicencas.getItems().addAll(itemLicenca, itemLicenca2);
					listaLicencas.setPrefHeight(100);
				break;
				
				case "Caixa":
					tipoMenu = "PainelMenuCaixa";
					
					// Op��es no menu de Controle
					itemControle = new Label("Ordem de Compra");
					itemControle.setId("GerenciarOrdemCompra");
					itemControle.setAccessibleText("Gerenciar Ordem de Compra");
					
					itemControle2 = new Label("Clientes");
					itemControle2.setId("GerenciarClientes");
					itemControle2.setAccessibleText("Clientes");
					
					itemControle3 = new Label("Estoque");
					itemControle3.setId("GerenciarEstoque");
					itemControle3.setAccessibleText("Estoque");
					
					itemControle10 = new Label("Caixa");
					itemControle10.setId("GerenciarCaixa");
					itemControle10.setAccessibleText("Caixa");
					
					listaControle.getItems().addAll(itemControle, itemControle2, itemControle3, itemControle10);
					listaControle.setPrefHeight(250);
					
					//Op��es no menu de Servi�os
					itemServico = new Label("Gerais");
					itemServico.setId("GerenciarServicos");
					itemServico.setAccessibleText("Gerais");
					
					itemServico2 = new Label("Abastecimento");
					itemServico2.setId("GerenciarAbastecimento");
					itemServico2.setAccessibleText("Abastecimento");
					
					listaServicos.getItems().addAll(itemServico, itemServico2);
					listaServicos.setPrefHeight(130);
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
									stage.setTitle("RENDI - Solu��es Rent�veis.");
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
							switch(botaoMenu.getAccessibleText()) {
								case "controle":
									listaControle.setPrefWidth(((Region) botaoMenu).getWidth());
									popupControle.setContent(listaControle);
									popupControle.setPopupContainer(anchorPane);
									popupControle.setSource(botaoMenu);
									popupControle.show(PopupVPosition.TOP, PopupHPosition.RIGHT, 0, 58);
									
									bindarPopups(listaControle.getItems());
								break;
								
								case "servicos":
									listaServicos.setPrefWidth(((Region) botaoMenu).getWidth());
									popupServicos.setContent(listaServicos);
									popupServicos.setPopupContainer(anchorPane);
									popupServicos.setSource(botaoMenu);
									popupServicos.show(PopupVPosition.TOP, PopupHPosition.RIGHT, 0, 58);
									
									bindarPopups(listaServicos.getItems());
								break;
								
								case "funcionarios":
									listaFuncionarios.setPrefWidth(((Region) botaoMenu).getWidth());
									popupFuncionarios.setContent(listaFuncionarios);
									popupFuncionarios.setPopupContainer(anchorPane);
									popupFuncionarios.setSource(botaoMenu);
									popupFuncionarios.show(PopupVPosition.TOP, PopupHPosition.RIGHT, 0, 58);
									
									bindarPopups(listaFuncionarios.getItems());
								break;
								
								case "licencas":
									listaLicencas.setPrefWidth(((Region) botaoMenu).getWidth());
									popupLicencas.setContent(listaLicencas);
									popupLicencas.setPopupContainer(anchorPane);
									popupLicencas.setSource(botaoMenu);
									popupLicencas.show(PopupVPosition.TOP, PopupHPosition.RIGHT, 0, 58);
									
									bindarPopups(listaLicencas.getItems());
								break;
							}
						}
					});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void bindarPopups(ObservableList<Label> items) {
		for(Node botaoPopup : items) {
			botaoPopup.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					mudaConteudo(botaoPopup.getId());
					
					Stage stage = (Stage) anchorPane.getScene().getWindow();
					stage.setTitle("RENDI - " + botaoPopup.getAccessibleText());
				}
			});
		}
	}
	
	String paginaAux = "";
	
	protected void mudaConteudo(String pagina) {
		imgLogo.setImage(logo);
		
		FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), conteudo);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.play();
		
		fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {				
					paginaAux = pagina;
					
					if(pagina.charAt(pagina.length() - 1) == '1') {
						paginaAux = pagina.substring(0, pagina.length() - 1);
					}
					
					StackPane paneConteudo = FXMLLoader.load(getClass().getResource("../view/fxml/"+ paginaAux +".fxml"));
					
					conteudo.getChildren().setAll(paneConteudo);
					
					FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), conteudo);
					fadeIn.setFromValue(0);
					fadeIn.setToValue(1);
					fadeIn.play();
					
					fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							imgLogo.setImage(logo_estatica);
							
							if(pagina.equals("GerenciarFuncionarios1") && paginaAux.equals("GerenciarFuncionarios")) {
								GerenciarFuncionariosController.mostra = true;
							}
						}
					});
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}