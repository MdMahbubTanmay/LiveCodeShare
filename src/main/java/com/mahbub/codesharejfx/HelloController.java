package com.mahbub.codesharejfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void GotToUserPanel(ActionEvent event) throws IOException
    {


        root = FXMLLoader.load(getClass().getResource("user-panel.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1000,800);


        stage.setTitle("CodeShare : UserPanel");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
