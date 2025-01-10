package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controller da interface meu-pedido, que mostra o status dos pedidos,
 * o histórico dos pedidos e possui a função de cancelar pedidos.
 */
public class MeuPedidoController implements Initializable {
    @FXML
    private Button botao_carrinho;
    @FXML
    private Button botao_sair;
    @FXML
    private Button botao_cardapio;
    @FXML
    private Button botao_cancelar;
    @FXML
    private Button botao_outrosPedidos;
    @FXML
    private Pane pedidoPane;
    @FXML
    private Label statusLabel;
    @FXML
    private Label codigoLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Label erro;
    private Cliente cliente = HelloApplication.display.clienteLogado();


    /**
     * Método para mudar para a interface cardápio.
     * @param e ActionEvent - botão cardápio
     */
    public void lidarBotaoCardapio(ActionEvent e) {
        InterfaceUtil.chanceScenes(e,"cardapio.fxml","Cardápio");
    }

    /**
     * Método para mudar para a interface carrinho.
     * @param e ActionEvent - botão carrinho
     */
    public void lidarBotaoCarrinho(ActionEvent e) {
        if(cliente.getCarrinho()!=null && cliente.getCarrinho().getStatus()==0){
            InterfaceUtil.chanceScenes(e,"pagamento.fxml","Pagamento");
        }
        else{
            InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
        }
    }

    /**
     * Método para sair da conta do cliente e voltar para a tela inicial.
     * @param e ActionEvent - botão sair
     */
    public void lidarBotaoSair(ActionEvent e) {
        HelloApplication.display.desloggedGui();
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }

    /**
     * Método para cancelar pedidos, caso seja possível.
     * @param e ActionEvent - botão cancelar
     */
    public void lidarBotaoCancelar(ActionEvent e){
        for(Carrinho carrinho : cliente.getMeusPedidos()){
            if(carrinho.getCodigo().equals(codigoLabel.getText())){
                if(carrinho.getStatus() == 2){
                    System.out.println("Impossivel cancelar");
                    erro.setVisible(true);
                }
                else if(carrinho.getStatus() == 3){
                    System.out.println("Impossivel cancelar");
                    erro.setVisible(true);
                }
                else{
                    cliente.cancelar(carrinho);
                    erro.setVisible(false);
                }
                InterfaceUtil.chanceScenes(e,"meu-pedido.fxml","Meu Pedido");
            }
        }
    }

    /**
     * Método para acessar o histórico de pedidos do cliente, muda para a interface meuPedido-cliente.
     * @param e ActionEvent - botão histórico
     */
    public void lidarBotaoOutrosPedidos(ActionEvent e){
        InterfaceUtil.chanceScenes(e,"meuPedido-cliente.fxml","Histórico de pedidos");
    }

    /**
     * Método para inicializar a tela com os dados do pedidos efetuado.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        erro.setVisible(false);
        if(cliente.getMeusPedidos().size() <= 1 ){
            botao_outrosPedidos.setVisible(false);
        }

        if(!cliente.getMeusPedidos().isEmpty()){
            if(cliente.getMeusPedidos().get(0).getStatus() == 0){
                statusLabel.setText("AGUARDANDO PAGAMENTO");
            }
            else if(cliente.getMeusPedidos().get(0).getStatus() == 1){
                statusLabel.setText("CONFIRMADO");
            }
            else if(cliente.getMeusPedidos().get(0).getStatus() == 2){
                statusLabel.setText("A CAMINHO");
            }
            else if(cliente.getMeusPedidos().get(0).getStatus() == 3){
                statusLabel.setText("ENTREGUE");
            }
            codigoLabel.setText(cliente.getMeusPedidos().get(0).getCodigo());
            totalLabel.setText("R$" + Float.toString(cliente.getMeusPedidos().get(0).getPrecoTotal()));
        }
        else{
            pedidoPane.setVisible(false);
        }
    }
}
