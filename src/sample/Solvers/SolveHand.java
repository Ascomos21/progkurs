package sample.Solvers;


import sample.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static sample.Solvers.SolveHand.Direction.*;

public class SolveHand {

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static class Point {
        int x, y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static boolean checkSolution(int x, int y, int height, int width) {
        if (x == height - 2 && y == width - 2) {
            return true;
        } else {
            return false;
        }
    }
    public static void solve(int[][] maze, int height, int width, ArrayList<Point> points) {
        int count =0;
        int x = 1;
        int y = 1;

        Direction direction = DOWN;
        List<Point> path = new ArrayList<>();

        ArrayList<Integer> dequeX = new ArrayList<>();
        ArrayList<Integer> dequeY = new ArrayList<>();
        maze[y][x] = Controller.step;
        while (!checkSolution(x, y, height, width)) {
            if(maze[y][x]==Controller.step){

                points.add(new Point(x,y));
                maze[y][x]=Controller.stepBack;
            }else {
                maze[y][x] = Controller.step;
                maze[points.get(points.size()-1).y][points.get(points.size()-1).x]=Controller.step;
            }
            while (true) {
                count++;


                if (direction == DOWN) {

                    if (maze[y][x - 1] != Controller.wall) {
                        direction = LEFT;
                        x--;
                        break;
                    }
                    if (maze[y + 1][x] != Controller.wall) {
                        y++;
                        break;
                    }
                }
                if (direction == UP) {

                    if (maze[y][x + 1] != Controller.wall) {
                        direction = RIGHT;
                        x++;
                        break;
                    }
                    if (maze[y - 1][x] != Controller.wall) {
                        y--;
                        break;
                    }
                }
                if (direction == RIGHT) {

                    if (maze[y + 1][x] != Controller.wall) {
                        direction = DOWN;
                        y++;
                        break;
                    }
                    if (maze[y][x + 1] != Controller.wall) {

                        x++;
                        break;
                    }
                }
                if (direction == LEFT) {

                    if (maze[y - 1][x] != Controller.wall) {
                        direction = UP;
                        y--;
                        break;
                    }
                    if (maze[y][x - 1] != Controller.wall) {
                        x--;
                        break;
                    }
                }
                if (direction == DOWN) {
                    direction = RIGHT;

                    break;
                }
                if (direction == RIGHT) {
                    direction = UP;
                    break;
                }
                if (direction == UP) {
                    direction = LEFT;
                    break;
                }
                if (direction == LEFT) {
                    direction = DOWN;
                    break;
                }
            }

        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == Controller.pass) {
                    System.out.print("  ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("asdasd");
        System.out.println("======================================");
        System.out.println("=======================================");


    }
    public static void outSolve(int[][] maze,ArrayList<Point> points) {
        Iterator<Point> iterator = points.iterator();
        for (int i=0;i<points.size();i++){
            System.out.println(maze[points.get(i).y][points.get(i).x]);
        }
        System.out.println(points.size());
    }
}
