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
    private boolean sawLine;

    private Timer prinmtMapTimer = new Timer(5000);


    public Bot() {
        init();
        mapper.addIntersection(true,false,false);
        sawLine = false;


        /**
         * detection loop
         */
        while (true) {
            //updates
            for (Updatable updatable : this.updatables) {
                updatable.update();
            }

            if(routeFollower.hasHitIntersection()){
                BoeBot.wait(350);
                routeFollower.off();
                mapper.addIntersection(true,false,false);
                routeFollower.turnRight();
                routeFollower.on();
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
