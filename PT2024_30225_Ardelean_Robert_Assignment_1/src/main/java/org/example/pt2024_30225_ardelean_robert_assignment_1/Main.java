package org.example.pt2024_30225_ardelean_robert_assignment_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setResizable(false);
        stage.setTitle("Calculator Polinoame");

        URL iconUrl = new File("images/png-clipart-geogebra-mathematics-computer-software-svg-purple-graph-of-a-function-thumbnail-removebg-preview.png").toURI().toURL();
        Image icon = new Image(iconUrl.toString(), 32, 32, false, false); //  width (32) si height (32)
        stage.getIcons().add(icon);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
