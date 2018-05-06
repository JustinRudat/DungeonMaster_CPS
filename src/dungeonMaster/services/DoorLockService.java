package dungeonMaster.services;

public interface DoorLockService {
	public int getCol();
	public int getRow();
	public String getColor();
	public boolean init(int x, int y,String color);
}
