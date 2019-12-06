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
    }

    public void addIntersection(boolean front, boolean right, boolean left) {

        switch (facingDirection) {
            case 'N':
                intersections[x][y] = new Intersection(front, right,true, left);
                if (right){
                    facingDirection = 'E';
                    //turn right

                    //add x because going right once
                    x++;
                }else if (front){
                    //go foward

                    //y changes
                    y++;
                } else if (left){
                    facingDirection = 'W';
                    //turn left

                    //x changes going left once
                    x--;
                }else {
                    facingDirection = 'S';
                    //turn 180 degrees

                    //goes back once
                    y--;
                }
                break;
            case 'E':
                intersections[x][y] = new Intersection(front, right,true, left);
                if (right){
                    facingDirection = 'S';
                    //turn right

                    //add x because going right once
                    y--;
                }else if (front){
                    //go foward

                    //y changes
                    x++;
                } else if (left){
                    facingDirection = 'N';
                    //turn left

                    //x changes going left once
                    y++;
                }else {
                    facingDirection = 'W';
                    //turn 180 degrees

                    //goes back once
                    x--;
                }
                break;
            case 'S':
                intersections[x][y] = new Intersection(true, left,front, right);
                if (right){
                    facingDirection = 'W';
                    //turn right

                    //add x because going right once
                    x--;
                }else if (front){
                    //go foward

                    //y changes
                    y--;
                } else if (left){
                    facingDirection = 'E';
                    //turn left

                    //x changes going left once
                    x++;
                }else {
                    facingDirection = 'N';
                    //turn 180 degrees

                    //goes back once
                    y++;
                }
                break;
            case 'W':
                intersections[x][y] = new Intersection(right, true,left, front);
                if (right){
                    facingDirection = 'N';
                    //turn right

                    y++;
                }else if (front){
                    //go foward


                    x--;
                } else if (left){
                    facingDirection = 'S';
                    //turn left

                    y--;
                }else {
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
                    System.out.print(".");
                } else {
                    System.out.print(intersections[i][j]);
                }
            }
            System.out.println();
        }
    }

}
