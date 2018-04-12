package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EngineDecorator implements EngineService{
	private final EngineService delegate;
	
	public EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	public EngineService getDelegate() {
		return this.delegate;
	}
	@Override
	public EnvironmentService getEnv() {
		return this.delegate.getEnv();
	}

	@Override
	public ArrayList<EntityService> getEntities() {
		return this.delegate.getEntities();
	}

	@Override
	public EntityService getEntity(int index) {
		return this.delegate.getEntity(index);
	}

	@Override
	public void init(EnvironmentService env) {
		this.delegate.init(env);
		
	}

	@Override
	public void removeEntity(int x) {
		this.delegate.removeEntity(x);
		
	}

	@Override
	public void addEntity(EntityService entity) {
		this.delegate.addEntity(entity);
		
	}

	@Override
	public void step() {
		this.delegate.step();
		
	}

}
