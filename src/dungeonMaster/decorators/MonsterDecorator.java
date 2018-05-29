package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MonsterService;

public class MonsterDecorator extends EntityDecorator implements MonsterService {

	public MonsterDecorator(MonsterService delegate) {
		super(delegate);
	}

	@Override
	public boolean sniffAPlayer() {
		return ((MonsterService)getDelegate()).sniffAPlayer();
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg, int armor, int portee,
			int men_res, int drop_c) {
		return ((MonsterService)getDelegate()).init(env, x, y, dir, hp, dmg, armor, portee, men_res, drop_c);
	}

	@Override
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin) {
		return ((MonsterService)getDelegate()).plusCourtChemin(graphe, depart, fin);
	}

	@Override
	public boolean moveTo(int x, int y) {
		return ((MonsterService)getDelegate()).moveTo(x, y);
	}
	
	@Override
	public boolean step() {
		return ((MonsterService)getDelegate()).step();
	}

	@Override
	public boolean setDropChance(int drop_c) {
		return ((MonsterService)getDelegate()).setDropChance(drop_c);
	}

	@Override
	public int getMentRes() {
		return ((MonsterService)getDelegate()).getMentRes();
	}

	@Override
	public boolean setMentRes(int ment_res) {
		return ((MonsterService)getDelegate()).setMentRes(ment_res);
	}

	@Override
	public int getDropChance() {
		return ((MonsterService)getDelegate()).getDropChance();
	}

	@Override
	public int getPortee() {
		return ((MonsterService)getDelegate()).getPortee();
	}

}
