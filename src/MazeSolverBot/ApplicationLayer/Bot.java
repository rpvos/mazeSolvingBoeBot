package MazeSolverBot.ApplicationLayer;

import MazeSolverBot.InterfaceLayer.Mapper;
import MazeSolverBot.InterfaceLayer.MotorControl;
import MazeSolverBot.InterfaceLayer.RouteFollower;
import MazeSolverBot.InterfaceLayer.UltrasonicControl;
import MazeSolverBot.Utils.Updatable;
import TI.BoeBot;
import java.util.ArrayList;

public class Bot {
    private ArrayList<Updatable> updatables;
    private MotorControl motorControl;
    private UltrasonicControl ultrasonicControl;
    private RouteFollower routeFollower;
    private Mapper mapper;


    public Bot() {
        init();

        mapper.addIntersection(1,0,true,false,false,false);
        mapper.printMap();
        
        /**
         * detection loop
         */
        while (true) {
            //updates
            for (Updatable updatable : this.updatables) {
                updatable.update();
            }

            if(routeFollower.hasHitIntersection()){
                routeFollower.off();

            }

            //wait so it is less CPU heavy
            BoeBot.wait(2);

        }
    }

    /**
     * initialising every object we need
     */
    public void init() {
        this.motorControl = new MotorControl();
        this.ultrasonicControl = new UltrasonicControl();
        this.mapper = new Mapper();

        this.updatables = new ArrayList<>();
        this.routeFollower = new RouteFollower(motorControl);

        this.updatables.add(motorControl);
        this.updatables.add(ultrasonicControl);
        this.updatables.add(routeFollower);
    }

}
