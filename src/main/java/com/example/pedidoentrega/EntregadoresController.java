package com.example.pedidoentrega;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Classe controller da interface com a lista de entregadores do administrador.
 */
public class EntregadoresController implements Initializable {
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_cadastrar;
    @FXML
    private Button botao_demitir;
    @FXML
    private Button botao_listaPedidos;
    @FXML
    private Button botao_mudarCardapio;
    @FXML
    private Button botao_estoque;
    @FXML
    private ListView<Entregador> lista_entregadores;

    private SorveteriaSistema sorveteria;

    /**
     * Método para sair da conta do administrador e voltar para a tela inicial.
     * @param e ActionEvent - sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para mudar para a interface sign-up-entregador.
     * @param e ActionEvent - cadastrar entregador
     */
    public void lidarBotaoCadastrar(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"sign-up-entregador.fxml","Cadastrar entregador");
    }

    /**
     * Método para mudar para a interface logged-administrador,
     * com a lista de pedidos da sorveteria.
     * @param e
     */
    public void lidarBotaoListaPedidos(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"logged-administrador.fxml","Administrador");
    }

    /**
     * Método para mudar para a interface mudar-cardapio.
     * @param e ActionEvent mudar cardápio
     */
    public void lidarBotaoMudarCardapio(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"mudar-cardapio.fxml","Mudar Cardápio");
    }

    /**
     * Método para mudar para a interface estoque.
     * @param e ActionEvent botão estoque
     */
    public void lidarBotaoEstoque(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"estoque.fxml","Estoque");
    }

    /**
     * Método para demitir um entregador.
     * @param e
     */
    public void lidarBotaoDemitir(ActionEvent e){
        Entregador entregador = lista_entregadores.getSelectionModel().getSelectedItem();
        lista_entregadores.getItems().remove(entregador); //retira o entregador da ListView
        sorveteria.removerEntregador(entregador); //retira o entregador da lista de entregadores
        ObservableList<Entregador> items = lista_entregadores.getItems();
        HelloApplication.display.atualizarArquivoEntregadores(items); //função para apagar entregador do arquivo
    }

    /**
     * Método para inicializar a lista de entregadores.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sorveteria = HelloApplication.display.getSorveteria();
        ArrayList<Entregador> entregadores = sorveteria.getEntregadores();

        lista_entregadores.getItems().addAll(entregadores);
    }

}
