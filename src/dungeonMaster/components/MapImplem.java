package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.MapService;

public class MapImplem implements MapService{
	private Cell[][] plateau;
	private int height;
	private int width;
	private ArrayList<DoorLockService> locked_doors;
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}
	
	public Cell[][] getPlateau(){
		return this.plateau;
		
	}

	@Override
	public Cell cellNature(int i, int j) {
		if(i<0||i>=this.getWidth()) {
			return null;
		}
		if(j<0 || j>this.getHeight()) {
			return null;
		}
		return this.plateau[i][j];
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		this.height = hauteur;
		this.width = largeur;
		this.plateau = new Cell[largeur][hauteur];
		for(int i =0; i< largeur; i++) {
			for(int j=0;j<hauteur;j++) {
				this.plateau[i][j] = Cell.EMP;
			}
		}
		this.locked_doors = new ArrayList<>();
		return true;
	}

	@Override
	public boolean openDoor(int x, int y) {
		switch(this.plateau[x][y]) {
			case DWC:
				this.plateau[x][y] = Cell.DWO;
				break;
			case DNC:
				this.plateau[x][y] = Cell.DNO;
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean closeDoor(int x, int y) {
		switch(plateau[x][y]) {
			case DWO:
				this.plateau[x][y] = Cell.DWC;
				break;
			case DNO:
				this.plateau[x][y] = Cell.DNC;
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean setPlateau(Cell[][] plateau) {
		this.plateau=plateau;
		return true;
	}

	@Override
	public ArrayList<DoorLockService> getDoorLocked() {
		return this.locked_doors;
	}

	@Override
	public boolean setDoorLocked(ArrayList<DoorLockService> doors) {
		 this.locked_doors = doors;
		 return true;
	}

}
