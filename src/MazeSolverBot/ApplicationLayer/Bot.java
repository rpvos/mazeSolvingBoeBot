package MazeSolverBot.ApplicationLayer;

import MazeSolverBot.InterfaceLayer.Mapper;
import MazeSolverBot.InterfaceLayer.MotorControl;
import MazeSolverBot.InterfaceLayer.RouteFollower;
import MazeSolverBot.InterfaceLayer.UltrasonicControl;
import MazeSolverBot.Utils.Updatable;
import TI.BoeBot;
import TI.Timer;

import java.util.ArrayList;

public class Bot {
    private ArrayList<Updatable> updatables;
    private MotorControl motorControl;
    private UltrasonicControl ultrasonicControl;
    private RouteFollower routeFollower;
    private Mapper mapper;

    private Timer prinmtMapTimer = new Timer(2000);


    public Bot() {
        init();

        mapper.addIntersection(0,0,true,false,false,false);

        /**
         * detection loop
         */
        while (true) {
            //updates
            for (Updatable updatable : this.updatables) {
                updatable.update();
            }

            if(routeFollower.hasHitIntersection()){
                mapper.addIntersection(1,0,true,true,true,true);
                routeFollower.off();
            }

            if (prinmtMapTimer.timeout()){
                mapper.printMap();
                prinmtMapTimer.mark();
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
