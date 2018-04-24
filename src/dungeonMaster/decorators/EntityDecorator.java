package dungeonMaster.decorators;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public  class EntityDecorator extends MobDecorator implements EntityService {
	
	public EntityDecorator(EntityService delegate) {
		super(delegate);
	}

	@Override
	public int getHealthPoints() {
		return ((EntityService) this.getDelegate()).getHealthPoints();
	}

	

	@Override
	public void step() {
		((EntityService) this.getDelegate()).step();
		
	}

	@Override
	public int getDegats() {
		return ((EntityService) this.getDelegate()).getDegats();
	}

	@Override
	public void setHealthPoints(int hp) {
		((EntityService) this.getDelegate()).setHealthPoints(hp);
	}

	@Override
	public void setDegats(int dmg) {
		((EntityService) this.getDelegate()).setDegats(dmg);
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg) {
		((EntityService) this.getDelegate()).init(env, x, y, dir, hp, dmg);
	}

	@Override
	public void attack() {
		((EntityService) this.getDelegate()).attack();
	}
	

}
