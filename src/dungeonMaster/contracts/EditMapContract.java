package dungeonMaster.contracts;

import dungeonMaster.decorators.EditMapDecorator;
import dungeonMaster.services.Cell;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapContract implements EditMapDecorator {

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
	public void setNature(int x, int y, Cell c) {
		
	}

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
	public void openDoor(int x, int y) {
		
	}

	@Override
	public void closeDoor(int x, int y) {
		
	}

	@Override
	public Cell[][] getPlateau() {
		// TODO Auto-generated method stub
		return null;
	}

}
