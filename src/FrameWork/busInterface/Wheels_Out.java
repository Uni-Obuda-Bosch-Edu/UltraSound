package FrameWork.busInterface;

public interface Wheels_Out {
	public void setWheelTorqueInNewton(double newTorque);
	public void setMaximumWheelTorqueInNewton(double newTorque);
	
	public void setMaximumBrakeTorqueInNewton(double newTorque);
	public void setFrictionalCoefficientOfBrakes(double coefficient);
	 
	public void setDiameterOfDriveAxesInMeters(double diameter);
	public void setLengthOfAxesInMeters(double length);
	public void setDistanceBetweenAxesInMeters(double distance);
	
	public void setDiameterOfWheelsInMeters(double diameter);
	public void setWidthOfWheelsInMeters(double width);
	 
	public void setDriveWheelStateZeroBasedDegree(double degree);
	public void setMaximumDriveWheelStateZeroBasedDegree(double degree);
	public void setMaximumWheelsTurnDegree(double degree);
     
	public void setTotalMassInKg(double newMass);
	public void setInnerFrictionalCoefficientInNewton(double coefficient);
}
