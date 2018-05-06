package dungeonMaster.components;

import dungeonMaster.services.DoorLockService;

public class DoorLockImplem implements DoorLockService {
	private int col;
	private int row;
	private String color;
	
	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public boolean init(int x, int y, String color) {
		this.col=x;
		this.row=y;
		this.color=color;
		return true;
	}
	
}
