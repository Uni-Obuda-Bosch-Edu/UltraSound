package UltraSound_System;

import java.awt.Point;
enum Direction{
	UP, DOWN
	}

public class UltraSoundSensor {

	private Integer sensorID;
	private Point sensorMidPoint;
	private Integer sensorViewDegree;
	Direction sensorDirection;
	public Direction getSensorDirection() {
		return sensorDirection;
	}

	public Integer getSensorID() {
		return sensorID;
	}

	public Point getSensorMidPoint() {
		return sensorMidPoint;
	}
	public void setSensorMidPoint(Point sensorMidPoint) {
		this.sensorMidPoint = sensorMidPoint;
	}
	public Integer getSensorViewDegree() {
		return sensorViewDegree;
	}
	public void setSensorViewDegree(Integer sensorViewDegree) {
		this.sensorViewDegree = sensorViewDegree;
	}

	public UltraSoundSensor(Integer sensorID, Point sensorMidPoint, Integer sensorViewDegree,
			Direction sensorDirection) {
		this.sensorID = sensorID;
		this.sensorMidPoint = sensorMidPoint;
		this.sensorViewDegree = sensorViewDegree;
		this.sensorDirection = sensorDirection;
	}
	
}