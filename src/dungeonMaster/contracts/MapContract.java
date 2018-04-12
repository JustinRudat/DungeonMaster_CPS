package dungeonMaster.contracts;

import dungeonMaster.decorators.MapDecorator;
import dungeonMaster.services.Cell;
import dungeonMaster.services.MapService;

public class MapContract extends MapDecorator {

	public MapContract(MapService delegate) {
		super(delegate);
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
