package MazeSolverBot.InterfaceLayer;

import MazeSolverBot.HardwareLayer.Sensor.LineFollower;
import MazeSolverBot.HardwareLayer.Sensor.LineFollowerCallBack;
import MazeSolverBot.Utils.IntervalTimer;
import MazeSolverBot.Utils.Updatable;
import TI.Timer;

import java.util.ArrayList;
import java.util.List;

public class RouteFollower implements LineFollowerCallBack, Updatable {

    private Timer timer1;
    private Timer t4;

    private int adjustment;

    private IntervalTimer intervalTimer;

    private MotorControl motorControl;

    private boolean fellOffRight;
    private boolean fellOffLeft;

    private String leftSensorStatus;
    private String rightSensorStatus;
    private String middleSensorStatus;

    private float counter2;
    private float counter4;

    private boolean lineFollowerState;

    private List<LineFollower> lineFollowerList;


    public RouteFollower(MotorControl motorControl) {

        this.t4 = new Timer(30);
        this.timer1 = new Timer(10);


        this.lineFollowerState = true;
        //Here the motor control wil be implemented
        this.motorControl = motorControl;

        this.intervalTimer = new IntervalTimer();

        this.adjustment = 300;

        //To use some of the methods to drive forward easy we associate the drive class
        this.counter2 = 0.1f;
        this.counter4 = 0.1f;

        lineFollowerList = new ArrayList<>();

        //Here the Line follower is created, this class stands for all three the sensors,
        //we chose this option to make the callbacks and updates more easy and line efficient
        lineFollowerList.add(new LineFollower("leftSensor",2, this));
        lineFollowerList.add(new LineFollower("middleSensor", 1,this));
        lineFollowerList.add(new LineFollower("rightSensor", 0,this));

    }

    /**
     * This method is constantly updated
     */
    public void update() {

        if(timer1.timeout()) {
            for (LineFollower lineFollower : lineFollowerList) {
                lineFollower.update();

            }
            timer1.mark();
        }

        //First there is a check if the function is turned on or not, the function can be turned off in the override
        if (this.lineFollowerState) {
            motorControl.setSlowAccelerate(false);


            //To ensure the Bot does not wiggle too much when following the line
            // we added an timer to round off the edges a bit
            if (t4.timeout()) {


                if (this.middleSensorStatus.equals("white")) {

                        //If the right sensor detects a line it steers left
                        if (this.rightSensorStatus.equals("black") || this.fellOffRight) {
                                this.motorControl.setMotorsTarget(0.2f, 0.5f);
                                this.fellOffRight = true;
                                //The longer the middle sensor does not detect a line the more it will steer
                                //again to ensure the Bot does not wiggle too much
                        }
                        //If the left sensor detects a line it steers right
                        if (this.leftSensorStatus.equals("black") || this.fellOffLeft) {
                                this.motorControl.setMotorsTarget(0.2f, -0.5f);
                                this.fellOffLeft = true;
                                //The longer the middle sensor does not detect a line the more it will steer
                                //again to ensure the Bot does not wiggle too much
                        }

                }else if(this.middleSensorStatus.equals("black")) {

                    this.fellOffLeft = false;
                    this.fellOffRight = false;

                    this.intervalTimer.restart();

                    this.motorControl.setMotorsTarget(0.2f,0);

                    //When the middle line follower detects the line again the steering wil be set back to default
                    this.counter2 = 0.2f;
                    this.counter4 = 0.2f;

                }
                if(this.rightSensorStatus.equals("black") && this.middleSensorStatus.equals("black") && this.leftSensorStatus.equals("black"))


                t4.mark();
            }
        }else {
            motorControl.setSlowAccelerate(true);
        }
    }

    public boolean hasHitIntersection(){
        return (this.rightSensorStatus.equals("black") && this.middleSensorStatus.equals("black") && this.leftSensorStatus.equals("black"));
    }

    /**
     * If the update function is called the linefollower class wil callback to this method to update the
     * attributes
     */
    public void onLineFollowerStatus(LineFollower lineFollower){
        //System.out.println(lineFollower.getDetectedColor() + ": " + lineFollower.getSensorName());
        if(lineFollower.getSensorName().equals("leftSensor")){
            this.leftSensorStatus = lineFollower.getDetectedColor();
        } else if(lineFollower.getSensorName().equals("middleSensor")){
            this.middleSensorStatus = lineFollower.getDetectedColor();
        } else if(lineFollower.getSensorName().equals("rightSensor")){
            this.rightSensorStatus = lineFollower.getDetectedColor();
        }

    }



    public boolean isOn() {
        return this.lineFollowerState;
    }

    /**
     * If this function is called the attribute will trigger on
     */

    public void on() {
        this.lineFollowerState = true;
    }


    public void off() {
        this.motorControl.setMotorsTarget(0,0);
        this.lineFollowerState = false;
    }

    public void turnRight(){
        Timer timer = new Timer(800);
        while (true){
            this.motorControl.rotate("right");
            for (LineFollower lineFollower : lineFollowerList) {
                lineFollower.update();
            }
            if (timer.timeout()&&this.middleSensorStatus.equals("black")){
                motorControl.setMotorsTarget(0,0);
                break;
            }
        }

    }

    public void turnLeft(){
        Timer timer = new Timer(500);
        while (true){
            this.motorControl.rotate("left");
            for (LineFollower lineFollower : lineFollowerList) {
                lineFollower.update();
            }
            if (timer.timeout()&&this.middleSensorStatus.equals("black")){
                motorControl.setMotorsTarget(0,0);
                break;
            }
        }
    }

    public void turnBack(){
        Timer timer = new Timer(1500);
        while (true){
            this.motorControl.rotate("right");
            for (LineFollower lineFollower : lineFollowerList) {
                lineFollower.update();
            }
            if (timer.timeout()&&this.middleSensorStatus.equals("black")){
                motorControl.setMotorsTarget(0,0);
                break;
            }
        }
    }

    public void goForward(){
        Timer timer = new Timer(500);
        while (true){
            this.motorControl.rotate("forward");
            for (LineFollower lineFollower : lineFollowerList) {
                lineFollower.update();
            }
            if (timer.timeout()&&this.middleSensorStatus.equals("black")){
                motorControl.setMotorsTarget(0,0);
                break;
            }
        }

    }
}
