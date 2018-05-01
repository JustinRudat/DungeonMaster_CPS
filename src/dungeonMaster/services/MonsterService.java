package dungeonMaster.services;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;

public interface MonsterService extends /* includes */ EntityService {
	
	public boolean sniffAPlayer();
	
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor,int portee);

	public boolean step();
	
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin);
	
	public boolean moveTo(int x, int y);
	
}
