package com.example.pedidoentrega;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InterfaceUtil {

    /**
     * Método para trocar de tela.
     * @param e
     * @param fxmlFile
     * @param titulo
     */
    public static void chanceScenes(ActionEvent e, String fxmlFile, String titulo){
        try{
            FXMLLoader loader = new FXMLLoader(InterfaceUtil.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException er){
            System.err.println(er.getMessage());
        }
    }
}
