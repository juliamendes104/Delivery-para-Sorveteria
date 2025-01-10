package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * Classe controller da tela inicial.
 */
public class HelloController {
    @FXML
    private Button botao_login;

    @FXML
    private Button botao_cadastrar;

    @FXML
    private Button botao_cardapio;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Método para mudar para a tela de login.
     * @param e ActionEvent login
     * @throws IOException
     */
    public void lidarLoginButton(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("log-in.fxml"));
        root = loader.load();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método para mudar para a tela de cadastro.
     * @param e ActionEvent cadastrar
     * @throws IOException
     */
    public void lidarCadastroButton(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up.fxml"));
        root = loader.load();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Cadastro");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método para mudar para a tela do cardápio.
     * @param e ActionEvent cardapio
     * @throws IOException
     */
    public void lidarCardapioButton(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardapio.fxml"));
        root = loader.load();

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Cardápio");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}