package Interfaces;

import java.util.List;

public interface IVideoCameraFilter {
	
	List<IWorldObject> getRelevantObjectsForVideoCamera(I2DPoint a, I2DPoint b, I2DPoint c);
}
