package MazeSolverBot.HardwareLayer.Sensor;

import MazeSolverBot.Utils.Updatable;
import TI.BoeBot;

public class Ultrasonic implements Updatable {
    private double distance;
    private UltrasonicCallBack ultrasonicCallBack;
    private String name;

    private int echoPin;
    private int triggerPin;

    public Ultrasonic(UltrasonicCallBack ultrasonicCallBack, String name, int triggerPin) {
        this.ultrasonicCallBack = ultrasonicCallBack;
        this.name = name;

        this.triggerPin = triggerPin;
        this.echoPin = triggerPin+1;
    }

    public void update() {

        //to make sure there is no collision in inputs we will reset the trigger pin by switching the pin false
        BoeBot.digitalWrite(this.triggerPin, false);
        BoeBot.uwait(2);

        //When the trigger pin is reset, the trigger wil be turned true
        // to trigger the sound pulses from the ultrasonic sensor
        BoeBot.digitalWrite(this.triggerPin, true);
        BoeBot.uwait(10);
        BoeBot.digitalWrite(this.triggerPin, false);

        //The time for the sound pulse to reflect into the sensor will be measured here
        // by timing the echo pin to turn true
        double time = BoeBot.pulseIn(this.echoPin, true, 3000);
        if (time < 0) {

            //After testing we found out the max distance was about 44.03 cm
            this.distance = 44.03;
        }

        //The time will be converted to the distance travelled by the sound pulse
        this.distance = ((time * 0.034) / 2.0);

        //This line will call a method in hit detection with a specific parameter, in this case we use this to return
        //a value to hit detection
        ultrasonicCallBack.ultrasonicSensorDistance(this);
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }
}
