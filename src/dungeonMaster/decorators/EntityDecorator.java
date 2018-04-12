package dungeonMaster.decorators;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EntityDecorator extends MobDecorator implements EntityService {
	
	public EntityDecorator(EntityService delegate) {
		super(delegate);
	}

	@Override
	public int getHealthPoints() {
		// TODO Auto-generated method stub
		return ((EntityService) this.getDelegate()).getHealthPoints();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		((EntityService) this.getDelegate()).init(env, x, y, dir, hp);
		
	}

	@Override
	public void step() {
		((EntityService) this.getDelegate()).step();
		
	}
	

}
