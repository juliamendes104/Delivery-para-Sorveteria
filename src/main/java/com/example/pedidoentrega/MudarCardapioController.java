package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe controller da interface mudar-cardapio,
 * permite adicionar novos sabores de sorvete.
 */
public class MudarCardapioController {
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_entregadores;
    @FXML
    private Button botao_listaPedidos;
    @FXML
    private Button botao_confirmar;
    @FXML
    private Button botao_estoque;
    @FXML
    private TextField tf_novoSabor;
    @FXML
    private Label erro;

    /**
     * Método para sair da conta do administrador e voltar para a tela inicial.
     * @param e ActioEvent - botão sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para mudara para a interface com a lista de entregadores.
     * @param e ActionEvent - botão entregadores
     */
    public void lidarBotaoEntregador(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"administrador-entregadores.fxml","Entregadores");
    }

    /**
     * Método para mudar para a tela com a lista de pedidos da sorveteria.
     * @param e ActionEvent - botão lista de pedidos
     */
    public void lidarBotaoListaPedidos(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"logged-administrador.fxml","Administrador");
    }

    /**
     * Método para confirmar a ação de adicionar sabor de sorvete.
     * @param e ActionEvent - botão confirmar
     * @throws IOException
     */
    public void lidarBotaoConfirmar(ActionEvent e) throws IOException {
        if(tf_novoSabor.getText() != null && !tf_novoSabor.getText().equals("")){
            HelloApplication.display.getSorveteria().adicionarSaborSorvete(tf_novoSabor.getText());

            FileWriter arquivoSabores = new FileWriter("src/main/java/com/example/pedidoentrega/sabores.txt", true);
            arquivoSabores.write(tf_novoSabor.getText() + "\n");
            arquivoSabores.close();

            erro.setText("Sabor adicionado");
            tf_novoSabor.setText("");
        }
        else{
            erro.setText("Campo Vazio");
        }
    }

    /**
     * Método para mudar para a inferface estoque, com a tabela do estoque.
     * @param e ActionEvent - botão estoque
     */
    public void lidarBotaoEstoque(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"estoque.fxml","Estoque");
    }
}
