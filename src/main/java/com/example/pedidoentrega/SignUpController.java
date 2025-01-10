package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Classe controller da interface sign-up,
 * que faz o cadastro de novos clientes.
 */
public class SignUpController {
    @FXML
    private TextField tf_nome;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_senha;
    @FXML
    private TextField tf_telefone;

    @FXML
    private TextField tf_rua;

    @FXML
    private TextField tf_numero;

    @FXML
    private TextField tf_bairro;

    @FXML
    private Button botao_confirmar;

    @FXML
    private Button botao_voltar;

    @FXML
    private Label erro;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Método para confirmar o cadastro após preencher todos os campos necessáros.
     * @param e ActionEvent - botão confirmar
     */
    public void lidarBotaoConfirmar(ActionEvent e){
        if(!tf_email.getText().equals("") && !tf_senha.getText().equals("") && !tf_nome.getText().equals("") && !tf_telefone.getText().equals("") && !tf_rua.getText().equals("") && !tf_bairro.getText().equals("") && !tf_numero.getText().equals("")){
            try{
                HelloApplication.display.cadastroGui(e,tf_nome.getText(),tf_email.getText(),tf_telefone.getText(),tf_senha.getText(), tf_rua.getText(), tf_bairro.getText(), tf_numero.getText());
            }catch(Exception er){
                erro.setText(er.getMessage());
            }
        }
        else{
            erro.setText("Campos Vazios");
        }
    }

    /**
     * Método para sair do cadastro e voltar para a tela inicial.
     * @param e ActionEvent - botão voltar
     * @throws IOException
     */
    public void lidarBotaoVoltar(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Frappè Sorveteria");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
