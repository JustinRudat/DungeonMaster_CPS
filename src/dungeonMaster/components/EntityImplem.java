package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EntityImplem extends MobImplem implements EntityService{
	private int healthPoints;
	@Override
	public int getHealthPoints() {
		return this.healthPoints;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		super.init(env,x,y,dir);
		this.healthPoints = hp;
		
	}

	@Override
	public void step() {
		
	}

	

}
