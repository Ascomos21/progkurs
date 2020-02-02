package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Generators.GeneratorEller.GeneratorEller;
import sample.Generators.GeneratorPrima.GeneratorPrima;
import sample.Paint.Painter;
import sample.Solvers.RecursiveBacktrack;
import sample.Solvers.SolveHand;
import sample.Windows.WindowError;
import sample.Windows.WindowInformation;

import java.io.IOException;
import java.util.ArrayList;

import static sample.Generators.GeneratorPrima.GeneratorPrima.outlab;


public class Controller {
    public static final int wall = 0;
    public static final int pass = 1;
    public static final int step = 3;
    public static final int stepBack = 6;
    public static final int start = 8;
    public static final int exit = 9;


    private static final int height = 19;
    private static final int width = 19;

    private static long timeGenPrim;
    private static long timeSolve;
    private static long timeGentEller;
    private static long timeSolve1;


    @FXML
    private Button generateLab;
    @FXML
    private Button solveLab;
    @FXML
    private Button generateLab1;
    @FXML
    private Button getInformation;
    @FXML
    private Button getInformation1;

    public static int[][] maze;
    public static int[][] maze2;
    public static int[][] mazeSave;
    public static int[][] mazeSave2;
    public ArrayList<SolveHand.Point> points = new ArrayList<>();


    @FXML
    void initialize() {

        generateLab.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                maze = new int[height][width];
                mazeSave = new int[height][width];
                long start = System.nanoTime();
                maze = GeneratorPrima.gereatorPrima(maze, maze[0].length - 1, maze.length - 1);
                long finish = System.nanoTime();
                timeGenPrim = finish - start;
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[0].length; j++) {
                        if (maze[i][j] == Controller.pass) {
                            mazeSave[i][j] = Controller.pass;
                        } else if (maze[i][j] == Controller.exit) {
                            mazeSave[i][j] = Controller.exit;
                        } else {
                            mazeSave[i][j] = Controller.wall;
                        }
                    }

                }
                maze[1][1] = Controller.start;
                Painter.paint(maze, 60, 80);

                outlab(maze);
                maze2 = new int[height][width];
                mazeSave2 = new int[height][width];
                start = System.nanoTime();
                GeneratorEller.driver(maze2);
                finish = System.nanoTime();
                timeGentEller = finish - start;

                maze2[width - 2][height - 2] = Controller.exit;
                maze2[1][1] = Controller.start;

                Painter.paint(60, 550, maze2);
                for (int i = 0; i < maze2.length; i++) {
                    for (int j = 0; j < maze2[0].length; j++) {
                        if (maze2[i][j] == Controller.pass) {
                            maze2[i][j] = 0;
                            mazeSave2[i][j] = 0;

                        } else if (maze[i][j] == Controller.exit) {
                            mazeSave2[i][j] = Controller.exit;
                            maze2[i][j] = Controller.exit;

                        } else {
                            maze2[i][j] = 1;
                            mazeSave2[i][j] = 1;
                        }
                    }

                }

            }
        });

        solveLab.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (maze == null) {
                    WindowError windowError = new WindowError();
                    windowError.showAlertWindow();
                }


                SolveHand.solve(maze, maze.length, maze[0].length, points);
                System.out.println(points.size() + "checechechehcehchechehc");

                Painter.paintSolve(maze, 520, 80);
                SolveHand.outSolve(maze, points);

                points.clear();
                SolveHand.solve(maze2, maze2.length, maze2[0].length, points);
                Painter.paintSolve(maze2, 520, 550);

            }
        });
        generateLab1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (maze2 == null) {
                    WindowError windowError = new WindowError();
                    windowError.showAlertWindow();
                }
                long start = System.nanoTime();
                RecursiveBacktrack recursiveBacktrack = new RecursiveBacktrack();
                recursiveBacktrack.solver(mazeSave, 1, 1, 0);
                long finish = System.nanoTime();
                timeSolve = finish - start;
                Painter.paintSolve(mazeSave, 970, 80);
                start = System.nanoTime();
                recursiveBacktrack.solver(mazeSave2, 1, 1, 0);
                finish = System.nanoTime();
                timeSolve1 = finish - start;
                Painter.paintSolve(mazeSave2, 970, 550);

            }

        });
        getInformation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("aaaaaa");
                WindowInformation windowInformation = new WindowInformation();
                int step = windowInformation.getcountStep(mazeSave);
                windowInformation.showWindowInfrormation(timeGenPrim, timeSolve, step);
            }
        });
        getInformation1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                WindowInformation windowInformation = new WindowInformation();
                int step = windowInformation.getcountStep(mazeSave2);

                windowInformation.showWindowInfrormation(timeGentEller, timeSolve1, step);
            }
        });
    }

    public void startWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Windows/Information.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Information");

        window.setScene(new Scene(root));
        window.show();
    }

}
