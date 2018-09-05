package ir.AshkanAbd;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.*;

import java.io.*;


public class Controller {

    @FXML
    private Button pathBrowse, outputBrowse, extract;
    @FXML
    private TextField pathText, outputText;
    @FXML
    private Label loadingLabel , errorLabel;

    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;
    private File srcFile, outputFile;
    private ExtractData extractData;
    private Dialog dialog;

    public void initialize() {
        errorLabel = new Label();
        errorLabel.setText("Invalid file selected");
        errorLabel.setPrefWidth(320);
        errorLabel.setPrefHeight(150);
        errorLabel.setStyle("-fx-font-family: sans-serif; -fx-font-size: 25; -fx-alignment: center; -fx-text-alignment: center");
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();
        dialog = new Dialog();
        dialog.setTitle("Error");
        dialog.setHeight(180);
        dialog.setWidth(320);
        dialog.getDialogPane().setContent(errorLabel);
        pathBrowse.setOnMouseClicked(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY) {
                fileChooser.setTitle("Select PDF file");
                srcFile = fileChooser.showOpenDialog(null);
                if (srcFile != null) pathText.setText(srcFile.getAbsolutePath());
            }
        });
        outputBrowse.setOnMouseClicked(event -> {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY) {
                directoryChooser.setTitle("Select output folder");
                outputFile = directoryChooser.showDialog(null);
                if (outputFile != null) outputText.setText(outputFile.getAbsolutePath());
            }
        });
        extract.setOnMouseClicked(event -> {
            try {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY) {
                    errorLabel.setText("Extracting data...\n\nPlease wait...");
                    dialog.setTitle("Please wait");
                    dialog.show();
                    extractData = new ExtractData(srcFile, outputFile);
                    extractData.extractText();
                    extractData.extractImages();
                    errorLabel.setText("Extracting completed");
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                }
            } catch (Exception e) {
                dialog.setTitle("Error");
                errorLabel.setText("Invalid file selected");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            }
        });
    }
}
