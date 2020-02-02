package sample.Windows;

import javafx.scene.control.Alert;
import sample.Controller;

public class WindowInformation {

    public void showWindowInfrormation(long timeGen, long timeSolve, int step) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Інформація про лабіринт");
        alert.setHeaderText("Інформація про пошук виходу через лабіринт");
        alert.setContentText("Кількість кроків для виходу найкоротшим шляхом " + step + '\n' +
                "Кількість часу для генерації лабіринту " + timeGen + '\n' +
                "Кількість часу для пошуку виходу через лабіринт" + timeSolve);
        alert.showAndWait();

    }

    public int getcountStep(int[][] maze) {
        int count = 0;
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == Controller.step) {
                    count++;
                }
            }
        }
        return count;
    }
}
