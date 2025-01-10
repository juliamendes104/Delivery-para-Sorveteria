package com.example.pedidoentrega;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controller da interface de histórico de pedidos do cliente.
 */
public class historicoController implements Initializable {
    @FXML
    private Label erroLabel;
    @FXML
    private Button botao_carrinho, botao_voltar;
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_cardapio;
    @FXML
    private Button botao_cancelar;
    @FXML
    private TableView<Carrinho> tabela_pedidos;
    @FXML
    private TableColumn<Carrinho, String>  coluna_pedido;
    @FXML
    private TableColumn<Carrinho, Float>  coluna_total;
    @FXML
    private TableColumn<Carrinho, String>  coluna_status;
    private Cliente cliente = HelloApplication.display.clienteLogado();
    private ArrayList<Carrinho> listaCarrinho;

    /**
     * Método para mudar para a interface do cardápio.
     * @param e ActionEvent botão cardapio
     */
    public void lidarBotaoCardapio(ActionEvent e) {
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardápio");
    }

    /**
     * Método para mudar para a interface do carrinho.
     * @param e ActionEvent botão carrinho
     */
    public void lidarBotaoCarrinho(ActionEvent e) {
        if(cliente.getCarrinho()!=null && cliente.getCarrinho().getStatus()==0){
            InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
        }
        else{
            InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
        }
    }

    /**
     * Método para sair da conta do cliente.
     * @param e ActionEvent botão sair
     */
    public void lidarBotaoSair(ActionEvent e) {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para voltar para a interface do meu-pedido.
     * @param e ActionEvent botão voltar
     */
    public void lidarBotaoVoltar(ActionEvent e) {
        InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu pedido");
    }

    /**
     * Método para cancelar um pedido do histórico de pedidos.
     * @param e ActionEvent botão cancelar
     */
    public void lidarBotaoCancelar(ActionEvent e){
        Carrinho retirarCarrinho = tabela_pedidos.getSelectionModel().getSelectedItem();
        if (retirarCarrinho != null) {
            TableColumn<Carrinho, String> colunaPedido = (TableColumn<Carrinho, String>) tabela_pedidos.getColumns().get(0);
            String codigo_pedido = colunaPedido.getCellData(retirarCarrinho);
            for (Carrinho carrinho : cliente.getMeusPedidos()) {
                if (carrinho.getCodigo().equals(codigo_pedido)) {
                    if (carrinho.getStatus() == 2) {
                        erroLabel.setText("Impossível cancelar. Já saiu para entrega.");
                    } else if (carrinho.getStatus() == 3) {
                        erroLabel.setText("Impossível cancelar. Este pedido já foi entregue.");
                    } else {
                        erroLabel.setText("");
                        tabela_pedidos.getItems().remove(retirarCarrinho); //retira o pedido da tabela
                        cliente.cancelar(carrinho);
                    }
                    InterfaceUtil.chanceScenes(e, "meuPedido-ciente.fxml", "Histórico de pedidos");
                }
            }
        }
    }

    /**
     * Método para inicializar a tabela do histórico de pedidos.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coluna_pedido.setCellValueFactory(new PropertyValueFactory<Carrinho,String>("codigo"));
        coluna_total.setCellValueFactory(new PropertyValueFactory<Carrinho,Float>("precoTotal"));
        coluna_status.setCellValueFactory(cellData -> {
            int status = cellData.getValue().getStatus();
            String textoStatus = "";

            if(status == 0){
                textoStatus = "AGUARDANDO PAGAMENTO";
            }
            else if (status == 1) {
                textoStatus = "CONFIRMADO";
            } else if (status == 2) {
                textoStatus = "ENTREGANDO";
            } else if (status == 3) {
                textoStatus = "CONCLUIDO";
            } else {
                textoStatus = "CANCELADO";
            }

            return new SimpleStringProperty(textoStatus);
        });

        listaCarrinho = cliente.getMeusPedidos();
        tabela_pedidos.setItems(FXCollections.observableArrayList(listaCarrinho));
    }
}
