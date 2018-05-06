package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.GobelinService;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MonsterService;

public class GobelinDecorator extends MonsterDecorator implements GobelinService {

	public GobelinDecorator(MonsterService delegate) {
		super(delegate);
	}

	public boolean init(EnvironmentService env, int col, int row, Dir dir) {
		return ((GobelinService) this.getDelegate()).init(env, col, row, dir);
	}

}
