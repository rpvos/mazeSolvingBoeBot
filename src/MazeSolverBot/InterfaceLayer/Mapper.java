package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Intersection;


public class Mapper {
    private Intersection[][] intersections;
    private int x = 0;
    private int y = 0;

    public Mapper() {
        intersections = new Intersection[0][0];
    }

    public void addIntersection(){
        intersections[x][y] = new Intersection(true,true,true);
    }

}
