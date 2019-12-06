package MazeSolverBot.ApplicationLayer;

import MazeSolverBot.InterfaceLayer.MotorControl;
import MazeSolverBot.InterfaceLayer.UltrasonicControl;
import MazeSolverBot.Utils.Updatable;
import TI.BoeBot;
import java.util.ArrayList;

public class Bot {
    private ArrayList<Updatable> updatables;
    private MotorControl motorControl;
    private UltrasonicControl ultrasonicControl;


    public Bot() {
        init();

        /**
         * detection loop
         */
        while (true) {
            //updates
            for (Updatable updatable : this.updatables) {
                updatable.update();
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
        this.updatables = new ArrayList<>();


        this.updatables.add(motorControl);
        this.updatables.add(ultrasonicControl);
    }

}
