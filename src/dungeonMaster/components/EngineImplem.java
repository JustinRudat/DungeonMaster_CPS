package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.services.Cell;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class EngineImplem implements EngineService {
	private boolean isgameover;
	private boolean iswin;
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
		this.iswin=false;
		return true;
	}

	@Override
	public boolean removeEntity(int x) {
		
		EntityService rem = this.getEntities().remove(x);
		return (rem!=null);
	}

	@Override
	public boolean addEntity(EntityService entity) {
		
		return this.getEntities().add(entity);
	}

	@Override
	public boolean step() {
		for(int j=0; j<entities.size();j++) {
			EntityService entity = this.getEntity(j);
		
			
			entity.step();
			if(entity instanceof PlayerService) {
				if(this.getEnv().cellNature(entity.getCol(), entity.getRow())==Cell.OUT ) {
					if(((PlayerService)entity).getBag().size() > 0 && ((PlayerService)entity).getBagAt(0).getLootType() == LootType.Treasure) {
						this.isgameover=true;
						this.iswin=true;
						break;
					}
				}
				
			}
			for(int i=0;i<entities.size();i++) {
				EntityService entity_tmp = this.getEntities().get(i);
				if(entity_tmp.getHealthPoints()<=0) {
					if(entity_tmp instanceof PlayerService) {
						boolean exist_another_player = false;
						for(EntityService entity_trd : this.getEntities()) {
							if(entity_trd instanceof PlayerService) {
								if(!entity_tmp.equals(entity_trd)) {
									exist_another_player=true;
								}
							}
						}
						if(!exist_another_player) {
							this.isgameover=true;
							this.iswin=false;
						}
					}
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

	@Override
	public boolean isGameOver() {
		return this.isgameover;
	}

	@Override
	public boolean isWin() {
		return this.iswin;
	}

	
	
	

}
