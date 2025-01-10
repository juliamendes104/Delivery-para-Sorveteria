package com.example.pedidoentrega;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Classe Controller da interface cardapio.
 */
public class CardapioController implements Initializable {
    @FXML
    private ChoiceBox<String> saboresChoiceBox;
    @FXML
    private ChoiceBox<String> milkshakeChoiceBox;
    @FXML
    private ChoiceBox<String> picoleChoiceBox;
    @FXML
    private ComboBox<String> sorveteTipo;
    @FXML
    private Spinner<Integer> sorveteSpinner;
    @FXML
    private Spinner<Integer> milkshakeSpinner;
    @FXML
    private Spinner<Integer> picoleSpinner;
    @FXML
    private Button botao_addSorvete;
    @FXML
    private Button botao_addMilkshake;
    @FXML
    private Button botao_addPicole;
    @FXML
    private RadioButton botao_P;
    @FXML
    private RadioButton botao_M;
    @FXML
    private RadioButton botao_G;
    @FXML
    private RadioButton botao_agua;
    @FXML
    private RadioButton botao_leite;
    @FXML
    private Button botao_voltar;
    @FXML
    private Button botao_carrinho;
    @FXML
    private Label label_precoPicole;
    @FXML
    private Label label_precoMilkshake;
    @FXML
    private Label label_precoSorvete;
    @FXML
    private Pane notificacaoPane;
    @FXML
    private Label erroSorvete;
    @FXML
    private Label erroMilkshake;
    @FXML
    private Label erroPicole;
    private Estoque estoque;

    /**
     * Método de inicialização, pega todas as informações dos produtos:
     * sabores, tamanhos, tipos, etc.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estoque = HelloApplication.display.getSorveteria().getEstoque();
        Set<String> sabores = estoque.getSabores();
        notificacaoPane.setVisible(false);

        saboresChoiceBox.getItems().addAll(sabores);
        saboresChoiceBox.setValue(sabores.iterator().next());
        milkshakeChoiceBox.getItems().addAll(sabores);
        milkshakeChoiceBox.setValue(sabores.iterator().next());
        picoleChoiceBox.getItems().addAll(sabores);
        picoleChoiceBox.setValue(sabores.iterator().next());


        sorveteTipo.getItems().addAll("Cascao", "Casquinha", "Copinho P", "Copinho M", "Copinho G");


        SpinnerValueFactory<Integer> valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,1);
        sorveteSpinner.setValueFactory(valueFactory);
        SpinnerValueFactory<Integer> milkshakeValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,1);
        milkshakeSpinner.setValueFactory(milkshakeValueFactory);
        SpinnerValueFactory<Integer> picoleValueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,1);
        picoleSpinner.setValueFactory(picoleValueFactory);

        milkshakeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            label_precoMilkshake.setText("R$" + Float.toString(milkshakeSpinner.getValue()*Sorvete.setPreco(this.getTamanhoMilkshake())));
        });
        picoleSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            label_precoPicole.setText("R$" + Float.toString(picoleSpinner.getValue()*Sorvete.setPreco(this.getBasePicole())));
        });
        sorveteSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            label_precoSorvete.setText("R$" + Float.toString(sorveteSpinner.getValue()*Sorvete.setPreco(sorveteTipo.getValue())));
        });
    }

    /**
     * Método para adicionar um sorvete no carrinho,
     * caso todos os campos necessários estejam selecionados.
     * @param e ActionEvent - botão Adicionar ao Carrinho
     */
    public void lidarBotaoAddSorvete(ActionEvent e){
        if(HelloApplication.display.clienteLogado() != null){
            Cliente cliente = HelloApplication.display.clienteLogado();
            if(sorveteTipo.getValue() == null){
                erroSorvete.setText("Selecione um tipo");

            }
            else if(sorveteTipo.getValue().equals("Casquinha") || sorveteTipo.getValue().equals("Cascao")){
                String tipo = sorveteTipo.getValue();
                Casquinha casquinha = new Casquinha(saboresChoiceBox.getValue(), estoque, tipo);
                cliente.adicionarPedido(casquinha,sorveteSpinner.getValue());
                if(tipo.equals("Casquinha")){
                    estoque.setQuantCasquinha(-1*sorveteSpinner.getValue());
                }
                else{
                    estoque.setQuantCascao(-1*sorveteSpinner.getValue());
                }
                notificacaoPane.setVisible(true);
                erroSorvete.setText("");
            }
            else if(sorveteTipo.getValue().equals("Copinho P") || sorveteTipo.getValue().equals("Copinho M") || sorveteTipo.getValue().equals("Copinho G")){
                String tipo = sorveteTipo.getValue();
                Copinho copinho = new Copinho(saboresChoiceBox.getValue(), estoque, tipo);
                cliente.adicionarPedido(copinho,sorveteSpinner.getValue());
                if(tipo.equals("Copinho P")){
                    estoque.setQuantCopinhoP(-1*sorveteSpinner.getValue());
                }
                else if(tipo.equals("Copinho M")){
                    estoque.setQuantCopinhoM(-1*sorveteSpinner.getValue());
                }
                else{
                    estoque.setQuantCopinhoG(-1*sorveteSpinner.getValue());
                }
                notificacaoPane.setVisible(true);
                erroSorvete.setText("");
            }
        }
        else{
            InterfaceUtil.chanceScenes(e,"log-in.fxml","Login");
        }
        this.setVisible();
    }

    /**
     * Método para adicionar um milkshake no carrinho,
     * caso todos os campos necessários estejam selecionados.
     * @param e ActionEvent - botão Adicionar ao Carrinho
     */
    public void lidarBotaoAddMilkshake(ActionEvent e){
        if(HelloApplication.display.clienteLogado() != null) {
            Cliente cliente = HelloApplication.display.clienteLogado();
            if (botao_P.isSelected()) {
                Milkshake milkshake = new Milkshake(milkshakeChoiceBox.getValue(), estoque, "CopoP");
                cliente.adicionarPedido(milkshake,milkshakeSpinner.getValue());
                estoque.setQuantCopoP(-1*milkshakeSpinner.getValue());
                notificacaoPane.setVisible(true);
                erroMilkshake.setText("");
            } else if (botao_M.isSelected()) {
                Milkshake milkshake = new Milkshake(milkshakeChoiceBox.getValue(), estoque, "CopoM");
                cliente.adicionarPedido(milkshake,milkshakeSpinner.getValue());
                estoque.setQuantCopoM(-1*milkshakeSpinner.getValue());
                notificacaoPane.setVisible(true);
                erroMilkshake.setText("");
            } else if (botao_G.isSelected()) {
                Milkshake milkshake = new Milkshake(milkshakeChoiceBox.getValue(), estoque, "CopoG");
                cliente.adicionarPedido(milkshake,milkshakeSpinner.getValue());
                estoque.setQuantCopoG(-1*milkshakeSpinner.getValue());
                notificacaoPane.setVisible(true);
                erroMilkshake.setText("");
            } else {
                erroMilkshake.setText("Selecione um tamanho");
            }
        }
        else{
            InterfaceUtil.chanceScenes(e,"log-in.fxml","Login");
        }
        this.setVisible();
    }

    /**
     * Método para adicionar um picolé no carrinho,
     * caso todos os campos necessários estejam selecionados.
     * @param e ActionEvent - botão Adicionar ao Carrinho
     */
    public void lidarBotaoAddPicole(ActionEvent e){
        if(HelloApplication.display.clienteLogado() != null) {
            Cliente cliente = HelloApplication.display.clienteLogado();
            if (botao_agua.isSelected()) {
                Picole picole = new Picole(picoleChoiceBox.getValue(), estoque, "PicoleAgua");
                cliente.adicionarPedido(picole,picoleSpinner.getValue());
                estoque.setQuantPicoleAgua(-1*picoleSpinner.getValue());
                notificacaoPane.setVisible(true);
                erroPicole.setText("");
            } else if (botao_leite.isSelected()) {
                Picole picole = new Picole(picoleChoiceBox.getValue(), estoque, "PicoleLeite");
                cliente.adicionarPedido(picole,picoleSpinner.getValue());
                estoque.setQuantPicoleLeite(-1*picoleSpinner.getValue());
                notificacaoPane.setVisible(true);
                erroPicole.setText("");
            } else {
                erroPicole.setText("Selecione um tipo");
            }
        }
        else{
            InterfaceUtil.chanceScenes(e,"log-in.fxml","Login");
        }
        this.setVisible();
    }

    /**
     * Método para entrar na interface do carrinho,
     * caso o usuário esteja logado, ou pede login caso contrário.
     * @param e ActionEvent - botão Carrinho
     */
    public void lidarBotaoCarrinho(ActionEvent e){
        if(HelloApplication.display.clienteLogado() != null) {
            Cliente cliente = HelloApplication.display.clienteLogado();
            InterfaceUtil.chanceScenes(e,"carrinho.fxml","Carrinho");
        }
        else{
            InterfaceUtil.chanceScenes(e,"log-in.fxml","Login");
        }
    }

    /**
     * Método para sair do carrinho,
     * volta a tela do usuário caso esteja logado, ou para tela de login caso contrário.
     * @param e ActionEvent - botão Voltar
     */
    public void lidarBotaoVoltar(ActionEvent e) throws IOException {
        if(HelloApplication.display.clienteLogado() != null){
            Cliente cliente = HelloApplication.display.clienteLogado();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
            Parent root = loader.load();
            LoggeInController loggedinController = loader.getController();
            loggedinController.displayUsuario(cliente.getNome(),cliente.getEmail(),cliente.getTelefone());
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setTitle("Menu");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else{
            InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
        }
    }

    /**
     * Método para mostrar o preço do milkshake.
     */
    public void lidarBotaoMilkshake(){
        label_precoMilkshake.setText("R$" +Float.toString(milkshakeSpinner.getValue()*Sorvete.setPreco(this.getTamanhoMilkshake())));
    }

    /**
     * Método para pegar o tamanho do milkshake.
     * @return retorna otamanho do milkshake (P,M,G)
     */
    public String getTamanhoMilkshake(){
        if(botao_P.isSelected()){
            return "CopoP";
        }
        else if(botao_M.isSelected()){
            return "CopoM";
        }
        else if(botao_G.isSelected()){
            return "CopoG";
        }
        else{
            return "Vazio";
        }
    }

    /**
     * Método para mostrar o preço do picolé.
     */
    public void lidarBotaoPicole(){
        label_precoPicole.setText("R$" +Float.toString(picoleSpinner.getValue()*Sorvete.setPreco(getBasePicole())));
    }

    /**
     * Método para pegar a base do picolé.
     * @return retorna a base do picolé
     */
    public String getBasePicole(){
        if(botao_agua.isSelected()){
            return "PicoleAgua";
        }
        else if(botao_leite.isSelected()){
            return "PicoleLeite";
        }
        else{
            return "Vazio";
        }
    }

    /**
     * Método para mostrar o preço do sorvete.
     */
    public void lidarBotaoSorvete(){
        label_precoSorvete.setText("R$" +Float.toString(sorveteSpinner.getValue()*Sorvete.setPreco(sorveteTipo.getValue())));
    }

    /**
     * Método para aparecer mensagem avisando que foi adicionado ao carrinho.
     */
    public void setVisible(){
        if (notificacaoPane.isVisible()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> notificacaoPane.setVisible(false));
            pause.play();
        }
    }

}