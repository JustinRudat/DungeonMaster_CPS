package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class EngineImplem implements EngineService {
	private boolean isgameover;
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
	public boolean init(EnvironmentService env) {
		this.env = env; 
		this.entities = new ArrayList<>();
		this.isgameover = false;
		return true;
	}

	@Override
	public boolean removeEntity(int x) {
		this.getEntities().remove(x);
		return true;
	}

	@Override
	public boolean addEntity(EntityService entity) {
		this.getEntities().add(entity);
		return true;
	}

	@Override
	public boolean step() {
		for(int j=0; j<entities.size();j++) {
			EntityService entity = this.getEntity(j);
		
			
			entity.step();
			for(int i=0;i<entities.size();i++) {
				EntityService entity_tmp = this.getEntities().get(i);
				if(entity_tmp.getHealthPoints()<=0) {
					OptionService<MobService> opt = new OptionImplem<>();
					opt.init(null, Option.No);
					this.getEnv().getContent().set(entity_tmp.getRow()*this.getEnv().getWidth()+entity_tmp.getCol(),opt);
					entities.remove(entity_tmp);
					i--;
				}
			}
		}
		return true;
		
	}
	
	

}
