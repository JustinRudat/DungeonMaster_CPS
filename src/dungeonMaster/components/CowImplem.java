package dungeonMaster.components;

import java.util.Random;

import dungeonMaster.services.Cell;
import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class CowImplem extends EntityImplem implements CowService {
	
	@Override
	public boolean step() {
		Random rand = new Random();
		int le_tirage = rand.nextInt(90);
		if(le_tirage<30) {
			this.forward();
		}
		else if(le_tirage<60) {
			this.turnL();
		}
		else if(le_tirage<=90) {
			this.turnR();
		}
		return true;
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg) {
		return super.init(env, x, y, dir, hp, 1);
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		return super.init(env, x, y, dir, hp, 1);
		
	}
}
