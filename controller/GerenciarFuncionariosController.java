package controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import model.bean.Dados;
import model.bean.Endereco;
import model.bean.Escolaridade;
import model.bean.EstadoCivil;
import model.bean.Funcao;
import model.bean.Funcionario;
import model.bean.Genero;
import model.bean.JSONException;
import model.bean.JSONObject;
import model.bean.JSONTokener;
import model.bean.Pessoa;
import model.bean.PessoaFisica;
import model.bean.Setor;
import model.bean.Telefone;
import model.bean.TextFieldFormatter;
import model.bean.Usuario;
import model.dao.FuncionarioDAO;
import model.dao.PessoaFisicaDAO;

public class GerenciarFuncionariosController extends Dados implements Initializable {
	@FXML StackPane conteudoConteudo;
	
	@FXML
	private VBox funGerenciar, funCadastrar;
	
	@FXML
    private JFXTreeTableView<Funcionario> tblFuncionarios;
	
	@FXML
    private JFXButton btnPesquisarFuncionario;

    @FXML
    private JFXButton btnAlterarFuncionario;

    @FXML
    private JFXButton btnVisualizarFuncionario;

    @FXML
    private JFXButton btnCadastrarFuncionario;
    
    @FXML
    private JFXButton btnExcluirFuncionario;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtSobrenome;

    @FXML
    private JFXTextField txtRg;

    @FXML
    private JFXTextField txtCpf;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCep;

    @FXML
    private JFXTextField txtUf;

    @FXML
    private JFXTextField txtCidade;

    @FXML
    private JFXTextField txtBairro;

    @FXML
    private JFXTextField txtRua;

    @FXML
    private JFXTextField txtNumero;

    @FXML
    private JFXDatePicker datAdmissao;

    @FXML
    private JFXDatePicker datDemissao;

    @FXML
    private JFXDatePicker datNascimento;

    @FXML
    private JFXTextField txtSalario;

    @FXML
    private JFXComboBox<EstadoCivil> cmbEstadoCivil;

    @FXML
    private JFXComboBox<Genero> cmbGenero;

    @FXML
    private JFXComboBox<Escolaridade> cmbEscolaridade;

    @FXML
    private JFXComboBox<Funcao> cmbFuncao;

    @FXML
    private JFXComboBox<Setor> cmbSetor;
    
    @FXML
    private JFXTextField txtTelefone, txtPesquisar;

    @FXML
    private JFXButton btnAdicionarTelefone;

    @FXML
    private JFXButton btnRemoverTelefone;

    @FXML
    private JFXComboBox<String> cmbTelefones;
    
    @FXML
    private JFXButton btnFotoAdicionar;
    
    @FXML
    private ImageView imgFoto;
    
    @FXML
    private JFXButton btnCancelarFuncionario, btnCadastrarFuncionarioFinal, btnFotoTirar, btnFotoCancelar, btnAtualizarFuncionario;
    
    @FXML
    private SplitPane splConteudo, splCad;
    
    @FXML
    private Pane paneConteudo;
    
    @FXML
    private ImageView imgFuncionario;
    
    @FXML
    private Label lblNome, lblDataAdmissao, lblDataDemissao;
    
    @FXML
    private JFXTreeTableView<Telefone> tblTelefones;
    
    @FXML
    private JFXCheckBox chkCadastrar;
    
    @FXML
    private VBox vBoxCad;
    
    @FXML
    private Text txtTitulo;
    
    private File arquivo;
    
    private BufferedImage imagemBuffer;
    private Webcam webcam;
    
    private String imagemNomeNovoFinal = "usuario_padrao.png";
    
    Timeline webcamTimeline;
    
    public static boolean mostra = false;
	
    Timeline timeline;
    
    private JFXTextField txtUsuario;
    private JFXPasswordField txtSenha;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnVisualizarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
		        
		        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
		        
	            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
	            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
	            divTimeline.play();
			}
		});
		
		mostra = false;
		
		timeline = new Timeline(
			new KeyFrame(Duration.millis(100),
					event -> {
						if(mostra) {
							funGerenciar.setVisible(false);
							funCadastrar.setVisible(true);
							
							mostra = false;
							
							timeline.stop();
						}
			        }
			)
		);
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		btnPesquisarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(txtPesquisar.isVisible()) {
					txtPesquisar.setVisible(false);
				} else {
					txtPesquisar.setVisible(true);
				}
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colId = new JFXTreeTableColumn<>("Id");
		colId.setPrefWidth(150);
		colId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colNome = new JFXTreeTableColumn<>("Nome");
		colNome.setPrefWidth(150);
		colNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return Bindings.concat(param.getValue().getValue().getPessoa().nomeProperty(), " ", param.getValue().getValue().getPessoa().sobrenomeProperty());
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colRg = new JFXTreeTableColumn<>("RG");
		colRg.setPrefWidth(150);
		colRg.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().rgProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colCpf = new JFXTreeTableColumn<>("CPF");
		colCpf.setPrefWidth(150);
		colCpf.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getPessoa_fisica().cpfProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colCidade = new JFXTreeTableColumn<>("Cidade");
		colCidade.setPrefWidth(150);
		colCidade.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getPessoa().getEndereco().cidadeProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colSalario = new JFXTreeTableColumn<>("Salário");
		colSalario.setPrefWidth(150);
		colSalario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().salarioProperty().asString();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colFuncao = new JFXTreeTableColumn<>("Função");
		colFuncao.setPrefWidth(150);
		colFuncao.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getFuncao().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<Funcionario, String> colSetor = new JFXTreeTableColumn<>("Setor");
		colSetor.setPrefWidth(150);
		colSetor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Funcionario, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Funcionario, String> param) {
				return param.getValue().getValue().getSetor().nomeProperty();
			}
		});
		
		ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
		funcionarios.addAll(FuncionarioDAO.listar());
		TreeItem<Funcionario> root = new RecursiveTreeItem<Funcionario>(funcionarios, RecursiveTreeObject::getChildren);
		
		tblFuncionarios.getColumns().setAll(colId, colNome, colRg, colCpf, colCidade, colSalario, colFuncao, colSetor);
		tblFuncionarios.setRoot(root);
		tblFuncionarios.setShowRoot(false);
		
		tblFuncionarios.setRowFactory(tv -> {
			JFXTreeTableRow<Funcionario> linha = new JFXTreeTableRow<>();
			
			linha.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getClickCount() == 2 && !linha.isEmpty()) {
						Funcionario funcInfo = tblFuncionarios.getSelectionModel().getSelectedItem().getValue();
						
						JFXTreeTableColumn<Telefone, String> colIdTel = new JFXTreeTableColumn<>("Id");
						colIdTel.setPrefWidth(150);
						colIdTel.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Telefone, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<Telefone, String> param) {
								return param.getValue().getValue().idProperty().asString();
							}
						});
						
						JFXTreeTableColumn<Telefone, String> colNumeroTel = new JFXTreeTableColumn<>("Telefone");
						colNumeroTel.setPrefWidth(150);
						colNumeroTel.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Telefone, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(CellDataFeatures<Telefone, String> param) {
								return param.getValue().getValue().numeroProperty();
							}
						});
						
						ObservableList<Telefone> telefones = FXCollections.observableArrayList();
						telefones.addAll(FuncionarioDAO.getTelefones(tblFuncionarios.getSelectionModel().getSelectedItem().getValue().getId()));
						TreeItem<Telefone> rootTel = new RecursiveTreeItem<Telefone>(telefones, RecursiveTreeObject::getChildren);
						
						tblTelefones.getColumns().setAll(colIdTel, colNumeroTel);
						tblTelefones.setRoot(rootTel);
						tblTelefones.setShowRoot(false);
						
						Image fotoProdutoImg = new Image(getClass().getResource(Funcionario.getCaminhoFoto(funcInfo.getFoto())).toExternalForm());
						
						imgFuncionario.setImage(fotoProdutoImg);
						imgFuncionario.setFitWidth(150);
						imgFuncionario.setFitHeight(150);
						
						lblNome.setText(funcInfo.getPessoa().getNome());
						lblDataAdmissao.setText("Admissão: " + funcInfo.getData_admissao());
						lblDataDemissao.setText("| Demissão: " + funcInfo.getData_demissao());
						
						DoubleProperty divPos = splConteudo.getDividers().get(0).positionProperty();
				        
				        double novaPos = divPos.get() > 0.8 ? 0.7 : 1.0;
				        
			            KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), novaPos);
			            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
			            divTimeline.play();
					}
				}
			});
			
			return linha;
		});
		
		tblFuncionarios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Funcionario>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<Funcionario>> arg0, TreeItem<Funcionario> arg1,
					TreeItem<Funcionario> arg2) {
				if(splConteudo.getDividers().get(0).positionProperty().get() > 0.7) {
					KeyValue keyValue = new KeyValue(splConteudo.getDividers().get(0).positionProperty(), 1.0);
		            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
		            divTimeline.play();
				}
			}
		});
		
		btnCadastrarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				txtTitulo.setText("Cadastrar Funcionários");
				
				btnCadastrarFuncionarioFinal.setVisible(true);
				btnAtualizarFuncionario.setVisible(false);
				
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
		
		btnAlterarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {		
				txtTitulo.setText("Atualizar Funcionário");
				btnAtualizarFuncionario.setVisible(true);
				btnCadastrarFuncionarioFinal.setVisible(false);
				
				Funcionario func = tblFuncionarios.getSelectionModel().getSelectedItem().getValue();
				
				txtNome.setText(func.getPessoa().getNome());
				txtSobrenome.setText(func.getPessoa().getSobrenome());
				txtRg.setText(func.getPessoa().getPessoa_fisica().getRg());
				txtCpf.setText(func.getPessoa().getPessoa_fisica().getCpf());
				txtEmail.setText(func.getPessoa().getEmail());
				txtCep.setText(func.getPessoa().getEndereco().getCep());
				txtUf.setText(func.getPessoa().getEndereco().getUf());
				txtCidade.setText(func.getPessoa().getEndereco().getCidade());
				txtBairro.setText(func.getPessoa().getEndereco().getBairro());
				txtRua.setText(func.getPessoa().getEndereco().getRua());
				txtNumero.setText(String.valueOf(func.getPessoa().getEndereco().getNumero()));
				txtSalario.setText(String.valueOf(func.getSalario()));
				cmbEstadoCivil.getSelectionModel().select(func.getPessoa().getEstado_civil());
				cmbGenero.getSelectionModel().select(func.getPessoa().getGenero());
				cmbEscolaridade.getSelectionModel().select(func.getPessoa().getPessoa_fisica().getEscolaridade());
				cmbFuncao.getSelectionModel().select(func.getFuncao());
				cmbSetor.getSelectionModel().select(func.getSetor());
				
				funGerenciar.setVisible(false);
				funCadastrar.setVisible(true);
			}
		});
		
		btnAtualizarFuncionario.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				Funcionario funcionario = new Funcionario(tblFuncionarios.getSelectionModel().getSelectedItem().getValue().getId(), String.valueOf(datAdmissao.getValue()), String.valueOf(datDemissao.getValue()), Double.valueOf(txtSalario.getText().trim()), imagemNomeNovoFinal, 1, new Funcao(cmbFuncao.getSelectionModel().getSelectedItem().getId(), null), new Setor(cmbSetor.getSelectionModel().getSelectedItem().getId(), null), new Pessoa(txtNome.getText().trim(), txtSobrenome.getText().trim(), String.valueOf(datNascimento.getValue()), txtEmail.getText().trim(), new Genero(cmbGenero.getSelectionModel().getSelectedItem().getId(), null), new EstadoCivil(cmbEstadoCivil.getSelectionModel().getSelectedItem().getId(), null), null, new PessoaFisica(txtRg.getText().trim(), txtCpf.getText().trim(), new Escolaridade(cmbEscolaridade.getSelectionModel().getSelectedItem().getId(), null)), new Endereco(txtUf.getText().trim(), txtCidade.getText().trim(), txtCep.getText().trim(), txtBairro.getText().trim(), txtRua.getText().trim(), Integer.valueOf(txtNumero.getText().trim()))));
				
				if(FuncionarioDAO.atualizar(funcionario)) {
					mensagem.show("Funcionário atualizado com sucesso!", 2000);
				} else {
					mensagem.show("Erro ao atualizar funcionário!", 2000);
				}
			}
		});
		
		Label fotoOpcao1 = new Label("Escolher Foto");
		Label fotoOpcao2 = new Label("Tirar Foto");
		
		JFXListView<Label> fotoOpcoes = new JFXListView<Label>();
		fotoOpcoes.getItems().addAll(fotoOpcao1, fotoOpcao2);
		fotoOpcoes.setPrefWidth(105);
		fotoOpcoes.setPrefHeight(70);
		
		JFXPopup popup = new JFXPopup();
		popup.setContent(fotoOpcoes);
		popup.setPopupContainer(paneConteudo);
		popup.setSource(btnFotoAdicionar);
		
		btnFotoAdicionar.setOnMouseClicked((e)-> popup.show(PopupVPosition.TOP, PopupHPosition.RIGHT));
		
		fotoOpcao1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popup.close();
				
				FileChooser.ExtensionFilter arquivoFiltro = new FileChooser.ExtensionFilter("Apenas Imagens", "*.jpg", "*.jpeg", "*.png");
				
				FileChooser fcEscolher = new FileChooser();
				fcEscolher.setTitle("Escolher arquivo");
				fcEscolher.getExtensionFilters().add(arquivoFiltro);
				
				arquivo = fcEscolher.showOpenDialog(null);
				
				if(arquivo != null) {
					Image imagemAnexo = new Image(arquivo.toURI().toString());
					
					imgFoto.setImage(imagemAnexo);
				}
			}
		});
		
		fotoOpcao2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				popup.close();
				
				btnFotoTirar.setVisible(true);
				btnFotoCancelar.setVisible(true);
				
				Dimension cs = WebcamResolution.VGA.getSize();
				webcam = Webcam.getDefault();
				
				webcam.setViewSize(cs);
				
				webcam.open();
				
				webcamTimeline = new Timeline(new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	imagemBuffer = webcam.getImage();
				    	
				    	Image imagemCapturada = SwingFXUtils.toFXImage(imagemBuffer, null);
				    	
				    	imgFoto.setImage(imagemCapturada);
				    }
				}));
				webcamTimeline.setCycleCount(Timeline.INDEFINITE);
				webcamTimeline.play();
			}
		});
		
		btnFotoTirar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					byte[] imagemNomeByte = "eminem".getBytes("UTF-8");
	            	
	            	MessageDigest md = MessageDigest.getInstance("MD5");
	            	byte[] retorno = md.digest(imagemNomeByte);
	            	
	            	String imagemNomeNovo = DatatypeConverter.printHexBinary(retorno) + ".png";
	            	
					ImageIO.write(webcam.getImage(), "png", new File("C:\\Users\\Igor PC\\workspace\\RENDI\\src\\view\\img\\funcionarios\\" + imagemNomeNovo));

					imagemNomeNovoFinal = imagemNomeNovo;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				btnFotoTirar.setVisible(false);
				btnFotoCancelar.setVisible(false);
				
				webcam.close();
				webcamTimeline.stop();
			}
		});
		
		txtPesquisar.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				tblFuncionarios.setPredicate(new Predicate<TreeItem<Funcionario>>(){
					@Override
					public boolean test(TreeItem<Funcionario> funcionario) {
						if(funcionario.getValue().getPessoa().getNome().contains(txtPesquisar.getText()) || funcionario.getValue().getPessoa().getPessoa_fisica().getCpf().contains(txtPesquisar.getText()) || funcionario.getValue().getPessoa().getPessoa_fisica().getRg().contains(txtPesquisar.getText()) || funcionario.getValue().getPessoa().getEndereco().getCidade().contains(txtPesquisar.getText()) || funcionario.getValue().getFuncao().getNome().contains(txtPesquisar.getText()) || funcionario.getValue().getSetor().getNome().contains(txtPesquisar.getText())){
							return true;
						} else {
							return false;
						}
					}
				});
			}
		});
		
		btnFotoCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				btnFotoTirar.setVisible(false);
				btnFotoCancelar.setVisible(false);
				
				webcam.close();
				webcamTimeline.stop();
			}
		});
		
		txtCep.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@SuppressWarnings("deprecation")
			@Override
			public void handle(KeyEvent event) {
				String cep = txtCep.getText().trim();
				
				if(cep.length() == 8) {
					try {
						JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL("https://viacep.com.br/ws/"+ cep +"/json").openStream())).nextValue();
					    
					    txtUf.setText(jo.getString("uf"));
					    txtCidade.setText(jo.getString("localidade"));
					    txtBairro.setText(jo.getString("bairro"));
					    txtRua.setText(jo.getString("logradouro"));
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		txtTelefone.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				TextFieldFormatter tff = new TextFieldFormatter();
				tff.setMask("(##) #####-####");
				tff.setCaracteresValidos("0123456789");
				tff.setTf(txtTelefone);
				tff.formatter();
			}
		});
		
		chkCadastrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(chkCadastrar.isSelected()) {
					txtUsuario = new JFXTextField();
					txtUsuario.setLabelFloat(true);
					txtUsuario.setPromptText("Usuário");
					
					txtSenha = new JFXPasswordField();
					txtSenha.setLabelFloat(true);
					txtSenha.setPromptText("Senha");
					txtSenha.setTranslateY(15);
					
					vBoxCad.getChildren().addAll(txtUsuario, txtSenha);
					
					KeyValue keyValue = new KeyValue(splCad.getDividers().get(0).positionProperty(), 0.7);
		            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
		            divTimeline.play();
				} else {
					KeyValue keyValue = new KeyValue(splCad.getDividers().get(0).positionProperty(), 1.0);
		            Timeline divTimeline = new Timeline(new KeyFrame(Duration.millis(300), keyValue));
		            divTimeline.play();
		            
		            vBoxCad.getChildren().removeAll(txtUsuario, txtSenha);
				}
			}
		});
		
		// Populando combobox Estado Civil
		ObservableList<EstadoCivil> obsListaEstadoCivil = FXCollections.observableArrayList();
		obsListaEstadoCivil.addAll(PessoaFisicaDAO.getEstadosCivis());
		
		cmbEstadoCivil.setItems(obsListaEstadoCivil);
		
		// Populando combobox Gênero
		ObservableList<Genero> obsListaGenero = FXCollections.observableArrayList();
		obsListaGenero.addAll(PessoaFisicaDAO.getGeneros());
		
		cmbGenero.setItems(obsListaGenero);
		
		// Populando combobox Escolaridade
		ObservableList<Escolaridade> obsListaEscolaridade = FXCollections.observableArrayList();
		obsListaEscolaridade.addAll(PessoaFisicaDAO.getEscolaridades());
		
		cmbEscolaridade.setItems(obsListaEscolaridade);
		
		// Populando combobox Função
		ObservableList<Funcao> obsListaFuncao = FXCollections.observableArrayList();
		obsListaFuncao.addAll(FuncionarioDAO.getFuncoes());
		
		cmbFuncao.setItems(obsListaFuncao);
		
		// Populando combobox Setor
		ObservableList<Setor> obsListaSetor = FXCollections.observableArrayList();
		obsListaSetor.addAll(FuncionarioDAO.getSetores());
		
		cmbSetor.setItems(obsListaSetor);
		
		// Adicionando / Removendo Telefones
		ArrayList<String> lstTelefones = new ArrayList<String>();
				
		btnAdicionarTelefone.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String textoTelefone = txtTelefone.getText().trim();
				
				if(!textoTelefone.isEmpty()) {
					lstTelefones.add(textoTelefone);
					
					atualizaTelefones(lstTelefones);
					
					txtTelefone.setText("");
				}
			}
		});
		
		btnRemoverTelefone.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(lstTelefones.size() != 0) {
					lstTelefones.remove(lstTelefones.size() - 1);
					
					atualizaTelefones(lstTelefones);
				}
			}
		});
		
		btnCadastrarFuncionarioFinal.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				JFXSnackbar mensagem = new JFXSnackbar(conteudoConteudo);
				
				boolean erroValidacao = false;
				
				if(validaCampo("Nome", txtNome.getText().trim())) {
					txtNome.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				} else if(validaCampo("Email", txtEmail.getText().trim())) {
					txtEmail.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				} else if(validaCampo("RG", txtRg.getText().trim())) {
					txtRg.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				} else if(validaCampo("CPF", txtCpf.getText().trim())) {
					txtCpf.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				} else if(validaCampo("CEP", txtCep.getText().trim())) {
					txtCep.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				} else if(validaCampo("Número", txtNumero.getText().trim())) {
					txtNumero.setUnFocusColor(Paint.valueOf("#f44336"));
					
					erroValidacao = true;
				}
				
				if(erroValidacao) {
					mensagem.show("Ocorreram erros de validação!", 2000);
				} else {
					ArrayList<Telefone> telefones = new ArrayList<Telefone>();
					
					for(String telefone : cmbTelefones.getItems()) {
						Telefone tel = new Telefone(0, telefone);
						
						telefones.add(tel);
					}
					
					if(arquivo != null) {					
			            try {
			            	BufferedImage imagemSla = ImageIO.read(arquivo);
			            	
			            	String imagemNome = arquivo.getName();       
			            	String imagemExtensao = imagemNome.substring(imagemNome.indexOf(".") + 1, arquivo.getName().length());
			            	byte[] imagemNomeByte = imagemNome.getBytes("UTF-8");
			            	
			            	MessageDigest md = MessageDigest.getInstance("MD5");
			            	byte[] retorno = md.digest(imagemNomeByte);
			            	
			            	String imagemNomeNovo = DatatypeConverter.printHexBinary(retorno);
			            	
			            	imagemNomeNovoFinal = imagemNomeNovo + "." + imagemExtensao;
			            	
			            	ImageIO.write(imagemSla, imagemExtensao, new File("C:\\Users\\Igor PC\\workspace\\RENDI\\src\\view\\img\\funcionarios\\" + imagemNomeNovo + "." + imagemExtensao));
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					}
					
					Funcionario funcionario = new Funcionario(0, String.valueOf(datAdmissao.getValue()), String.valueOf(datDemissao.getValue()), Double.valueOf(txtSalario.getText().trim()), imagemNomeNovoFinal, 1, new Funcao(cmbFuncao.getSelectionModel().getSelectedItem().getId(), null), new Setor(cmbSetor.getSelectionModel().getSelectedItem().getId(), null), new Pessoa(txtNome.getText().trim(), txtSobrenome.getText().trim(), String.valueOf(datNascimento.getValue()), txtEmail.getText().trim(), new Genero(cmbGenero.getSelectionModel().getSelectedItem().getId(), null), new EstadoCivil(cmbEstadoCivil.getSelectionModel().getSelectedItem().getId(), null), telefones, new PessoaFisica(txtRg.getText().trim(), txtCpf.getText().trim(), new Escolaridade(cmbEscolaridade.getSelectionModel().getSelectedItem().getId(), null)), new Endereco(txtUf.getText().trim(), txtCidade.getText().trim(), txtCep.getText().trim(), txtBairro.getText().trim(), txtRua.getText().trim(), Integer.valueOf(txtNumero.getText().trim()))));

					if(chkCadastrar.isSelected()) {
						DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						Usuario usuario = new Usuario(txtUsuario.getText().trim(), txtSenha.getText().trim(), formatoData.format(new Date()), 1);
						
						if(FuncionarioDAO.cadastrar(funcionario, usuario)) {
							mensagem.show("Funcionário cadastrado com sucesso!", 2000);
						} else {
							mensagem.show("Erro ao cadastrar funcionário!", 2000);
						}
					} else {
						if(FuncionarioDAO.cadastrar(funcionario)) {
							mensagem.show("Funcionário cadastrado com sucesso!", 2000);
						} else {
							mensagem.show("Erro ao cadastrar funcionário!", 2000);
						}
					}
				}
			}
		});
	}
	
	protected void atualizaTelefones(ArrayList<String> lstTelefones) {
		cmbTelefones.valueProperty().set(null);
		
		if(lstTelefones.size() != 0) {
			ObservableList<String> obsListaTelefones = FXCollections.observableArrayList();
			obsListaTelefones.addAll(lstTelefones);
			
			cmbTelefones.setItems(obsListaTelefones);
			
			if(!cmbTelefones.isVisible()) {
				cmbTelefones.setVisible(true);
			}
		} else {
			if(cmbTelefones.isVisible()) {
				cmbTelefones.setVisible(false);
			}
		}
	}
}