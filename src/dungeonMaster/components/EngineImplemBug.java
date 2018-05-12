package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.enumeration.Option;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class EngineImplemBug implements EngineService {
	private boolean isgameover;
	private EnvironmentService env;
	private ArrayList<EntityService> entities;
	private boolean iswin;
	
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
		this.env = new EnvironmentImplem(); 
		this.entities = new ArrayList<>();
		this.isgameover = false;
		this.iswin =false;
		return true;
	}

	@Override
	public boolean removeEntity(int x) {
		return true;
	}

	@Override
	public boolean addEntity(EntityService entity) {
		return true;
	}

	@Override
	public boolean step() {
		for(EntityService entity : this.getEntities()){
			
			entity.step();
			for(int i=0;i<entities.size();i++) {
				EntityService entity_tmp = this.getEntities().get(i);
				if(entity.getHealthPoints()<0) {
					OptionService<MobService> opt = new OptionImplem<>();
					opt.init(null, Option.So);
					this.getEnv().getContent().set(entity_tmp.getRow()*this.getEnv().getWidth()+entity_tmp.getCol(),opt);
					entities.remove(entity_tmp);
					i--;
				}
			}
		}
		return true;
		
	}

	@Override
	public boolean isGameOver() {
		return !this.isgameover;
	}

	@Override
	public boolean isWin() {
		return !this.iswin;
	}

	@Override
	public EngineService generateRandomGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addLoot(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EngineService generateRandomSquareGame() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
