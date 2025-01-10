package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Classe Controller da interface alterarEndereco para alterar o endereço de entrega.
 */
public class AlterarEnderecoController {
    @FXML
    private TextField tf_rua,tf_bairro, tf_numero;
    @FXML
    private Label erro;

    /**
     * Método para mudar para interface meu-pedido.
     * @param e ActionEvent - botão Meu Pedido
     */
    public void lidarBotaoMeuPedido(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu Pedido");
    }

    /**
     * Método para mudar para interface carrinho.
     * @param e ActionEvent - botão Carrinho
     */
    public void lidarBotaoCarrinho(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
    }

    /**
     * Método para mudar para interface cardapio.
     * @param e ActionEvent - botão Cardapio
     */
    public void lidarBotaoCardapio(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardapio");
    }

    /**
     * Método para sair da conta e mudar para tela inicial.
     * @param e ActionEvent - botão Sair
     */
    public void lidarBotaoSair(ActionEvent e){
        HelloApplication.display.desloggedGui(); //sai da conta
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para voltar para interface pagamento.
     * @param e ActionEvent - botão Voltar
     */
    public void lidarBotaoVoltar(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
    }

    /**
     * Método para verificar se todos os campos do endereço foram preenchidos e, então,
     * chamar a interface de pagamento.
     * @param e ActionEvent - botão Confirmar
     */
    public void lidarBotaoConfirmar(ActionEvent e){
        if(!tf_rua.getText().equals("") && !tf_bairro.getText().equals("") && !tf_numero.getText().equals("")){
            Cliente cliente = HelloApplication.display.clienteLogado();
            cliente.alterarEndereco(new Endereco(tf_rua.getText(), tf_bairro.getText(), tf_numero.getText()));
            InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
        }
        else{
            erro.setText("Campos Vazios");
        }
    }
}
