package dungeonMaster.contracts;

import dungeonMaster.decorators.EntityDecorator;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;

public class EntityContract implements EntityDecorator {

	@Override
	public int getHealthPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dir getFace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnL() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turnR() {
		// TODO Auto-generated method stub

	}

	@Override
	public void strafeL() {
		// TODO Auto-generated method stub

	}

	@Override
	public void strafeR() {
		// TODO Auto-generated method stub

	}

}
