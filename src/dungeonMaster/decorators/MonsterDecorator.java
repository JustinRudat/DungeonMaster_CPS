package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MonsterService;

public class MonsterDecorator extends EntityDecorator implements MonsterService {

	public MonsterDecorator(MonsterService delegate) {
		super(delegate);
	}

	@Override
	public boolean sniffAPlayer() {
		return false;
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg, int armor, int portee,
			int men_res, int drop_c) {
		return false;
	}

	@Override
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin) {
		return null;
	}

	@Override
	public boolean moveTo(int x, int y) {
		return false;
	}

	@Override
	public boolean setDropChance(int drop_c) {
		return false;
	}

	@Override
	public int getMentRes() {
		return 0;
	}

	@Override
	public boolean setMentRes(int ment_res) {
		return false;
	}

	@Override
	public int getDropChance() {
		return 0;
	}

}
