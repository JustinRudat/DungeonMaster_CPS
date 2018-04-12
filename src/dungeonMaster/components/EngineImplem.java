package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

public class EngineImplem implements EngineService {
	private EnvironmentService env;
	private ArrayList<EntityService> entities;
	
	@Override
	public EnvironmentService getEnv() {
		return this.env;
	}

	@Override
	public ArrayList<EntityService> getEntities() {
		return this.entities;
	}

	@Override
	public EntityService getEntity(int index) {
		return entities.get(index);
	}

	@Override
	public void init(EnvironmentService env) {
		this.env = env; 
		this.entities = new ArrayList<>();
	}

	@Override
	public void removeEntity(int x) {
		this.getEntities().remove(x);
	}

	@Override
	public void addEntity(EntityService entity) {
		this.getEntities().add(entity);
	}

	@Override
	public void step() {
		for(EntityService entity : this.getEntities()){
			entity.step();
		}
		
	}

}
