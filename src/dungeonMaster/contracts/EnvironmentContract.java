package dungeonMaster.contracts;

import dungeonMaster.decorators.EnvironmentDecorator;
import dungeonMaster.services.Cell;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MapService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class EnvironmentContract extends EnvironmentDecorator {

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}

	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		return null;
		
	}

	@Override
	public void closeDoor(int x, int y) {
		
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
	public Cell[][] getPlateau() {
		// TODO Auto-generated method stub
		return null;
	}

}
