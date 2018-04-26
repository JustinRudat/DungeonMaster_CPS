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
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int degat) {
		try {
			if(hp<=0) {
				throw new PreConditionException("hp initialized to 0 or less");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		
		return super.init(env, x, y, dir, hp, degat);
	}

	@Override
	public boolean step() {
		return super.step();

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
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		return super.init(env, x, y, dir);

	}

	@Override
	public boolean forward() {
		return super.forward();

	}

	@Override
	public boolean backward() {
		return super.backward();

	}

	@Override
	public boolean turnL() {
		return super.turnL();

	}

	@Override
	public boolean turnR() {
		return super.turnR();

	}

	@Override
	public boolean strafeL() {
		return super.strafeL();

	}

	@Override
	public boolean strafeR() {
		return super.strafeR();

	}

}
