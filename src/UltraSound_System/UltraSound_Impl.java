package UltraSound_System;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FrameWork.virtualDataBus.Container;

public class UltraSound_Impl implements UltraSound_Out, UltraSound_IN {

	// private int XUltraSoundDistance = 50; // 100*240
	private int YUltraSoundDistance = 120;

	private List<UltraSoundSensor> sensors;
	Container container;

	public UltraSound_Impl() {
		sensors = new ArrayList<UltraSoundSensor>();
		sensorCalc();
	}

	private void sensorCalc() {
		int idGen = 0;
		Direction directUP = Direction.UP;
		Direction directDOWN = Direction.DOWN;
		int sensorViewDegree = 45;

		Point pointUpSensor = new Point(GetCarPositionXY().x, GetCarPositionXY().y + YUltraSoundDistance);
		Point pointDownSensor = new Point(GetCarPositionXY().x, GetCarPositionXY().y - YUltraSoundDistance);
		// Down
		sensors.add(new UltraSoundSensor(idGen++, pointDownSensor, sensorViewDegree, directDOWN));// 0
		sensors.add(new UltraSoundSensor(idGen++, pointDownSensor, sensorViewDegree, directDOWN));// 1
		sensors.add(new UltraSoundSensor(idGen++, pointDownSensor, sensorViewDegree, directDOWN));// 2
		sensors.add(new UltraSoundSensor(idGen++, pointDownSensor, sensorViewDegree, directDOWN));//

		// Up
		sensors.add(new UltraSoundSensor(idGen++, pointUpSensor, sensorViewDegree, directUP));// 5
		sensors.add(new UltraSoundSensor(idGen++, pointUpSensor, sensorViewDegree, directUP));// 6
		sensors.add(new UltraSoundSensor(idGen++, pointUpSensor, sensorViewDegree, directUP));// 7
		sensors.add(new UltraSoundSensor(idGen++, pointUpSensor, sensorViewDegree, directUP));// 8
	}

	@Override
	public HashMap<Integer, Point> getObstackles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point GetCarPositionXY() {
		// TODO Auto-generated method stub
		return new Point(100, 100);
	}

	@Override
	public int GetCarParameter() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Map<Integer, Double> getDistanceByObstackles() {
		Map<Integer, Double> allFindingsObstackles = new HashMap<Integer, Double>();
		for (UltraSoundSensor currUSS : sensors) {
			allFindingsObstackles.putAll(searchingObstackle(currUSS));
		}
		return allFindingsObstackles;
	}

	private Map<Integer, Double> searchingObstackle(UltraSoundSensor ultrasoundsensor) {

		Map<Integer, Double> findingsObstackle = new HashMap<Integer, Double>();

		for (Map.Entry<Integer, Point> currObstackle : getObstackles().entrySet()) {
			int distanceX = 0;
			int distanceY = 0;
			distanceX = Math.abs(ultrasoundsensor.getSensorMidPoint().x - currObstackle.getValue().x);
			distanceY = Math.abs(ultrasoundsensor.getSensorMidPoint().y - currObstackle.getValue().y);
			if (inTheCircle(distanceX, distanceY)) {
				if (inSectorOfTheCircle(new Point(100, 100), 90, new Point(120, 90), 2)) {
					findingsObstackle.put(currObstackle.getKey(),
							calcDistance2D(currObstackle.getValue(), GetCarPositionXY()));
				}
			}
		}

		return findingsObstackle;
	}
	// a te függvényed random adatokkal if (inSectorOfTheCircle(new Point(100,
	// 100), 90, new Point(120, 90), 2))

	public double calcDistance2D(Point currObstackle, Point carPosition) {
		return Point2D.distance(currObstackle.getX(), currObstackle.getY(), carPosition.getX(), carPosition.getY());
	}

	public boolean inTheCircle(int distanceX, int distanceY) {
		return (distanceX <= 500 && distanceY <= 500);
	}

	public boolean inSectorOfTheCircle(Point sensorMidPoint, int sensorViewDegree, Point obstacklePoint,
			int sensorNumberID) {
		boolean inside = false;
		double sensorHalfViewDegree = Math.PI / 180 * sensorViewDegree / 2;// radiánba
																			// átváltás

		Point sensorViewMiddelPoint = new Point(0, 0);

		Point BorderPoint = viewDegreeCalibration(sensorMidPoint, sensorViewDegree, sensorNumberID);

		// sensor látómezõjének közepén található referencia pont meghatározása
		sensorViewMiddelPoint.x = (int) ((BorderPoint.getX() - sensorMidPoint.getX()) * Math.cos(sensorHalfViewDegree)
				- ((BorderPoint.getY() - sensorMidPoint.getY()) * Math.sin(sensorHalfViewDegree))
				+ sensorMidPoint.getX());
		sensorViewMiddelPoint.y = (int) ((BorderPoint.getX() - sensorMidPoint.getX()) * Math.sin(sensorHalfViewDegree)
				+ ((BorderPoint.getY() - sensorMidPoint.getY()) * Math.cos(sensorHalfViewDegree))
				+ sensorMidPoint.getY());

		double distanceA = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), sensorViewMiddelPoint.getX(),
				sensorViewMiddelPoint.getY());
		double distanceB = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), obstacklePoint.getX(),
				obstacklePoint.getY());
		double distanceC = Point2D.distance(sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY(),
				obstacklePoint.getX(), obstacklePoint.getY());

		// látómezõ közepén elhelyezett refPonthoz
		double angleByDistance = Math.acos((Math.pow(distanceA, 2) + Math.pow(distanceB, 2) - Math.pow(distanceC, 2))
				/ (2 * distanceA * distanceB));

		if (sensorHalfViewDegree >= angleByDistance)
			inside = true;
		return inside;
	}

	public Point viewDegreeCalibration(Point sensorMidPoint, int angleOnCar, int sensorNumberID) {
		Point calibrationPoint = new Point(0, 0);
		double angleOnCarDouble = Math.PI / 180 * angleOnCar * sensorNumberID;
		int offsetXpoint = sensorMidPoint.x - 50; // nullát deffeljük majd

		calibrationPoint.x = (int) ((offsetXpoint - sensorMidPoint.getX()) * Math.cos(angleOnCarDouble)
				- (0 * Math.sin(angleOnCarDouble)) + sensorMidPoint.getX());
		calibrationPoint.y = (int) ((offsetXpoint - sensorMidPoint.getX()) * Math.sin(angleOnCarDouble)
				+ (0 * Math.cos(angleOnCarDouble)) + sensorMidPoint.getY());

		return calibrationPoint;
	}

}
