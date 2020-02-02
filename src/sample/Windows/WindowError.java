package sample.Windows;

import javafx.scene.control.Alert;

public class WindowError {
    public void showAlertWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Test arr is empty");
        alert.setHeaderText("Results");
        alert.setContentText("Maze is empty please create new maze");
        alert.showAndWait();
    }

}
