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
	public boolean init(EnvironmentService env) {
		return this.delegate.init(env);
		
	}

	@Override
	public boolean removeEntity(int x) {
		return this.delegate.removeEntity(x);
		
	}

	@Override
	public boolean addEntity(EntityService entity) {
		return this.delegate.addEntity(entity);
		
	}

	@Override
	public boolean step() {
		return this.delegate.step();
		
	}

	@Override
	public boolean isGameOver() {
		return this.delegate.isGameOver();
	}

	@Override
	public boolean isWin() {
		return this.delegate.isWin();
	}

	@Override
	public EngineService generateRandomGame() {
		return this.delegate.generateRandomGame();
	}

	

}
