package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.MapService;

public class MapImplemBug implements MapService{
	private Cell[][] plateau;
	private int height;
	private int width;
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
		return this.plateau[i][j];
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		this.height = hauteur;
		this.width = largeur;
		this.plateau = new Cell[hauteur][largeur];
		for(int i =0; i< hauteur; i++) {
			for(int j=0;j<largeur;j++) {
				this.plateau[i][j] = Cell.EMP;
			}
		}
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
		this.plateau = plateau;
		return true;
	}

}
