package MazeSolverBot.HardwareLayer.Sensor;

import TI.BoeBot;

public class LineFollower {

    private LineFollowerCallBack lineFollowerCallBack;

    private String sensorName;

    private double lineSensorOutput;

    private int pin;
    private int thresholdValue;

    public LineFollower(String sensorName, int pin, LineFollowerCallBack lineFollowerCallBack) {

        //To make a callback possible we need to give the interface through the constructor
        // so the class wil know there is a function called lineFollowerCallBack
        this.lineFollowerCallBack = lineFollowerCallBack;
        this.sensorName = sensorName;
        this.pin = pin;
        this.thresholdValue = 1250;
    }

    private boolean isOn;

    public boolean isOn() {
        return isOn;
    }

    /**
     this function will iterate to update the sensors
     */
    public void update() {
        this.lineSensorOutput = BoeBot.analogRead(this.pin);
        lineFollowerCallBack.onLineFollowerStatus(this);
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getDetectedColor(){
        if(this.lineSensorOutput < this.thresholdValue && this.lineSensorOutput > 0){
            return "white";
        } else if(this.lineSensorOutput >= thresholdValue && this.lineSensorOutput > 0){
            return "black";
        } else{
            return "NA check sensor";
        }
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }
}
