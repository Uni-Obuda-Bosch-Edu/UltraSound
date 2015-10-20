package UltraSound_System;

import java.awt.Point;
import java.util.Map;

import FrameWork.virtualDataBus.Container;

public class UltraSound_Impl implements UltraSound_Out, UltraSound_IN{

	Container container;
	
	public UltraSound_Impl(){
		init();
	}
	private void init(){
		container = Container.getInstance();
	}
	 
	@Override
	public Map<Integer, Point> Obstackles() {
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
	public Map<Integer, Integer> getDistanceByObstackles() {
		// TODO Auto-generated method stub
		return null;
	}

}
