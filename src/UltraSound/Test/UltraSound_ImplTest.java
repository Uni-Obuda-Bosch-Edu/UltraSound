package UltraSound.Test;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import UltraSound.Core.UltraSoundImpl.UltraSound_Impl;

public class UltraSound_ImplTest {

	
	@Test
	public void testCorners(){
		UltraSound_Impl test = new UltraSound_Impl();
		Point2D carCenter = new Point2D.Double();
		
		carCenter.setLocation(50,120);
		List<Point2D> corners = new ArrayList<Point2D>();
		corners = test.getCarObjetCornersPositions(carCenter);
				
		assertEquals(0, (int)corners.get(0).getX());

		assertEquals(240, (int)corners.get(0).getY());
		assertEquals(100, (int)corners.get(1).getX());
		assertEquals(240, (int)corners.get(1).getY());
		assertEquals(100, (int)corners.get(2).getX());
		assertEquals(0, (int)corners.get(2).getY());
		assertEquals(0, (int)corners.get(3).getX());
		assertEquals(0, (int)corners.get(3).getY());
	}
	
	@Test
	public void testMidPointRotation(){
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.midPointRotation(new Point2D.Double(10,10), new Point2D.Double(0,0), 180);
		assertEquals(20,(int) resoult.getX()); 
		assertEquals(20,(int) resoult.getY());
	}
	
	//latomezon belul van-e targy 
	@Test
	public void testIn() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inTheCircle(400, 400);
		assertEquals(true, resoult);
	}
	@Test
	public void testOut() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inTheCircle(600, 400);
		assertEquals(false, resoult);
	}
	
	//Szenzorokhoz tartozo ref oldalpont meghatarozasara hasznalt metodus test
	@Test
	public void testViewDegreeBackSensor0() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(0,0),90, 0);
		assertEquals(-240,(int) resoult.getX()); 
		assertEquals(100,(int) resoult.getY());
	}
	
	@Test
	public void testViewDegreeBackSensor1() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(0,0),90, 1);	
		assertEquals(-100,(int) resoult.getX()); 
		assertEquals(-240,(int) resoult.getY());
	}
	
	@Test
	public void testViewDegreeBackSensor2() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,0),90, 2);
		assertEquals(-140,(int) Math.round(resoult.getX())); 
		assertEquals(-100,(int) Math.round(resoult.getY()));
	}
		
	@Test
	public void testViewDegreeBackSensor3() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,0),90, 3);
		assertEquals(200,(int) Math.round(resoult.getX())); 
		assertEquals(-240,(int) Math.round(resoult.getY()));
	}
	
	@Test
	public void testViewDegreeFrontSensor0() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,240),90, 4);
		assertEquals(340,(int) Math.round(resoult.getX())); 
		assertEquals(140,(int) Math.round(resoult.getY()));
	}
	
	@Test
	public void testViewDegreeFrontSensor1() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,240),90, 5);
		assertEquals(200,(int) Math.round(resoult.getX())); 
		assertEquals(480,(int) Math.round(resoult.getY()));
	}
		
			
	@Test
	public void testViewDegreeFrontSensor2() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,240),90, 6);
		assertEquals(340,(int) Math.round(resoult.getX())); 
		assertEquals(240,(int) Math.round(resoult.getY()));
	}
	
	@Test
	public void testViewDegreeFrontSensor3() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point2D resoult = test.viewDegreeCalibration(new Point2D.Double(100,240),90, 7);
		assertEquals(100,(int) Math.round(resoult.getX())); 
		assertEquals(480,(int) Math.round(resoult.getY()));
	}
	
	
	@Test
	public void testInSector() {
		UltraSound_Impl test = new UltraSound_Impl();
		//testViewDegreeBackSensor0 megallapitotta hatarat a latomezonek igy 100kordinata illetve kissebb Yonok meg bele esnek igy igaznak kell lennie
		boolean resoult = test.inSectorOfTheCircle(new Point2D.Double(0,0), 90, new Point2D.Double(-240,99), 0);
		assertEquals(true, resoult);
	}
	
	@Test
	public void testInSector1() {
		UltraSound_Impl test = new UltraSound_Impl();
		//testViewDegreeBackSensor0 megallapitasa miatt (-240,101)kordinata mar kivul esik
		boolean resoult = test.inSectorOfTheCircle(new Point2D.Double(0,0), 90, new Point2D.Double(-240,101), 0);
		assertEquals(false, resoult);
	}
	
	
	@Test
	public void testInSector2() {
		UltraSound_Impl test = new UltraSound_Impl();
		//testViewDegreeBackSensor1 megallapitasa miatt (-140,-100)kordinata a masik oldali latomezo hatara 
		boolean resoult = test.inSectorOfTheCircle(new Point2D.Double(0,0), 90, new Point2D.Double(-140,-100), 0);
		assertEquals(true, resoult);
	}
	
	
	@Test
	public void testInSector3() {
		UltraSound_Impl test = new UltraSound_Impl();
		//testViewDegreeBackSensor1 megallapitasa miatt (-140,-100)kordinata a masik oldali latomezo hatara igy -101 mar nem fogbeleesni 
		boolean resoult = test.inSectorOfTheCircle(new Point2D.Double(0,0), 90, new Point2D.Double(-140,-101), 0);
		assertEquals(true, resoult);
	}
	
	
}
