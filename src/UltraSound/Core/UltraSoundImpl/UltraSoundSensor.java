package UltraSound.Core.UltraSoundImpl;

import java.awt.geom.Point2D;
enum Direction{
	UP, DOWN
	}

public class UltraSoundSensor {

	private Integer sensorID;
	private Point2D sensorMidPoint;
	private Integer sensorViewDegree;
	Direction sensorDirection;
	public Direction getSensorDirection() {
		return sensorDirection;
	}

	public Integer getSensorID() {
		return sensorID;
	}

	public Point2D getSensorMidPoint() {
		return sensorMidPoint;
	}
	public void setSensorMidPoint(Point2D sensorMidPoint) {
		this.sensorMidPoint = sensorMidPoint;
	}
	public Integer getSensorViewDegree() {
		return sensorViewDegree;
	}
	public void setSensorViewDegree(Integer sensorViewDegree) {
		this.sensorViewDegree = sensorViewDegree;
	}

	public UltraSoundSensor(Integer sensorID, Point2D sensorMidPoint, Integer sensorViewDegree,
			Direction sensorDirection) {
		this.sensorID = sensorID;
		this.sensorMidPoint = sensorMidPoint;
		this.sensorViewDegree = sensorViewDegree;
		this.sensorDirection = sensorDirection;
	}
	
}