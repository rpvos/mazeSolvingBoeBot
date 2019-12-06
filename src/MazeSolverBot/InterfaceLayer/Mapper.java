package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Intersection;


public class Mapper {
    private Intersection[][] intersections;
    private int x = 20;
    private int y = 20;

    public Mapper() {
        intersections = new Intersection[40][40];
    }

    public void addIntersection(int transmutationX, int transmutationY, boolean north, boolean east, boolean south, boolean west){
        x += transmutationX;
        y += transmutationY;
        intersections[x][y] = new Intersection(north,east,south,west);

        if (east = true){
            //turn right
        }
    }

    public void printMap(){
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (intersections[i][j]==null){
                    System.out.print(".");
                }else {
                    System.out.print(intersections[i][j]);
                }
        }
            System.out.println();
        }
    }

}
