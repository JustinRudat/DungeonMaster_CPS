package dungeonMaster.components;

import java.util.Random;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Opt;
import dungeonMaster.services.CowService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class CowImplemBug extends EntityImplem implements CowService {
	
	@Override
	public boolean step() {
		
		return true;
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		super.init(env, x, y, dir, hp, 1,0);
		return true;
		
	}
}
