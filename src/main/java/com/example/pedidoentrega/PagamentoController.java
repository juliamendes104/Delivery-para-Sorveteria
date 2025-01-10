package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controller da interface pagamento,
 * para preencher os dados do cartão para pagamento,
 * com opção de trocar o endereço de entrega.
 */
public class PagamentoController implements Initializable {
    @FXML
    private TextField tf_numeroCartao, tf_validade, tf_cvv, tf_cpfTitular, tf_nomeTitular;
    @FXML
    private Button botao_meuPedido;
    @FXML
    private Button botao_cardapio;
    @FXML
    private Button botao_sair;
    @FXML
    private Label erro, enderecoLabel, totalLabel;
    private Cliente cliente = HelloApplication.display.clienteLogado();

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
        InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
    }

    /**
     * Método para mudar para interface cardapio.
     * @param e ActionEvent - botão Cardapio
     */
    public void lidarBotaoCardapio(ActionEvent e) throws IOException {
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardapio");
    }

    /**
     * Método para sair da conta e mudar para tela inicial.
     * @param e ActionEvent - botão Sair
     */
    public void lidarBotaoSair(ActionEvent e) throws IOException{
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para mudar para a interface alterarEndereco.
     * @param e ActionEvent - botão Alterar endereço
     */
    public void lidarBotaoAlterarEndereco(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"alterarEndereco.fxml","Alterar Endereço");
    }

    /**
     * Método para verificar se todos os campos do pagamento foram preenchidos e, então,
     * chamar a interface para mostrar o pedido efetuado e seu status.
     * @param e ActionEvent - botão finalizar
     */
    public void lidarBotaoFinalizar(ActionEvent e){
        if(!tf_numeroCartao.getText().equals("") && !tf_cvv.getText().equals("") && !tf_nomeTitular.getText().equals("") && !tf_cpfTitular.getText().equals("") && !tf_validade.getText().equals("")){
            cliente.pagarEFinalizar();
            InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu Pedido");
        }
        else{
            erro.setText("Campos Vazios");
        }
    }

    /**
     * Método para voltar para interface pagamento.
     * @param e ActionEvent - botão Voltar
     */
    public void lidarBotaoVoltar(ActionEvent e) throws IOException {
        InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
    }

    /**
     * Método para inicializar o label com o endereço de entrega salvo no cadastro do cliente,
     * e mostra ele atualizado caso haja alteração.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enderecoLabel.setText(cliente.getEndereco().getRua() + ", " + cliente.getEndereco().getBairro() + ", " + cliente.getEndereco().getNum());
        totalLabel.setText("Total: R$" + Float.toString(cliente.getCarrinho().getPrecoTotal()));
    }
}
