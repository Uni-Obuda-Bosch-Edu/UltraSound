package Interfaces;

import java.awt.geom.Point2D;

public interface IWorldObject {

	int getID();

	String getName();

	WorldObjectTypes.Type getType();

	WorldObjectTypes.Sign getSign();

	WorldObjectTypes.Misc getMisc();

	WorldObjectTypes.Lane getLane();

	Point2D getPosition(); // itt nagyon megzavarja a mûködésünk, hogy ezt
							// használja a world I2DPoint, ezért átalakítottuk 

	double[][] getTransform();

	WorldObjectTypes.CarObject getCar();

}
