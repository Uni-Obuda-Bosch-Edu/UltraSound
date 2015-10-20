package FrameWork.busInterface;

public interface Engine_Out {
	public void setEngineTorque(double engineTorque);
	public void setEngineRevolution(int engineRevolution);
	public void setWaterTemperature(double waterTemperature);
	public void setOilTemperature(double oilTemperature);
	public void setOilPressure(double oilPressure);
	public void setServiceCode(int serviceCode);
}
