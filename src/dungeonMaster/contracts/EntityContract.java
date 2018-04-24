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
		return super.getHealthPoints();
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
		
		super.init(env, x, y, dir, hp, degat);
	}

	@Override
	public void step() {
		super.step();

	}

	@Override
	public EnvironmentService getEnv() {
		return super.getEnv();
	}

	@Override
	public int getCol() {
		return super.getCol();
	}

	@Override
	public int getRow() {
		return super.getRow();
	}

	@Override
	public Dir getFace() {
		return super.getFace();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir) {
		super.init(env, x, y, dir);

	}

	@Override
	public void forward() {
		super.forward();

	}

	@Override
	public void backward() {
		super.backward();

	}

	@Override
	public void turnL() {
		super.turnL();

	}

	@Override
	public void turnR() {
		super.turnR();

	}

	@Override
	public void strafeL() {
		super.strafeL();

	}

	@Override
	public void strafeR() {
		super.strafeR();

	}

}
