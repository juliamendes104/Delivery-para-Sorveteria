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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controller da interface menu-entregador, com a lista de pedidos para entregar.
 */
public class MenuEntregadorController implements Initializable {
    @FXML
    private TableView<Carrinho> tabela_entregador;
    @FXML
    private TableColumn<Carrinho, String> pedido;
    @FXML
    private TableColumn<Carrinho, String> endereco;
    @FXML
    private TableColumn<Carrinho, String> status;
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_entregar;
    @FXML
    private Button botao_concluir;
    private Entregador entregador = HelloApplication.display.entregadorLogado();
    private ArrayList<Carrinho> listaCarrinho;

    /**
     * Método para sair da conta do entregador e voltar para a tela inicial.
     * @param e ActionEvent - botão sair
     */
    public void lidarBotaoSair(ActionEvent e){
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para entregar o pedido selecionado na tabela.
     * @param e ActionEvent - botão entregar
     */
    public void lidarBotaoEntregar(ActionEvent e){
        Carrinho carrinho = tabela_entregador.getSelectionModel().getSelectedItem();
        carrinho.entregar();
        InterfaceUtil.chanceScenes(e,"menu-entregador.fxml", "Menu Entregador");
    }

    /**
     * Método para concluir uma entrega, mudando o status para concluído.
     * @param e ActionEvent - botão concluir
     */
    public void lidarBotaoConcluir(ActionEvent e){
        Carrinho carrinho = tabela_entregador.getSelectionModel().getSelectedItem();
        carrinho.concluir();
        InterfaceUtil.chanceScenes(e,"menu-entregador.fxml", "Menu Entregador");
    }

    /**
     * Método para inicializar a tabela de pedidos para entregar.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedido.setCellValueFactory(new PropertyValueFactory<Carrinho,String>("codigo"));
        endereco.setCellValueFactory(data -> {
            Endereco endereco = data.getValue().getEndereco();
            return new SimpleStringProperty(endereco.getRua() + ", " + endereco.getBairro() + ", " + endereco.getNum());
        });
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

        listaCarrinho = entregador.getCarrinhos();
        tabela_entregador.setItems(FXCollections.observableArrayList(listaCarrinho));
    }
}
