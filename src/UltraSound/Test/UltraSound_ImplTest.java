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
	//5 m�teren bel�lvan-e test
	@Test
	public void testBell() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inTheCircle(400, 400);
		assertEquals(true, resoult);
	}
	@Test
	public void testKivl() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inTheCircle(600, 400);
		assertEquals(false, resoult);
	}
	
	//Szenzorokhoz tartoz� referencia pont meghat�roz�sa (l�t�sz�g�k balsz�ls� pontja)
/*	
	
	@Test
	public void testViewDegreeBackSensor2() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(100,100),45, 1);
		assertEquals(new Point(64,64), resoult);
	}
	
	@Test
	public void testViewDegreeBackSensor3() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(100,100),45, 2);
		assertEquals(new Point(100,50), resoult);
	}
	@Test
	public void testViewDegreeBackSensor4() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(100,100),45, 3);
		assertEquals(new Point(135,64), resoult);
	}
	
	@Test
	public void testViewDegreeFrontSensor() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(200,200),45, 4);
		assertEquals(new Point(250,200), resoult);
	}
	
	@Test
	public void testViewDegreeFrontSensor2() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(200,200),45, 5);
		assertEquals(new Point(235,235), resoult);
	}
	
	@Test
	public void testViewDegreeBackSensor() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		Point resoult = test.viewDegreeCalibration(new Point(100,100),45, 0);
		assertEquals(new Point(50,100), resoult);
	}
	
	//Szenzor l�t� mezz�j�be es� t�rgyakat sz�r� met�dus tesztjei
	@Test
	public void testInSector() {
		UltraSound_Impl test = new UltraSound_Impl();
		//Param�terek SzenzorHelye, l�t�sz�ge, terept�rgy kordin�t�ja, senzor azonos�t�ja (0 h�ts� balsz�ls�)
		boolean resoult = test.inSectorOfTheCircle(new Point(100,100), 45, new Point(50,99), 0);
		assertEquals(true, resoult);
	}
	
	@Test
	public void testInSector1() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inSectorOfTheCircle(new Point(100,100), 45, new Point(80,80), 0);
		assertEquals(true, resoult);
	}
	
	@Test
	public void testInSector2() {
		UltraSound_Impl test = new UltraSound_Impl();
		
		boolean resoult = test.inSectorOfTheCircle(new Point(100,100), 45, new Point(101,101), 0);
		assertEquals(false, resoult);
	}
	*/
	
}
