package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.PlayerService;

public class EditMapDecorator extends MapDecorator implements EditMapService{
	
	public EditMapDecorator(EditMapService delegate) {
		super(delegate);
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return ((EditMapService) this.getDelegate()).isReachable(x1,y1,x2,y2);
	}

	@Override
	public boolean isReady() {
		return ((EditMapService) this.getDelegate()).isReady();
	}

	@Override
	public boolean setNature(int x, int y, Cell c) {
		return ((EditMapService) this.getDelegate()).setNature(x, y, c);
		
	}

	@Override
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin) {
		return ((EditMapService) this.getDelegate()).plusCourtChemin(graphe, depart, fin);
	}

	@Override
	public boolean randomEditSquare() {
		return ((EditMapService) this.getDelegate()).randomEditSquare();
	}

	

	@Override
	public boolean addDoorLock(int x, int y, String color,Cell type) {
		return ((EditMapService) this.getDelegate()).addDoorLock(x, y, color,type);
	}
	

}
