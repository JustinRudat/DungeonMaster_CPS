package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapImplem implements EditMapService{

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cell cellNature(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MapService openDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapService closeDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EditMapService setNature(int x, int y, Cell c) {
		// TODO Auto-generated method stub
		return null;
	}

}
