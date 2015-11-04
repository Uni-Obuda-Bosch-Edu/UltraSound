package UltraSound.Core.UltraSoundImpl;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaces.I2DPoint;
import Interfaces.IWorldObject;
import Interfaces.WorldObjectTypes;
import UltraSound.Core.Framework.virtualDataBus.Container;
import UltraSound.Core.UltraSound.UltraSound_IN;
import UltraSound.Core.UltraSound.UltraSound_Out;

public class UltraSound_Impl implements UltraSound_Out, UltraSound_IN {

	// private final int XUltraSoundDistance = 50; // 100*240
	private final int maxRange = 500;


	private List<UltraSoundSensor> sensors;
	Container container;

	public UltraSound_Impl() {
		sensors = new ArrayList<UltraSoundSensor>();
		sensorCalc();
	}

	private class offset {
		static final int x = 50;
		static final int y = 120;
	};

	public List<Point2D> getCarObjetCornersPositions(Point2D carCenter) {
		List<Point2D> corners = new ArrayList<Point2D>();
		Point2D corner = new Point2D.Double();

		corner = new Point2D.Double(carCenter.getX() - offset.x, carCenter.getY() + offset.y);
		corners.add(0, corner);
		corner = new Point2D.Double(carCenter.getX() + offset.x, carCenter.getY() + offset.y);
		corners.add(1, corner);
		corner = new Point2D.Double(carCenter.getX() + offset.x, carCenter.getY() - offset.y);
		corners.add(2, corner);
		corner = new Point2D.Double(carCenter.getX() - offset.x, carCenter.getY() - offset.y);
		corners.add(3, corner);
		return corners;
	}

	private void sensorCalc() {
		int idGen = 0;
		Direction directUP = Direction.UP;
		Direction directDOWN = Direction.DOWN;
		int sensorViewDegree = 90;

		
		// Down
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(3), sensorViewDegree, directDOWN));// 0
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(3), sensorViewDegree, directDOWN));// 1
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(2), sensorViewDegree, directDOWN));// 2
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(2), sensorViewDegree, directDOWN));// 3

		// Up
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(1), sensorViewDegree, directUP));// 4
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(1), sensorViewDegree, directUP));// 5
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(0), sensorViewDegree, directUP));// 6
		sensors.add(new UltraSoundSensor(idGen++, getCarObjetCornersPositions(GetCarPositionXY()).get(0), sensorViewDegree, directUP));// 7
	}

	@Override
	public List<IWorldObject> getObstackles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point GetCarPositionXY() {
		// TODO Auto-generated method stub
		return new Point(50,120);
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

		for (IWorldObject currObstackle : getObstackles()) {
			double distanceX = 0;
			double distanceY = 0;
			distanceX = Math.abs(ultrasoundsensor.getSensorMidPoint().getX()- currObstackle.getPosition().getX());
			distanceY = Math.abs(ultrasoundsensor.getSensorMidPoint().getY() - currObstackle.getPosition().getY());

			if (currObstackle.getCar() == WorldObjectTypes.CarObject.Car) {
				int fourCornernear = 0;
				for (Point2D currCorner : getCarObjetCornersPositions(currObstackle.getPosition())) {
					/*
					 * if (inTheCircle(currCorner.getX(), currCorner.getY())) {
					 * if
					 * (inSectorOfTheCircle(ultrasoundsensor.getSensorMidPoint()
					 * , ultrasoundsensor.getSensorViewDegree(), currCorner,
					 * ultrasoundsensor.getSensorID())) {
					 * findingsObstackle.put(currObstackle.getID(),
					 * calcDistance2D(currObstackle.getPosition(),
					 * GetCarPositionXY())); 					 *
					 * } }					 *
					 */
				}

			} else {
				if (inTheCircle(distanceX, distanceY)) {
					if (inSectorOfTheCircle(ultrasoundsensor.getSensorMidPoint(),
							ultrasoundsensor.getSensorViewDegree(), currObstackle.getPosition(),
							ultrasoundsensor.getSensorID())) {
						findingsObstackle.put(currObstackle.getID(),
								calcDistance2D(currObstackle.getPosition(), GetCarPositionXY()));
					}
				}
			}
		}
		return findingsObstackle;
	}

	public double calcDistance2D(Point2D point2d, Point2D carPosition) {
		return Point2D.distance(point2d.getX(), point2d.getY(), carPosition.getX(), carPosition.getY());
	}

	public boolean inTheCircle(double distanceX, double distanceY) {
		return (distanceX <= maxRange && distanceY <= maxRange);
	}

	public boolean inSectorOfTheCircle(Point2D sensorMidPoint, int sensorViewDegree, Point2D obstacklePoint,
			int sensorNumberID) {

		Point2D sensorViewMiddelPoint = new Point2D.Double(0, 0);
		Point2D BorderPoint = new Point2D.Double(0, 0);
		boolean inside = false;
		int sensorHalfViewDegree = sensorViewDegree / 2;

		BorderPoint = viewDegreeCalibration(sensorMidPoint, sensorViewDegree, sensorNumberID);

		// sensor latomezojenek kozepenek meghatarozasa
		sensorViewMiddelPoint = midPointRotation(sensorMidPoint, BorderPoint, 45);
		

		double distanceA = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), sensorViewMiddelPoint.getX(),
				sensorViewMiddelPoint.getY());
		double distanceB = Point2D.distance(sensorMidPoint.getX(), sensorMidPoint.getY(), obstacklePoint.getX(),
				obstacklePoint.getY());
		double distanceC = Point2D.distance(sensorViewMiddelPoint.getX(), sensorViewMiddelPoint.getY(), obstacklePoint.getX(),
				obstacklePoint.getY());

		// latomezo kozepenek egyik pontja es targy altal bezart szog nagysága
		double angleByDistance = Math.acos((Math.pow(distanceA, 2) + Math.pow(distanceB, 2) - Math.pow(distanceC, 2))
				/ (2 * distanceA * distanceB));
		angleByDistance=Math.toDegrees(angleByDistance);
		if (sensorHalfViewDegree >= angleByDistance)
			inside = true;
		return inside;
	}

	public Point2D viewDegreeCalibration(Point2D sensorMidPoint, int sensorViewDegree, int sensorNumberID) {
		Point2D calibrationPoint = new Point2D.Double(0, 0);

		int oppositeSensorID = ((sensorNumberID + 4) % sensors.size());

		// atlosan elhelyezkedo szenzorok kozeppontjat 180fokkal elforgatja
		// adott sensor kozeppontja korul
		calibrationPoint = midPointRotation(sensorMidPoint, sensors.get(oppositeSensorID).getSensorMidPoint(), 180);

		// ha 2-vel nem oszthato id-je a sensornak akkor ez a kivánt
		// referenciapont különben elforgatjuk
		if (sensorNumberID % 2 == 1)
			return calibrationPoint;
		else {
			calibrationPoint = midPointRotation(sensorMidPoint, calibrationPoint, sensorViewDegree*(-1));
			return calibrationPoint;
		}

	}

	//
	public Point2D midPointRotation(Point2D midpoint, Point2D rotpoint, int rotdegree) {

		Point2D rotatedPoint = new Point2D.Double(0, 0);
		double rotdegreeInRad = Math.PI / 180 * rotdegree;
		double x = 0;
		double y = 0;

		x = ((rotpoint.getX() - midpoint.getX()) * Math.cos(rotdegreeInRad)
				- ((rotpoint.getY() - midpoint.getY()) * Math.sin(rotdegreeInRad)) + midpoint.getX());

		y = ((rotpoint.getX() - midpoint.getX()) * Math.sin(rotdegreeInRad)
				+ ((rotpoint.getY() - midpoint.getY()) * Math.cos(rotdegreeInRad)) + midpoint.getY());
		;

		rotatedPoint.setLocation(x, y);

		return rotatedPoint;
	}
}
