package dungeonMaster.decorators;

import dungeonMaster.services.Cell;
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
	public void init(int largeur, int hauteur) {
		this.delegate.init(largeur, hauteur);
		
	}

	@Override
	public void openDoor(int x, int y) {
		this.delegate.openDoor(x, y);
		
	}

	@Override
	public void closeDoor(int x, int y) {
		this.delegate.closeDoor(x, y);
		
	}

}
