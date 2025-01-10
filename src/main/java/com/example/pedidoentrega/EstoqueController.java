package com.example.pedidoentrega;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controller da interface estoque do administrador.
 */
public class EstoqueController implements Initializable {
    @FXML
    private Button botao_sair, botao_add;
    @FXML
    private Button botao_entregadores;
    @FXML
    private Button botao_mudarCardapio;
    @FXML
    private TableView<ItemEstoque> tabela_estoque;
    @FXML
    private TableColumn<ItemEstoque, String> coluna_item;
    @FXML
    private TableColumn<ItemEstoque,Integer> coluna_disponivel;
    @FXML
    private Spinner quantidadeSpinner;
    private Estoque estoque = HelloApplication.display.getSorveteria().getEstoque();

    /**
     * Método para sair da conta do administrador e mudar para a tela inicial.
     * @param e ActionEvent - botão sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para mudar para a interface com a lista de entregadores.
     * @param e ActionEvent botão entregadores
     */
    public void lidarBotaoEntregador(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"administrador-entregadores.fxml","Entregadores");
    }

    /**
     * Método para mudar para a interface de mudar cardápio.
     * @param e ActionEvent botão mudar cardápio
     */
    public void lidarBotaoMudarCardapio(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"mudar-cardapio.fxml","Mudar Cardápio");
    }

    /**
     * Método para mudar para a interface de lista de pedidos da sorveteria.
     * @param e ActionEvent botão lista de pedidos
     */
    public void lidarBotaoListaPedidos(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"logged-administrador.fxml","Administrador");
    }

    /**
     * Método para adicionar quantidade de itens no estoque.
     * @param e ActionEvent - botão adicionar
     */
    public void lidarBotaoAdicionar(ActionEvent e){
        if(tabela_estoque.getSelectionModel().getSelectedItem() != null){
            ItemEstoque item = tabela_estoque.getSelectionModel().getSelectedItem();
            if(item.getItem().equals("Cascão")){
                estoque.setQuantCascao((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCascao());
            }
            else if(item.getItem().equals("Casquinha")){
                estoque.setQuantCasquinha((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCasquinha());
            }
            else if(item.getItem().equals("Copo P")){
                estoque.setQuantCopoP((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopoP());
            }
            else if(item.getItem().equals("Copo M")){
                estoque.setQuantCopoM((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopoM());
            }
            else if(item.getItem().equals("Copo G")){
                estoque.setQuantCopoG((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopoG());
            }
            else if(item.getItem().equals("Copinho P")){
                estoque.setQuantCopinhoP((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopinhoP());
            }
            else if(item.getItem().equals("Copinho M")){
                estoque.setQuantCopinhoM((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopinhoM());
            }
            else if(item.getItem().equals("Copinho G")){
                estoque.setQuantCopinhoG((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantCopinhoG());
            }
            else if(item.getItem().equals("Picole/Agua")){
                estoque.setQuantPicoleAgua((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantPicoleAgua());
            }
            else if(item.getItem().equals("Picole/Leite")){
                estoque.setQuantPicoleLeite((Integer)quantidadeSpinner.getValue());
                item.setQuantidade(estoque.getQuantPicoleLeite());
            }
            InterfaceUtil.chanceScenes(e,"estoque.fxml", "Estoque");
            HelloApplication.display.atualizarArquivoEstoque(tabela_estoque);
        }
    }

    /**
     * Método para inicializar a tabela de estoque.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(0,50,0);
        quantidadeSpinner.setValueFactory(valueFactory);

        coluna_item.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItem()));
        coluna_disponivel.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantidade()).asObject());

        ObservableList<ItemEstoque> itensEstoque = FXCollections.observableArrayList();
        itensEstoque.add(new ItemEstoque("Cascão", estoque.getQuantCascao()));
        itensEstoque.add(new ItemEstoque("Casquinha", estoque.getQuantCasquinha()));
        itensEstoque.add(new ItemEstoque("Copo P", estoque.getQuantCopoP()));
        itensEstoque.add(new ItemEstoque("Copo M", estoque.getQuantCopoM()));
        itensEstoque.add(new ItemEstoque("Copo G", estoque.getQuantCopoG()));
        itensEstoque.add(new ItemEstoque("Copinho P", estoque.getQuantCopinhoP()));
        itensEstoque.add(new ItemEstoque("Copinho M", estoque.getQuantCopinhoM()));
        itensEstoque.add(new ItemEstoque("Copinho G", estoque.getQuantCopinhoG()));
        itensEstoque.add(new ItemEstoque("Picole/Agua", estoque.getQuantPicoleAgua()));
        itensEstoque.add(new ItemEstoque("Picole/Leite", estoque.getQuantPicoleLeite()));

        tabela_estoque.setItems(itensEstoque);

        HelloApplication.display.atualizarArquivoEstoque(tabela_estoque);
    }
}
