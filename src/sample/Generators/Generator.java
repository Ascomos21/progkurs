package sample.Generators;

import sample.Controller;

import java.util.Random;

public class Generator {

    static boolean deadend(int x, int y, int[][] maze, int height, int width) {

        int a = 0;

        if (x != 1) {
            if (maze[y][x - 2] == Controller.pass)
                a += 1;
        } else a += 1;

        if (y != 1) {
            if (maze[y - 2][x] == Controller.pass)
                a += 1;
        } else a += 1;

        if (x != width - 2) {
            if (maze[y][x + 2] == Controller.pass)
                a += 1;
        } else a += 1;

        if (y != height - 2) {
            if (maze[y + 2][x] == Controller.pass)
                a += 1;
        } else a += 1;

        if (a == 4)
            return true;
        else
            return false;
    }

    public static void out(int[][] maze, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                /*if (i == height - 2 && width - 2 == j) {
                    maze[i][j] = 3;
                    System.out.print("2");
                    j++;
                }*/
                switch (maze[i][j]) {
                    case Controller.wall:
                        System.out.print("0 ");
                        break;
                    case Controller.pass:
                        System.out.print("  ");
                        break;
                    case Controller.step:
                        System.out.println("3 ");
                }
            }
            System.out.println();
        }
    }

    public static void mazemake(int[][] maze, int height, int width) {
        int x, y, c, a;
        Random random = new Random();

        boolean b;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = Controller.wall;
            }
        }
        x = 3;
        y = 3;
        a = 0;


        while (a < 10001) {
            maze[y][x] = Controller.pass;
            a++;

            while (true) {
                c = random.nextInt(4);
                switch (c) {
                    case 0:
                        if (y != 1)
                            if (maze[y - 2][x] == Controller.wall) { // Вверх
                                maze[y - 1][x] = Controller.pass;
                                maze[y - 2][x] = Controller.pass;
                                y -= 2;
                            }
                    case 1:
                        if (y != height - 2)
                            if (maze[y + 2][x] == Controller.wall) { // Вниз
                                maze[y + 1][x] = Controller.pass;
                                maze[y + 2][x] = Controller.pass;
                                y += 2;
                            }
                    case 2:
                        if (x != 1)
                            if (maze[y][x - 2] == Controller.wall) { // Налево
                                maze[y][x - 1] = Controller.pass;
                                maze[y][x - 2] = Controller.pass;
                                x -= 2;
                            }
                    case 3:
                        if (x != width - 2)
                            if (maze[y][x + 2] == Controller.wall) { // Направо
                                maze[y][x + 1] = Controller.pass;
                                maze[y][x + 2] = Controller.pass;
                                x += 2;
                            }

                }
                if (deadend(x, y, maze, height, width)) {

                    break;
                }


            }
            if (deadend(x, y, maze, height, width)) {
                do {
                    // System.out.println(random.nextInt(4) + 1);

                    x = 2 * (random.nextInt((width - 1) / 2)) + 1;
                    y = 2 * (random.nextInt((height - 1) / 2)) + 1;
                } while (maze[y][x] != Controller.pass);

            }
        }
        maze[1][1] = Controller.step;
        maze[maze.length-2][maze[0].length-2] = Controller.step;
    }

}
