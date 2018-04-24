package dungeonMaster.services;

public interface ObjetService {
	public String getName();
	public int getBonus();
	public int getCol();
	public int getRow();
	public void init(int col, int row, int bonus, String name);
}
