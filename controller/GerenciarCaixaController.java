package controller;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.bean.Caixa;
import model.bean.CombustivelVenda;
import model.bean.ServicoVenda;
import model.dao.CaixaDAO;
import model.dao.ServicoDAO;
import model.dao.TanqueDAO;

public class GerenciarCaixaController implements Initializable {
	@FXML
	private StackPane conteudoConteudo;
	
	@FXML
    private JFXTreeTableView<CombustivelVenda> tblAbastecimentos;
	
	@FXML
	private JFXTreeTableView<ServicoVenda> tblServicos;
	
	@FXML
	private Label lblTotalAbastecimentos, lblTotalServicos;
	
	@FXML
	private JFXButton btnFecharCaixa;
	
	@FXML
    private BarChart<?, ?> grfCaixa;
	
	private Double totalTotal = 0.0;
	
	private boolean fechouCaixa = CaixaDAO.fechouCaixa();
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<CombustivelVenda, String> colIdAbas = new JFXTreeTableColumn<>("Id");
		colIdAbas.setPrefWidth(150);
		colIdAbas.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colLitrosAbas = new JFXTreeTableColumn<>("Litros");
		colLitrosAbas.setPrefWidth(150);
		colLitrosAbas.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().litrosProperty().asString();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colValorAbas = new JFXTreeTableColumn<>("Valor");
		colValorAbas.setPrefWidth(150);
		colValorAbas.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return Bindings.concat("R$", param.getValue().getValue().getBico().getReparticao().getCombustivel().precoProperty().asString());
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colCombustivelAbas = new JFXTreeTableColumn<>("Combustível");
		colCombustivelAbas.setPrefWidth(150);
		colCombustivelAbas.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().getBico().getReparticao().getCombustivel().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<CombustivelVenda, String> colBicoAbas = new JFXTreeTableColumn<>("Bico");
		colBicoAbas.setPrefWidth(150);
		colBicoAbas.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CombustivelVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CombustivelVenda, String> param) {
				return param.getValue().getValue().getBico().nomeProperty();
			}
		});
		
		ObservableList<CombustivelVenda> abastecimentos = FXCollections.observableArrayList();
		
		if(!fechouCaixa) {
			abastecimentos.addAll(TanqueDAO.getAbastecimentosCaixa());
		}
		
		TreeItem<CombustivelVenda> rootAbas = new RecursiveTreeItem<CombustivelVenda>(abastecimentos, RecursiveTreeObject::getChildren);
		
		tblAbastecimentos.getColumns().setAll(colIdAbas, colLitrosAbas, colValorAbas, colCombustivelAbas, colBicoAbas);
		tblAbastecimentos.setRoot(rootAbas);
		tblAbastecimentos.setShowRoot(false);
		
		JFXTreeTableColumn<ServicoVenda, String> colIdServ = new JFXTreeTableColumn<>("Id");
		colIdServ.setPrefWidth(150);
		colIdServ.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().idProperty().asString();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colServicoServ = new JFXTreeTableColumn<>("Serviço");
		colServicoServ.setPrefWidth(150);
		colServicoServ.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().getServico().nomeProperty();
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colPrecoServ = new JFXTreeTableColumn<>("Preço");
		colPrecoServ.setPrefWidth(150);
		colPrecoServ.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return Bindings.concat("R$", param.getValue().getValue().getServico().precoProperty().asString());
			}
		});
		
		JFXTreeTableColumn<ServicoVenda, String> colQuilometragemServ = new JFXTreeTableColumn<>("Quilometragem");
		colQuilometragemServ.setPrefWidth(150);
		colQuilometragemServ.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ServicoVenda, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<ServicoVenda, String> param) {
				return param.getValue().getValue().quilometragemProperty().asString();
			}
		});
		
		ObservableList<ServicoVenda> servicos = FXCollections.observableArrayList();
		
		if(!fechouCaixa) {
			servicos.addAll(ServicoDAO.getServicosVendasCaixa());
		}
		
		TreeItem<ServicoVenda> rootServ = new RecursiveTreeItem<ServicoVenda>(servicos, RecursiveTreeObject::getChildren);
		
		tblServicos.getColumns().setAll(colIdServ, colServicoServ, colPrecoServ, colQuilometragemServ);
		tblServicos.setRoot(rootServ);
		tblServicos.setShowRoot(false);
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		Double totalAbastecimentos = 0.0;
		Double totalServicos = 0.0;
		
		if(!fechouCaixa) {
			for(CombustivelVenda venda : TanqueDAO.getAbastecimentosCaixa()) {
				totalAbastecimentos += venda.getLitros() * venda.getBico().getReparticao().getCombustivel().getPreco();
			}
			
			for(ServicoVenda venda : ServicoDAO.getServicosVendasCaixa()) {
				totalServicos += venda.getServico().getPreco();
			}
			
			totalTotal = totalAbastecimentos + totalServicos;
			
			btnFecharCaixa.setText("Fechar Caixa (R$" + df.format(totalTotal) + ")");
		} else {
			btnFecharCaixa.setDisable(true);
		}
		
		lblTotalAbastecimentos.setText("Total: R$" + df.format(totalAbastecimentos));
		lblTotalServicos.setText("Total: R$" + df.format(totalServicos));
		
		btnFecharCaixa.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				JFXDialogLayout mensagem_conteudo = new JFXDialogLayout();
				mensagem_conteudo.setHeading(new Text("Fechar Caixa"));
				mensagem_conteudo.setBody(new Text("Você tem certeza de que deseja fechar o caixa?"));
				
				JFXDialog mensagem_aviso = new JFXDialog(conteudoConteudo, mensagem_conteudo, JFXDialog.DialogTransition.CENTER);
				
				JFXButton mensagem_botaoSim = new JFXButton("Sim");
				mensagem_botaoSim.getStyleClass().addAll("btn", "btn_sucesso", "btn_aviso");
				
				mensagem_botaoSim.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {						
						DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
						
						Caixa caixa = new Caixa(formatoData.format(new Date()), Double.valueOf(totalTotal));
						
						if(CaixaDAO.cadastrar(caixa)) {
							mensagem_aviso.close();
							
							abastecimentos.clear();
							TreeItem<CombustivelVenda> rootAbas = new RecursiveTreeItem<CombustivelVenda>(abastecimentos, RecursiveTreeObject::getChildren);
							tblAbastecimentos.setRoot(rootAbas);
							
							servicos.clear();
							TreeItem<ServicoVenda> rootServ = new RecursiveTreeItem<ServicoVenda>(servicos, RecursiveTreeObject::getChildren);
							tblServicos.setRoot(rootServ);
							
							atualizaGrafico();
							
							btnFecharCaixa.setText("Fechar Caixa");
							btnFecharCaixa.setDisable(true);
						}
					}
				});
				
				JFXButton mensagem_botaoCancelar = new JFXButton("Cancelar");
				mensagem_botaoCancelar.getStyleClass().addAll("btn", "btn_erro", "btn_aviso");
				
				mensagem_botaoCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						mensagem_aviso.close();
					}
				});
				
				mensagem_conteudo.setActions(mensagem_botaoSim, mensagem_botaoCancelar);
				
				mensagem_aviso.show();
			}
		});

		atualizaGrafico();
	}
	
	protected void atualizaGrafico() {
		XYChart.Series dados = new XYChart.Series<>();
		
		for(Caixa caixa : CaixaDAO.getCaixas()) {
			final XYChart.Data<String, Number> data = new XYChart.Data(caixa.getVendasMes(), caixa.getVendasTotal());
			
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
					if(node != null) {
						displayLabelForData(data);
					} 
		        }
			});
			
			dados.getData().add(data);
		}
		
		grfCaixa.getData().addAll(dados);
	}
	
	private void displayLabelForData(XYChart.Data<String, Number> data) {
		final Node node = data.getNode();
		final Text dataText = new Text("R$" + data.getYValue());
		
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});

		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.5));
			}
		});
	}
}