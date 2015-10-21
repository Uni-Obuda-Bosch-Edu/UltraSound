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
/*
 * //unit teszt nem ment amig kinem kommenteltem
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
*/
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

	
	private Map<Integer, Integer> searchingObstackle(Point sensorCoord) {

		Map<Integer, Integer> findingsObstackle = new HashMap<Integer, Integer>();

		for (Map.Entry<Integer, Point> currObstackle : getObstackles().entrySet()) {
			int distanceX = 0;
			int distanceY = 0;
			distanceX = Math.abs(sensorCoord.x - currObstackle.getValue().x);
			distanceY = Math.abs(sensorCoord.y - currObstackle.getValue().y);
			if (inTheCircle(distanceX, distanceY)) {
				
	
				if (inSectorOfTheCircle(new Point(100,100), 90, new Point(120,90), 2)) {// te függvényed (random adatokkal hivtam meg)
				//	findingsObstackle.put(currObstackle.getKey(),calc(currObstackle.getValue())); // calc egy új függvény ami számolja a koordinátát
				}
			}
		}
		return findingsObstackle;
	}

	public boolean inTheCircle(int distanceX, int distanceY){
		return (distanceX<=500 && distanceY<=500);
	}

	public boolean inSectorOfTheCircle(Point sensorMidPoint, int sensorViewDegree, Point obstacklePoint, int sensorNumberID){
	 // 
		
		boolean inside =false;
		double sensorHalfViewDegree = Math.PI/180*sensorViewDegree/2;//radiánba átváltás
		
		Point sensorViewMiddelPoint = new Point(0,0);

		Point BorderPoint= viewDegreeCalibration(sensorMidPoint, sensorViewDegree, sensorNumberID);
		
		//sensor látómezõjének közepén található referencia pont meghatározása
		sensorViewMiddelPoint.x = (int) ((BorderPoint.getX()-sensorMidPoint.getX())*Math.cos(sensorHalfViewDegree)-((BorderPoint.getY()-sensorMidPoint.getY())*Math.sin(sensorHalfViewDegree))+sensorMidPoint.getX());
		sensorViewMiddelPoint.y = (int) ((BorderPoint.getX()-sensorMidPoint.getX())*Math.sin(sensorHalfViewDegree)+((BorderPoint.getY()-sensorMidPoint.getY())*Math.cos(sensorHalfViewDegree))+sensorMidPoint.getY());
		
		double distanceA = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY());
		double distanceB = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), obstacklePoint.getX(), obstacklePoint.getY());
		double distanceC = Point2D.distance(sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY(), obstacklePoint.getX(), obstacklePoint.getY());
		
		//látómezõ közepén elhelyezett refPonthoz 
		double angleByDistance = Math.acos((Math.pow(distanceA, 2)+Math.pow(distanceB, 2)-Math.pow(distanceC, 2))/(2*distanceA*distanceB));
		
		if(sensorHalfViewDegree >= angleByDistance) inside =true;
		return inside;
	}
	

	public Point viewDegreeCalibration(Point sensorMidPoint,int angleOnCar, int sensorNumberID)
	{
		Point calibrationPoint = new Point(0,0);
		double angleOnCarDouble =  Math.PI/180*angleOnCar*sensorNumberID;
		int offsetXpoint = sensorMidPoint.x-50; //nullát deffeljük majd
		
		calibrationPoint.x = (int) ((offsetXpoint-sensorMidPoint.getX())*Math.cos(angleOnCarDouble)-(0*Math.sin(angleOnCarDouble))+sensorMidPoint.getX());
		calibrationPoint.y = (int) ((offsetXpoint-sensorMidPoint.getX())*Math.sin(angleOnCarDouble)+(0*Math.cos(angleOnCarDouble))+sensorMidPoint.getY());
	
		return calibrationPoint;
	}
	

}
