package sample.Solvers;

import sample.Controller;


public class RecursiveBacktrack {

    boolean check(int maze[][], int x, int y) {

        return (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == Controller.pass);
    }

    boolean checkWall(int maze[][], int x, int y) {
        return maze[x][y] == Controller.wall;
    }

    boolean achiveresult(int maze[][], int x, int y) {
        return y == maze[0].length - 2 && x == maze.length - 2;
    }

    public boolean solver(int[][] maze, int x, int y, int count) {
        count++;
        if (achiveresult(maze, x, y)) {
            return true;
        }
        if (checkWall(maze, x, y)) {
            return false;
        }
        if (check(maze, x, y)) {



            maze[x][y] = Controller.step;
            if (solver(maze, x, y - 1, count))
                return true;
            if (solver(maze, x - 1, y, count))
                return true;
            if (solver(maze, x, y + 1, count))
                return true;
            if (solver(maze, x + 1, y, count)) {
                return true;
            }
            count++;
            maze[x][y] = Controller.stepBack;
            return false;
        } else {
            return false;
        }

    }
}
