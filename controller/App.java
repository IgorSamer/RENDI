package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.bean.Cliente;
import model.bean.Email;
import model.bean.SMS;
import model.dao.ClienteDAO;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/Entrar.fxml"));
		
		Scene scene = new Scene(root);
		
		// JFXDecorator decorator = new JFXDecorator(stage, root);
		// decorator.setCustomMaximize(true);
		// Scene scene = new Scene(decorator, 800, 850);
		
		// scene.getStylesheets().add(App.class.getResource("/view/css/Estilos.css").toExternalForm());
		stage.getIcons().add(new Image("/view/img/Logo.png"));
		stage.setScene(scene);
		stage.setTitle("RENDI - Soluções Rentáveis.");
		stage.setMaximized(true);
		stage.show();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(Cliente cliente : ClienteDAO.getAniversariantes()) {
					Email email = new Email();
					email.setPara(cliente.getPessoa().getEmail());
					email.setAssunto("Feliz Aniversário!");
					email.setMensagem("<h1>Feliz Aniversário, "+ cliente.getPessoa().getNome() +"!</h1><p>Parabéns pra você! :D</p>");
					email.enviar();
					
					SMS mensagem = new SMS();
					mensagem.setDestinatario(cliente.getPessoa().getTelefones().get(0).getNumero());
					mensagem.setMensagem("Feliz aniversario, " + cliente.getPessoa().getNome());
					mensagem.enviar();
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}