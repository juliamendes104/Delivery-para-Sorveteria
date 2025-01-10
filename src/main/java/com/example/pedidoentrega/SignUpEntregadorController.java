package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Classe controller da interface sign-up-entregador,
 * para fazer o cadastro de novos entregadores,
 * ação possível apenas para o administrador.
 */
public class SignUpEntregadorController {
    @FXML
    private Button botao_sair, botao_confirmar;
    @FXML
    private Button botao_listaPedidos;
    @FXML
    private Button botao_mudarCardapio;
    @FXML
    private Button botao_estoque;
    @FXML
    private TextField tf_nome, tf_email, tf_telefone;
    @FXML
    private PasswordField tf_senha;
    @FXML
    private Label erro;


    /**
     * Método para confirmar os dados do novo entregador e cadastrá-lo.
     * @param e ActionEvent - botão confirmar
     */
    public void lidarBotaoConfirmar(ActionEvent e){
        if(!tf_email.getText().equals("") && !tf_senha.getText().equals("") && !tf_nome.getText().equals("") && !tf_telefone.getText().equals("")){
            try{
                HelloApplication.display.cadastroGui(e,tf_nome.getText(),tf_email.getText(),tf_telefone.getText(),tf_senha.getText());
            }catch(Exception er){
                erro.setText(er.getMessage());
            }
        }
        else{
            erro.setText("Campos Vazios");
        }
    }

    /**
     * Método para sair da conta do administrador.
     * @param e ActionEvent - botão sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
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
     * Método para mudar para a interface logged-administrador,
     * com a lista de pedidos da sorveteria.
     * @param e
     */
    public void lidarBotaoListaPedidos(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"logged-administrador.fxml","Lista de Pedidos");
    }

    /**
     * Método para mudar para a tela da lista de entregadores.
     * @param e ActionEvent - botão entregadores
     */
    public void lidarBotaoEntregador(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"administrador-entregadores.fxml","Entregadores");
    }

}
