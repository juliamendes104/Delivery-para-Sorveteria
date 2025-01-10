package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * Classe controller da interface logged-in do cliente.
 */
public class LoggeInController {
    @FXML
    private Label label_nome_user;

    @FXML
    private Label label_telefone;

    @FXML
    private Label label_email_user;

    @FXML
    private Button botao_meuPedido;

    @FXML
    private Button botao_cardapio;

    @FXML
    private Button botao_sair;

    @FXML
    private Button botao_carrinho;
    private Cliente cliente = HelloApplication.display.clienteLogado();

    /**
     * Método para mostrar na tela os dados da conta do usuário logado.
     * @param nome String - nome do cliente
     * @param email String - email do cliente
     * @param telefone String - telefone do cliente
     */
    public void displayUsuario(String nome, String email, String telefone){
        label_nome_user.setText(nome);
        label_email_user.setText(email);
        label_telefone.setText(telefone);
    }

    /**
     * Método para mudar para a interface meu-pedido.
     * @param e ActionEvent - botão meu pedido
     */
    public void lidarBotaoMeuPedido(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu Pedido");
    }

    /**
     * Método para mudar para a interface carrinho ou pagamento,
     * caso pedido tenha sido feito e está aguardando pagamento.
     * @param e ActionEvent - botão carrinho
     */
    public void lidarBotaoCarrinho(ActionEvent e){
        if(cliente.getCarrinho()!=null && cliente.getCarrinho().getStatus()==0){
            InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
        }
        else{
            InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
        }
    }

    /**
     * Método para mudar para a interface do cardapio.
     * @param e ActionEvent - botão cardapio
     * @throws IOException
     */
    public void lidarBotaoCardapio(ActionEvent e) throws IOException{
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardapio");
    }

    /**
     * Método para sair da conta do cliente e voltar para a tela inicial.
     * @param e ActionEvent - botão sair
     * @throws IOException
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException{
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }
}
