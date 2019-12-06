package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Intersection;

import java.util.ArrayList;


public class Mapper {
    private Intersection[][] intersections;
    private int x = 20;
    private int y = 20;
    private char facingDirection;


    public Mapper() {
        intersections = new Intersection[40][40];
        facingDirection = 'N';
    }

    public void addIntersection(boolean front, boolean right, boolean left) {

        if (intersections[x][y] != null) {
            intersections[x][y].count();
        }

        switch (facingDirection) {
            case 'N':
                intersections[x][y] = new Intersection(front, right, true, left);
                if (right) {
                    facingDirection = 'E';
                    //turn right

                    //add x because going right once
                    x++;
                } else if (front) {
                    //go foward

                    //y changes
                    y++;
                } else if (left) {
                    facingDirection = 'W';
                    //turn left

                    //x changes going left once
                    x--;
                } else {
                    facingDirection = 'S';
                    //turn 180 degrees

                    //goes back once
                    y--;
                }
                break;
            case 'E':
                intersections[x][y] = new Intersection(front, right, true, left);
                if (right) {
                    facingDirection = 'S';
                    //turn right

                    //add x because going right once
                    y--;
                } else if (front) {
                    //go foward

                    //y changes
                    x++;
                } else if (left) {
                    facingDirection = 'N';
                    //turn left

                    //x changes going left once
                    y++;
                } else {
                    facingDirection = 'W';
                    //turn 180 degrees

                    //goes back once
                    x--;
                }
                break;
            case 'S':
                intersections[x][y] = new Intersection(true, left, front, right);
                if (right) {
                    facingDirection = 'W';
                    //turn right

                    //add x because going right once
                    x--;
                } else if (front) {
                    //go foward

                    //y changes
                    y--;
                } else if (left) {
                    facingDirection = 'E';
                    //turn left

                    //x changes going left once
                    x++;
                } else {
                    facingDirection = 'N';
                    //turn 180 degrees

                    //goes back once
                    y++;
                }
                break;
            case 'W':
                intersections[x][y] = new Intersection(right, true, left, front);
                if (right) {
                    facingDirection = 'N';
                    //turn right

                    y++;
                } else if (front) {
                    //go foward


                    x--;
                } else if (left) {
                    facingDirection = 'S';
                    //turn left

                    y--;
                } else {
                    facingDirection = 'E';
                    //turn 180 degrees

                    x++;
                }
                break;
        }
    }

    public void printMap() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (intersections[i][j] == null) {
                    System.out.print("0");
                } else {
                    System.out.print(intersections[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    private ArrayList mapSolver() {
        ArrayList<Character> directions = new ArrayList<>();
        x = 20;
        y = 20;
        Intersection prevIntersection = intersections[20][20];
        boolean exitFound = false;
        while (!exitFound) {
            if (intersections[x][y + 1].getCounter() == 1 && intersections[x][y + 1] != prevIntersection) {
                directions.add('N');
                y++;
                prevIntersection = intersections[x][y];
            } else if (intersections[x + 1][y].getCounter() == 1 && intersections[x + 1][y] != prevIntersection) {
                directions.add('E');
                x++;
                prevIntersection = intersections[x][y];
            } else if (intersections[x - 1][y].getCounter() == 1 && intersections[x - 1][y] != prevIntersection) {
                directions.add('W');
                x--;
                prevIntersection = intersections[x][y];
            } else if (intersections[x][y - 1].getCounter() == 1 && intersections[x][y - 1] != prevIntersection) {
                directions.add('S');
                y--;
                prevIntersection = intersections[x][y];
            } else {
                exitFound = true;
            }
        }

        return directions;
    }

    private void driveMap(ArrayList<Character> directions) {
        facingDirection = 'N';
        for (char direction : directions) {
            if (facingDirection == 'N') {
                if (direction == 'N') {
                    //drive forward

                } else if (direction == 'E') {
                    //turn right
                    facingDirection = 'E';

                } else if (direction == 'W') {
                    //turn left
                    facingDirection = 'W';

                }
            } else if (facingDirection == 'E') {
                if (direction == 'E') {
                    //drive forward

                } else if (direction == 'S') {
                    //turn right
                    facingDirection = 'S';

                } else if (direction == 'N') {
                    //turn left
                    facingDirection = 'N';

                }
            } else if (facingDirection == 'S') {
                if (direction == 'S') {
                    //drive forward

                } else if (direction == 'W') {
                    //turn right
                    facingDirection = 'W';

                } else if (direction == 'E') {
                    //turn left
                    facingDirection = 'E';

                }
            } else if (facingDirection == 'W') {
                if (direction == 'W') {
                    //drive forward

                } else if (direction == 'N') {
                    //turn right
                    facingDirection = 'N';

                } else if (direction == 'S') {
                    //turn left
                    facingDirection = 'S';

                }
            }
        }
    }
}
