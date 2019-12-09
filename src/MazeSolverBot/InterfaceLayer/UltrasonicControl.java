package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Sensor.Ultrasonic;
import MazeSolverBot.HardwareLayer.Sensor.UltrasonicCallBack;
import MazeSolverBot.Utils.Updatable;

public class UltrasonicControl implements Updatable, UltrasonicCallBack {

    private final double triggerDistance = 5;

    private Ultrasonic ultrasonicRight;
    private Ultrasonic ultrasonicMiddle;
    private Ultrasonic ultrasonicLeft;

    private double ultrasonicRightDistance;
    private double ultrasonicMiddleDistance;
    private double ultrasonicLeftDistance;

    public UltrasonicControl() {
        ultrasonicRight = new Ultrasonic(this, "right", 0);
        ultrasonicMiddle = new Ultrasonic(this, "middle", 2);
        ultrasonicLeft = new Ultrasonic(this, "left",4);
    }

    @Override
    public void update() {
        ultrasonicRight.update();
        ultrasonicMiddle.update();
        ultrasonicLeft.update();
        //TODO read out distances

    }

    public boolean getUltrasonicRightDistance() {
        return ultrasonicRightDistance > 15;
    }

    public boolean getUltrasonicMiddleDistance() {
        System.out.println(this.ultrasonicMiddleDistance);
        return ultrasonicMiddleDistance > 15;
    }

    public boolean getUltrasonicLeftDistance() {
        return ultrasonicLeftDistance > 15;
    }

    @Override
    public void ultrasonicSensorDistance(Ultrasonic ultrasonic) {
            if (ultrasonic.getName().equals("right")) {
                ultrasonicRightDistance = ultrasonic.getDistance();
            } else if (ultrasonic.getName().equals("middle")) {
                ultrasonicMiddleDistance = ultrasonic.getDistance();
            } else if (ultrasonic.getName().equals("left")) {
                ultrasonicLeftDistance = ultrasonic.getDistance();
            }

    }
}
