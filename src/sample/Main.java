package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Paint.Painter;

public class Main extends Application {
    public Pane root;

    public void start(Stage primaryStage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("Windows/sample.fxml"));
        Painter.main = this;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1600, 950));
        primaryStage.show();
        new Controller().startWindow();

    }

    public static void main(String[] args) {
        launch();
    }
}
