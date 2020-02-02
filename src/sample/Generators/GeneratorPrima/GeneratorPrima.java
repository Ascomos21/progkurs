package sample.Generators.GeneratorPrima;

import sample.Controller;

import java.util.LinkedList;
import java.util.Random;

public class GeneratorPrima {

    private static boolean maze[][];


    public static final boolean WALL = false;
    public static final boolean PASSAGE = !WALL;


    public static int[][] gereatorPrima(int[][] maze2, int width, int height) {
        maze2 = new int[width + 1][height + 1];
        maze = new boolean[width][height];

        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random random = new Random();
        int x = 0;
        int y = 0;
        frontiers.add(new int[]{x, y, x, y});

        while (!frontiers.isEmpty()) {
            int a = random.nextInt(frontiers.size());
            System.out.println(a + "----" + x + "------" + y + "-----" + frontiers.size());
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j]) {
                        System.out.print("  ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();
            }
            final int[] f = frontiers.remove(a);
            x = f[2];
            y = f[3];
            if (maze[x][y] == WALL) {
                maze[f[0]][f[1]] = maze[x][y] = PASSAGE;
                if (x >= 2 && maze[x - 2][y] == WALL)
                    frontiers.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && maze[x][y - 2] == WALL)
                    frontiers.add(new int[]{x, y - 1, x, y - 2});
                if (x < width - 2 && maze[x + 2][y] == WALL)
                    frontiers.add(new int[]{x + 1, y, x + 2, y});
                if (y < height - 2 && maze[x][y + 2] == WALL)
                    frontiers.add(new int[]{x, y + 1, x, y + 2});
            }

        }
        for (int i = 0; i < width + 1; i++) {
            //System.out.print(1);
            maze2[i][0] = Controller.wall;
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            //System.out.print(1);
            maze2[0][i] = Controller.wall;


            for (int j = 0; j < width; j++) {
                if (maze[i][j]) {
                    // System.out.print(" ");
                    maze2[i + 1][j + 1] = Controller.pass;
                } else {
                    // System.out.print(1);
                    maze2[i + 1][j + 1] = Controller.wall;

                }
                if (i == height - 1 && j == height - 1) {
                    maze2[i][j] = Controller.exit;
                }
            }
            //  System.out.println();
        }
        outlab(maze2);
        return maze2;
    }

    public static void outlab(int[][] maze) {
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
    }
}
