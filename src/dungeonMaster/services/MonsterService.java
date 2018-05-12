package dungeonMaster.services;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Dir;

public interface MonsterService extends /* includes */ EntityService {
	
	public boolean sniffAPlayer();
	
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor,int portee,int men_res, int drop_c);

	public boolean step();
	
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin);
	
	public boolean moveTo(int x, int y);
	
	public boolean setDropChance(int drop_c);
	
	public int getMentRes();
	
	public boolean setMentRes(int ment_res);
	
	public int getDropChance();
	
	public int getPortee();
	
	
	
}
