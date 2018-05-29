package dungeonMaster.tests;

import static org.junit.Assert.assertTrue;

import dungeonMaster.components.EditMapImplem;
import dungeonMaster.components.MapImplem;
import dungeonMaster.contracts.EditMapContract;
import dungeonMaster.contracts.MapContract;
import dungeonMaster.enumeration.Cell;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class MapTest extends TestCase{
	public MapTest() {
		
	}
	protected int largeur, hauteur;
	protected void setUp(){
	      largeur = 70;
	      hauteur = 50;
	   }
	 
	
	public void testMapImplem() {
		MapContract map = new MapContract(new MapImplem());
		
		map.init(largeur, hauteur);
		
		assertTrue(map.cellNature(10, 10)==Cell.EMP);
	}
	
	
	public void testOpenDoor() {
		EditMapContract map = new EditMapContract(new EditMapImplem());
		map.init(largeur, hauteur);
		int x = largeur /2 ;
		int y = hauteur /2;
		map.setNature(x-1, y, Cell.WLL);
		map.setNature(x+1, y, Cell.WLL);
		map.setNature(x, y, Cell.DNC);
		assertTrue(map.openDoor(x, y));
	}
	public void testCloseDoor() {
		EditMapContract map = new EditMapContract(new EditMapImplem());
		map.init(largeur, hauteur);
		int x = largeur /2 ;
		int y = hauteur /2;
		map.setNature(x-1, y, Cell.WLL);
		map.setNature(x+1, y, Cell.WLL);
		map.setNature(x, y, Cell.DNC);
		map.openDoor(x, y);
		assertTrue(map.closeDoor(x, y));
	}
	
	public void testIsReady() {
		EditMapContract map = new EditMapContract(new EditMapImplem());
		map.init(largeur, hauteur);
		int x = largeur /2 ;
		int y = hauteur /2;
		map.setNature(0, 0, Cell.IN);
		map.setNature(x, y, Cell.OUT);
		assertTrue(map.isReady());
	}
	
	public static final Test suite(){
	    return new TestSuite(MapTest.class); 
	}
}
