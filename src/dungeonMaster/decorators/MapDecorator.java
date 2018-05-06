package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.services.Cell;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.MapService;

public class MapDecorator implements MapService{
	
	private final MapService delegate;
	
	public MapDecorator(MapService delegate) {
		this.delegate = delegate;
	}
	
	public MapService getDelegate() {
		return this.delegate;
	}
	
	@Override
	public int getHeight() {
		return this.delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return this.delegate.getWidth();
	}

	@Override
	public Cell[][] getPlateau() {
		return this.delegate.getPlateau();
	}

	@Override
	public Cell cellNature(int i, int j) {
		return this.delegate.cellNature(i, j);
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		return this.delegate.init(largeur, hauteur);
		
	}

	@Override
	public boolean openDoor(int x, int y) {
		return this.delegate.openDoor(x, y);
		
	}

	@Override
	public boolean closeDoor(int x, int y) {
		return this.delegate.closeDoor(x, y);
		
	}

	@Override
	public boolean setPlateau(Cell[][] plateau) {
		return this.delegate.setPlateau(plateau);
	}

	@Override
	public ArrayList<DoorLockService> getDoorLocked() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setDoorLocked(ArrayList<DoorLockService> doors) {
		// TODO Auto-generated method stub
		return false;
	}

}
