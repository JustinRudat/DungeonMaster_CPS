package dungeonMaster.services;

public interface MonstreService extends /* includes */ EntityService {
	
	public boolean sniffAPlayer();
	
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor,int portee);

}
