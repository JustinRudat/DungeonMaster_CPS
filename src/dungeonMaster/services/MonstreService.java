package dungeonMaster.services;

public interface MonstreService extends /* includes */ EntityService {
	
	public boolean sniffAPlayer();
	
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int portee);

}
