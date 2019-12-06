package MazeSolverBot.HardwareLayer;

public class Intersection {
    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;


    public Intersection(boolean north, boolean east,boolean south, boolean west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    @Override
    public String toString() {
        return "O";
    }
}
