package UltraSound_System;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import FrameWork.virtualDataBus.Container;
import java.awt.geom.Point2D;

public class UltraSound_Impl implements UltraSound_Out, UltraSound_IN {

	private int XUltraSoundDistance;
	private int YUltraSoundDistance;

	private Point LeftUpSensor;
	private Point RightUpSensor;
	private Point LeftDownSensor;
	private Point RightDownSensor;

	Container container;

	public UltraSound_Impl() {
		init();
	}

	private void init() {
		container = Container.getInstance();
		XUltraSoundDistance = 50; // 100*240
		YUltraSoundDistance = 120;

		LeftUpSensor = new Point();
		RightUpSensor = new Point();
		LeftDownSensor = new Point();
		RightDownSensor = new Point();
		sensorCalc();
	}

	private void sensorCalc() {
		int carPosX = GetCarPositionXY().x;
		int carPosY = GetCarPositionXY().y;

		LeftUpSensor.x = carPosX - XUltraSoundDistance; // x-z
		LeftUpSensor.y = carPosY + YUltraSoundDistance; // y+k

		RightUpSensor.x = carPosX + XUltraSoundDistance; // x+z
		RightUpSensor.y = carPosY + YUltraSoundDistance; // y+k

		LeftDownSensor.x = carPosX - XUltraSoundDistance; // x-z
		LeftDownSensor.y = carPosY - YUltraSoundDistance; // y-k

		RightDownSensor.x = carPosX - XUltraSoundDistance; // x+z
		RightDownSensor.y = carPosY - YUltraSoundDistance; // y-k
	}

	@Override
	public HashMap<Integer, Point> getObstackles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point GetCarPositionXY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int GetCarParameter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<Integer, Integer> getDistanceByObstackles() {
		// TODO Auto-generated method stub
		// melyik szenzor
		// akadály

		return null;
	}

	private HashMap<Integer, Integer> searchingObstackle(Point sensorCoord) {

		HashMap<Integer, Integer> findingsObstackle = new HashMap<Integer, Integer>();

		for (int i = 0; i < getObstackles().size(); i++) {
			int distanceX = 0;
			int distanceY = 0;
			distanceX = Math.abs(sensorCoord.x - getObstackles().get(i).x);
			distanceY = Math.abs(sensorCoord.y - getObstackles().get(i).y);
			if(inTheCircle(distanceX, distanceY)){
				if()
				findingsObstackle.put(getObstackles()., value);
			}
		}
		return findingsObstackle;

	}
	private boolean inTheCircle(int distanceX, int distanceY){
		return (distanceX<=5 && distanceY<=5);
	}

	private boolean inSectorOfTheCircle(Point sensorMidPoint, int sensorViewDegree, Point obstacklePoint){
	 // 
		
		boolean inside =false;
		int sensorHalfViewDegree = sensorViewDegree/2;
		Point sensorViewMiddelPoint = new Point(0,0);
		Point BorderPoint = viewDegreeCalibration(sensorMidPoint, 45);
		
		//sensor látómezõjének közepén található referencia pont meghatározása
		sensorViewMiddelPoint.x = (int) ((BorderPoint.getX()-sensorMidPoint.getX())*Math.cos(sensorHalfViewDegree)-(BorderPoint.getY()-sensorMidPoint.getY()*Math.sin(sensorHalfViewDegree))+BorderPoint.getX());
		sensorViewMiddelPoint.y = (int) ((BorderPoint.getX()-sensorMidPoint.getX())*Math.sin(sensorHalfViewDegree)+(BorderPoint.getY()-sensorMidPoint.getY()*Math.cos(sensorHalfViewDegree))+BorderPoint.getY());
		
		double distanceA = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY());
		double distanceB = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), obstacklePoint.getX(), obstacklePoint.getY());
		double distanceC = Point2D.distance(sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY(), obstacklePoint.getX(), obstacklePoint.getY());
		
		//látómezõ közepén elhelyezett refPonthoz 
		double angleByDistance = Math.acos((Math.pow(distanceA, 2)+Math.pow(distanceB, 2)-Math.pow(distanceC, 2))/(2*distanceA*distanceB));
		
		if(sensorHalfViewDegree >= angleByDistance) inside =true;
		return inside;
	}
	

	private	Point viewDegreeCalibration(Point sensorMidPoint,int angleOnCar)
	{
		sensorMidPoint.x += 500;
		
		return sensorMidPoint;
	}
	
}
