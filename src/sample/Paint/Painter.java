package sample.Paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Controller;
import sample.Main;

import java.util.ArrayList;

import static sample.Controller.maze;

public class Painter {
    static Image exit = new Image("exit.png");
    static Image start = new Image("start.png");

    static int x;
    static int y;
    static int count = 0;
    public static Main main;
    public static ArrayList<Object> elements = new ArrayList<>();

    public static void check(int x1) {
        int checkCount = maze[0].length;
        if (count == checkCount + 1) {
            y += 20;
            count = 1;
            x = x1 - 20 * maze[0].length;
        }
        x += 20;
    }

    public static Rectangle newWall(int maze[][]) {
        count++;
        check(x);
        Rectangle rectangleBlack = new Rectangle(x, y, 20, 20);
        rectangleBlack.setFill(Color.BLACK);
        elements.add(rectangleBlack);
        main.root.getChildren().add(rectangleBlack);
        return rectangleBlack;
    }

    public static Rectangle newPass(int maze[][]) {
        count++;
        check(x);
        Rectangle rectangle = new Rectangle(x, y, 20, 20);
        rectangle.setFill(Color.WHITE);
        elements.add(rectangle);
        main.root.getChildren().add(rectangle);
        return rectangle;
    }

    public static Rectangle newStep(int maze[][]) {
        count++;
        check(x);
        Rectangle rectangle = new Rectangle(x, y, 20, 20);
        rectangle.setFill(Color.GREEN);
        elements.add(rectangle);
        main.root.getChildren().add(rectangle);

        return rectangle;
    }

    public static Rectangle newStepBack(int maze[][]) {
        count++;
        check(x);
        Rectangle rectangle = new Rectangle(x, y, 20, 20);
        rectangle.setFill(Color.GREY);
        elements.add(rectangle);
        main.root.getChildren().add(rectangle);
        return rectangle;
    }

    public static ImageView newExit() {
        count++;
        check(x);
        ImageView imageView = new ImageView(exit);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setX(x);
        imageView.setY(y);

        main.root.getChildren().add(imageView);
        return imageView;

    }

    public static ImageView newStart() {

        count++;
        check(x);

        ImageView imagestart = new ImageView(start);
        imagestart.setFitHeight(20);
        imagestart.setFitWidth(20);
        imagestart.setX(x);
        imagestart.setY(y);
        main.root.getChildren().add(imagestart);

        return imagestart;

    }


    public static void paint(int maze[][], int x1, int y1) {
        x = x1;
        y = y1;
        count = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                print(maze, i, j);
            }
        }

    }

    public static void paint(int x1, int y1, int maze[][]) {
        x = x1;
        y = y1;
        count = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                print1(maze, i, j);
            }
        }
    }

    public static void paintSolve(int maze[][], int x1, int y1) {
        x = x1;
        y = y1;
        count = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                printSolve(maze, i, j);

            }

        }

    }

    static void print(int maze[][], int i, int j) {
        if (maze[i][j] == Controller.wall) {
            elements.add(newWall(maze));
        } else if (maze[i][j] == Controller.step) {
            elements.add(newStep(maze));
        } else if (maze[i][j] == Controller.exit) {
            elements.add(newExit());
        } else if (maze[i][j] == Controller.start) {
            elements.add(newStart());
        } else {
            elements.add(newPass(maze));

        }
    }

    static void print1(int maze[][], int i, int j) {
        if (maze[i][j] == Controller.pass) {
            elements.add(newWall(maze));
        } else if (maze[i][j] == Controller.step) {
            elements.add(newStep(maze));
        } else if (maze[i][j] == Controller.exit) {
            elements.add(newExit());
        } else if (maze[i][j] == Controller.start) {
            elements.add(newStart());
        } else {
            elements.add(newPass(maze));
        }
    }

    static void printSolve(int maze[][], int i, int j) {

        if (maze[i][j] == Controller.wall) {
            elements.add(newWall(maze));
        } else if (maze[i][j] == Controller.exit) {
            elements.add(newExit());
        } else if (maze[i][j] == Controller.step) {
            elements.add(newStep(maze));
        } else if (maze[i][j] == Controller.stepBack) {
            elements.add(newStepBack(maze));
        } else {
            elements.add(newPass(maze));
        }
    }
}
