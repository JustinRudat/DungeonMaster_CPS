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
		return super.cellContent(x, y);
		
	}

	@Override
	public boolean closeDoor(int x, int y) {
		return super.closeDoor(x, y);
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public Cell cellNature(int i, int j) {
		return super.cellNature(i, j);
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		return super.init(largeur, hauteur);
	}

	@Override
	public boolean openDoor(int x, int y) {
		return super.openDoor(x, y);
	}

	@Override
	public Cell[][] getPlateau() {
		return super.getPlateau();
	}

}
