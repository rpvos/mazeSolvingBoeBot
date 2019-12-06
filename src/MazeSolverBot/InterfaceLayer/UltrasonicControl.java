package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Sensor.Ultrasonic;
import MazeSolverBot.HardwareLayer.Sensor.UltrasonicCallBack;
import MazeSolverBot.Utils.Updatable;

public class UltrasonicControl implements Updatable, UltrasonicCallBack {

    private final double triggerDistance = 5;
    
    private Ultrasonic ultrasonicRight;
    private Ultrasonic ultrasonicMiddle;
    private Ultrasonic ultrasonicLeft;

    public UltrasonicControl() {
        ultrasonicRight = new Ultrasonic(this, "right");
        ultrasonicMiddle = new Ultrasonic(this, "middle");
        ultrasonicLeft = new Ultrasonic(this, "left");
    }

    @Override
    public void update() {
        ultrasonicRight.update();
        ultrasonicMiddle.update();
        ultrasonicLeft.update();
    }

    @Override
    public void ultrasonicSensorDistance(Ultrasonic ultrasonic) {
        if (ultrasonic.getDistance() > triggerDistance) {

            if (ultrasonic.getName().equals("right")) {
                //TODO
            } else if (ultrasonic.getName().equals("middle")) {
                //TODO
            } else if (ultrasonic.getName().equals("left")) {
                //TODO
            }
        }
    }
}
