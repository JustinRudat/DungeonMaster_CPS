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
	public boolean step() {
		return ((EntityService) this.getDelegate()).step();
		
	}

	@Override
	public int getDegats() {
		return ((EntityService) this.getDelegate()).getDegats();
	}

	@Override
	public boolean setHealthPoints(int hp) {
		return ((EntityService) this.getDelegate()).setHealthPoints(hp);
	}

	@Override
	public boolean setDegats(int dmg) {
		return ((EntityService) this.getDelegate()).setDegats(dmg);
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg) {
		return ((EntityService) this.getDelegate()).init(env, x, y, dir, hp, dmg);
	}

	@Override
	public boolean attack() {
		return ((EntityService) this.getDelegate()).attack();
	}
	

}
