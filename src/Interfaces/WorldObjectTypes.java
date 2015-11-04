package Interfaces;

public class WorldObjectTypes {

	public static enum Type{Sign,Lane, Misc}

	public static enum Sign{None,
	Direction_Forward, Direction_Left, Direction_Right, Direction_ForwardLeft, Direction_ForwardRight, Direction_RoundAbout,
	Parking_Left, Parking_Right, 
	Priority_Yield, Priority_Stop, Priority_Highway,
	Speed_10, Speed_20, Speed_40, Speed_50, Speed_70, Speed_90, Speed_100}

	public static enum Lane{None,
	Advanced_CrossRoads, Advanced_Rotary, Advanced_JunctionLeft, Advanced_JunctionRight,
	Simple_45Left, Simple_45Right, Simple_65Left, Simple_65Right, Simple_90Left, Simple_90Right, Simple_Straight}

	public static enum Misc{ None,
	CrossWalk, Parking0, Parking90, Parking_Bollard, Man, Tree}
	public static enum CarObject{ Car, notCar }
}
