package dungeonMaster.components;

import java.util.Random;

import dungeonMaster.services.Cell;
import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class CowImplemBug extends EntityImplem implements CowService {
	
	@Override
	public boolean step() {
		
		return true;
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		super.init(env, x, y, dir, hp, 1);
		return true;
		
	}
}
