package dungeonMaster.contracts;

import dungeonMaster.decorators.EnvironmentDecorator;
import dungeonMaster.services.Cell;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MapService;
import dungeonMaster.services.Option;

public class EnvironmentContract implements EnvironmentDecorator {

	@Override
	public Option cellContent(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentService closeDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
	public MapService openDoor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
