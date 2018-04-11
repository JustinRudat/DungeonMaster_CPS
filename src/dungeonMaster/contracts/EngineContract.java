package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.decorators.EngineDecorator;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EngineContract implements EngineDecorator {

	@Override
	public EnvironmentService getEnv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EntityService> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityService getEntity(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(EnvironmentService env) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEntity(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEntity(EntityService entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

}
