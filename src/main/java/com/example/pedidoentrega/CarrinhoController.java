package com.example.pedidoentrega;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controller da interface carrinho.
 */
public class CarrinhoController implements Initializable {
    @FXML
    private Button botao_meuPedido;
    @FXML
    private Button botao_cardapio;
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_finalizar;
    @FXML
    private Button botao_remover;
    @FXML
    private TableView<Pedido> tabela_pedidos;
    @FXML
    private TableColumn<Pedido, String> produto;
    @FXML
    private TableColumn<Pedido, Integer> quantidade;
    @FXML
    private TableColumn<Pedido, Float> unidade;
    @FXML
    private TableColumn<Pedido, Float> total;

    private Cliente cliente = HelloApplication.display.clienteLogado();
    private ArrayList<Pedido<?>> listaPedidos;


    /**
     * Método para mudar para interface meu-pedido.
     * @param e ActionEvent - botão Meu Pedido
     */
    public void lidarBotaoMeuPedido(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu Pedido");
    }

    /**
     * Método para mudar para interface cardapio.
     * @param e ActionEvent - botão Cardapio
     */
    public void lidarBotaoCardapio(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardapio");
    }

    /**
     * Método para mudar para interface inicial.
     * @param e ActionEvent - botão Sair
     */
    public void lidarBotaoSair(ActionEvent e){
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para remover pedido do carrinho.
     * @param e ActionEvent - botão Remover
     */
    public void lidarBotaoRemover(ActionEvent e){
        Pedido pedido = tabela_pedidos.getSelectionModel().getSelectedItem();
        tabela_pedidos.getItems().remove(pedido);
        cliente.removerPedido(pedido);
    }

    /**
     * Método para mudar para interface pagamento.
     * @param e ActionEvent - botão Finalizar
     */
    public void lidarBotaoFinalizar(ActionEvent e){
        if(!cliente.getPedidos().isEmpty()){
            cliente.finalizarPedido();
            InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
        }
        else{
            System.out.println("Carrinho vazio");
        }
    }

    /**
     * Método para inicializar a tabela do carrinho.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produto.setCellValueFactory(new PropertyValueFactory<Pedido,String>("descricao"));
        quantidade.setCellValueFactory(new PropertyValueFactory<Pedido,Integer>("quantidade"));
        unidade.setCellValueFactory(new PropertyValueFactory<Pedido,Float>("unidade"));
        total.setCellValueFactory(new PropertyValueFactory<Pedido,Float>("valor"));

        listaPedidos = cliente.getPedidos();
        tabela_pedidos.setItems(FXCollections.observableArrayList(listaPedidos));
    }
}
