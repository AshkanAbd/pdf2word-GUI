package ir.AshkanAbd;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {

    private Scene scene;
    private FXMLLoader loader;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("main.fxml"));
        controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("PDF 2 Text GUI");
        scene = new Scene(root, 640, 300);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
