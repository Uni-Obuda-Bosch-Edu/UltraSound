package FrameWork.busInterface;

public interface DriverInput_Out {
	
	public void setEngineToggleButtonState(boolean buttonState);
	public void setWheelRotationPercent(double steeringWheelAngle);
	public void setSteeringWheelMaxAngle(double steeringWheelMaxAngle);
	public void setBrakePedalPushPercent(double breakPedalAngle);
	public void setGasPedalPushPercent(double gasPedalPercent);
	public void setCurrentGear(int currentGear);
	public void setMaxGear(int maxGear);
	public void setShiftLeverPosition(int shiftLeverPosition);
	
}
