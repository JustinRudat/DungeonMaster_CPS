package dungeonMaster.contracts;

import dungeonMaster.decorators.EntityDecorator;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EntityContract extends EntityDecorator {

	public EntityContract(EntityService delegate) {
		super(delegate);
	}

	@Override
	public int getHealthPoints() {
		return ((EntityService)this.getDelegate()).getHealthPoints();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp,int degat) {
		try {
			if(hp<=0) {
				throw new PreConditionException("hp initialized to 0 or less");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		
		((EntityService)this.getDelegate()).init(env, x, y, dir, hp, degat);
	}

	@Override
	public void step() {
		((EntityService)this.getDelegate()).step();

	}

	@Override
	public EnvironmentService getEnv() {
		return ((EntityService)this.getDelegate()).getEnv();
	}

	@Override
	public int getCol() {
		return ((EntityService)this.getDelegate()).getCol();
	}

	@Override
	public int getRow() {
		return ((EntityService)this.getDelegate()).getRow();
	}

	@Override
	public Dir getFace() {
		return ((EntityService)this.getDelegate()).getFace();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir) {
		((EntityService)this.getDelegate()).init(env, x, y, dir);

	}

	@Override
	public void forward() {
		((EntityService)this.getDelegate()).forward();

	}

	@Override
	public void backward() {
		((EntityService)this.getDelegate()).backward();

	}

	@Override
	public void turnL() {
		((EntityService)this.getDelegate()).turnL();

	}

	@Override
	public void turnR() {
		((EntityService)this.getDelegate()).turnR();

	}

	@Override
	public void strafeL() {
		((EntityService)this.getDelegate()).strafeL();

	}

	@Override
	public void strafeR() {
		((EntityService)this.getDelegate()).strafeR();

	}

}
