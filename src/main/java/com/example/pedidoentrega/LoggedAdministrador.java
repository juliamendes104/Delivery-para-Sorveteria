package com.example.pedidoentrega;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controller da interface logged-administrador, com a lista de pedidos da sorveteria.
 */
public class LoggedAdministrador implements Initializable {
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_entregadores;
    @FXML
    private Button botao_mudarCardapio;
    @FXML
    private Button botao_estoque;
    @FXML
    private TableView<Carrinho> tabela_pedidos;
    @FXML
    private TableColumn<Carrinho, String> pedido;
    @FXML
    private TableColumn<Carrinho, Float> total;
    @FXML
    private TableColumn<Carrinho, String> status;
    private Administrador administrador = HelloApplication.display.getAdministradorSorveteria();
    private ArrayList<Carrinho> listaCarrinho;

    /**
     * Método para sair da conta do administrador.
     * @param e ActionEvent - botão sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException{
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para mudar para a tela da lista de entregadores.
     * @param e ActionEvent - botão entregadores
     */
    public void lidarBotaoEntregador(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"administrador-entregadores.fxml","Entregadores");
    }

    /**
     * Método para mudar para a tela de adicionar novo sabor.
     * @param e ActionEvent - botão mudar cardápio
     */
    public void lidarBotaoMudarCardapio(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"mudar-cardapio.fxml","Mudar Cardápio");
    }

    /**
     * Método para mudar para a tela da tabela de estoque.
     * @param e ActionEvent - botão estoque
     */
    public void lidarBotaoEstoque(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"estoque.fxml","Estoque");
    }

    /**
     * Método para inicializar a tabela de pedidos da sorveteria.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedido.setCellValueFactory(new PropertyValueFactory<Carrinho,String>("codigo"));
        total.setCellValueFactory(new PropertyValueFactory<Carrinho,Float>("precoTotal"));
        status.setCellValueFactory(cellData -> {
            int status = cellData.getValue().getStatus();
            String textoStatus = "";

            if (status == 1) {
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

        listaCarrinho = administrador.getCarrinhos();
        tabela_pedidos.setItems(FXCollections.observableArrayList(listaCarrinho));
    }
}
