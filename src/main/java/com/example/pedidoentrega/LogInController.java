package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Classe controller da interface log-in.
 */
public class LogInController {
    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_senha;

    @FXML
    private Button botao_entrar;

    @FXML
    private Button botao_voltar;

    @FXML
    private Label erro;


    /**
     * Método para entrar na conta do usuário.
     * @param e ActionEvent - botão entrar
     */
    public void lidarBotaoEntrar(ActionEvent e){
        if(!tf_email.getText().equals("") && !tf_senha.getText().equals("")){
            try{
                HelloApplication.display.loginGui(e, tf_email.getText(),tf_senha.getText());
            }catch(UsuarioInexistenteException er){
                erro.setText(er.getMessage());
            }
        }
        else{
            erro.setText("Campos Vazios");
        }
    }

    /**
     * Método para voltar para a tela inicial.
     * @param e ActionEvent - botão voltar
     * @throws IOException
     */
    public void lidarBotaoVoltar(ActionEvent e) throws IOException{
        InterfaceUtil.chanceScenes(e,"hello-view.fxml","Frappè Sorveteria");
    }
}
