package Interfaces;

import java.awt.geom.Point2D;

public interface IWorldObject {

	int getID();

	String getName();

	WorldObjectTypes.Type getType();

	WorldObjectTypes.Sign getSign();

	WorldObjectTypes.Misc getMisc();

	WorldObjectTypes.Lane getLane();

	Point2D getPosition(); // itt nagyon megzavarja a m�k�d�s�nk, hogy ezt
							// haszn�lja a world I2DPoint, ez�rt �talak�tottuk 

	double[][] getTransform();

	WorldObjectTypes.CarObject getCar();

}
