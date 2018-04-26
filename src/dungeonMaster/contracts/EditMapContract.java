package dungeonMaster.contracts;

import dungeonMaster.decorators.EditMapDecorator;
import dungeonMaster.services.Cell;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapContract extends EditMapDecorator {

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return super.isReachable(x1, y1, x2, y2);
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return super.isReady();
	}

	@Override
	public boolean setNature(int x, int y, Cell c) {
		return super.setNature(x, y, c);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public Cell cellNature(int i, int j) {
		// TODO Auto-generated method stub
		return super.cellNature(i, j);
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		return super.init(largeur, hauteur);
	}

	@Override
	public boolean openDoor(int x, int y) {
		return super.openDoor(x, y);
	}

	@Override
	public boolean closeDoor(int x, int y) {
		return super.closeDoor(x, y);
	}

	@Override
	public Cell[][] getPlateau() {
		// TODO Auto-generated method stub
		return super.getPlateau();
	}

}
