package org.example.pt2024_30225_ardelean_robert_assignment_1;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TitleController {
    @FXML
    private ImageView imageNewPage;

    @FXML
    private VBox vBox;

    public void initialize() {
        // initializarea efectului fade in
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), vBox);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    private void handleHover() {
        // Efectul scale transition pentru hover a imaginii
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), imageNewPage);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);

        // Fade in a titlului cu intarziere
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), vBox);
        fadeIn.setToValue(1);

        // Setam tranzitia de scale si dupa terminarea ei inceperea trenzitei fade in
        SequentialTransition sequentialTransition = new SequentialTransition(scaleTransition, fadeIn);
        sequentialTransition.play();
    }

    @FXML
    private void handleExit() {
        // Fade out a titlului
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), vBox);
        fadeOut.setToValue(0);

        // Ordonarea tranziitilor
        fadeOut.setOnFinished(event -> {
            // Scale transition dupa terminarea fadeOut-lui
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), imageNewPage);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        fadeOut.play();
    }


    private void switchScene(Node node) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Calculator.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 640, 480));

            // setare icon
            URL iconUrl = new File("images/png-clipart-geogebra-mathematics-computer-software-svg-purple-graph-of-a-function-thumbnail-removebg-preview.png").toURI().toURL();
            Image icon = new Image(iconUrl.toString(), 32, 32, false, false); // width (32) and height (32)
            newStage.getIcons().add(icon);

            newStage.show();

            // CInciderea stage-ului initial
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void handleNewTab() {
        // Inceperea stage-ului nou
        switchScene(vBox);
    }

}