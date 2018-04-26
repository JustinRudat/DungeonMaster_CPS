package dungeonMaster.tests;

import static org.junit.Assert.assertTrue;

import dungeonMaster.components.EditMapImplem;
import dungeonMaster.components.MapImplem;
import dungeonMaster.contracts.EditMapContract;
import dungeonMaster.contracts.MapContract;
import dungeonMaster.services.Cell;
import junit.framework.TestCase;
import junit.framework.TestResult;

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
		
		map.cellNature(10, 10);
	}
	
	public void testEditMapImplem() {
		EditMapContract map = new EditMapContract(new EditMapImplem());
		map.init(largeur, hauteur);
		int x = largeur /2 ;
		int y = hauteur /2;
		map.setNature(x-1, y, Cell.WLL);
		map.setNature(x+1, y, Cell.WLL);
		map.setNature(x, y, Cell.DNC);
		map.openDoor(x, y);
		map.closeDoor(x, y);
		map.setNature(0, 0, Cell.IN);
		map.setNature(hauteur -2, largeur-2, Cell.OUT);
		assertTrue(map.isReady());
	}
}
