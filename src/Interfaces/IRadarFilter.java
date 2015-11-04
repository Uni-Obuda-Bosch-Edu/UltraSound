package Interfaces;

import java.util.List;

public interface IRadarFilter {
	
	List<IWorldObject> getRelevantObjectsForRadar(I2DPoint a, I2DPoint b, I2DPoint c);
}
