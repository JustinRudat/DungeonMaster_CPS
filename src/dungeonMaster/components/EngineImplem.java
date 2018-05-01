package dungeonMaster.components;

import java.util.ArrayList;
import java.util.Random;

import dungeonMaster.services.Cell;
import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonstreService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class EngineImplem implements EngineService {
	private boolean isgameover;
	private boolean iswin;
	private EnvironmentService env;
	private ArrayList<EntityService> entities;
	
	private final int default_hauteur = 20;
	private final int default_largeur = 30;
	
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
							return true;
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
	
	public EngineService generateRandomGame() {
		this.entities = new ArrayList<>();
		EditMapImplem editmap = new EditMapImplem();
		editmap.init(default_largeur,default_hauteur);
		editmap.randomEdit();
		
		EnvironmentService env_tmp = new EnvironmentImplem();
		env_tmp.init(default_largeur,default_hauteur);
		env_tmp.setPlateau(editmap.getPlateau());
		
		Random rand = new Random();
		int nb_mob = 10+rand.nextInt(6);
		for(int i =0; i<nb_mob;i++) {
			MonstreService cow = new MonsterImplem();
			int x = 1+rand.nextInt(default_largeur-1);
			int y = 1+rand.nextInt(default_hauteur-1);
			Cell cell = env_tmp.cellNature(x,y);
			while(cell!=Cell.EMP&&cell!=Cell.OUT&&cell!=Cell.WLL&&cell!=Cell.DWC&&cell!=Cell.DNC&&cell!=Cell.IN) {
				x = 1+rand.nextInt(default_largeur-1);
				y = 1+rand.nextInt(default_hauteur-1);
				cell = env_tmp.cellNature(x,y);
			}
			cow.init(env_tmp, x, y, Dir.N,10,2,1,2);
			addEntity(cow);
		}
		int nb_item = 4+rand.nextInt(4);
		for(int i =0; i<nb_item;i++) {
			LootService loot = new LootImplem();
			int x = 1+rand.nextInt(default_largeur-1);
			int y = 1+rand.nextInt(default_hauteur-1);
			Cell cell = env_tmp.cellNature(x,y);
			while(cell!=Cell.EMP&&cell!=Cell.OUT&&cell!=Cell.WLL&&cell!=Cell.DWC&&cell!=Cell.DNC&&cell!=Cell.IN) {
				x = 1+rand.nextInt(default_largeur-1);
				y = 1+rand.nextInt(default_hauteur-1);
				cell = env_tmp.cellNature(x,y);
			}
			switch(rand.nextInt(2)) {
				case 0: 
					loot.init(env_tmp, x, y,LootType.Weapon,1,"epee");
					break;
				case 1: 
					loot.init(env_tmp, x, y,LootType.Armor,1,"cote de maille");
					break;
				default:
					break;
			}
			
			
		}
		LootService tresor = new LootImplem();
		int x = 1+rand.nextInt(default_largeur-1);
		int y = 1+rand.nextInt(default_hauteur-1);
		Cell cell = env_tmp.cellNature(x,y);
		while(cell!=Cell.EMP&&cell!=Cell.OUT&&cell!=Cell.WLL&&cell!=Cell.DWC&&cell!=Cell.DNC&&cell!=Cell.IN) {
			x = 1+rand.nextInt(default_largeur-1);
			y = 1+rand.nextInt(default_hauteur-1);
			cell = env_tmp.cellNature(x,y);
		}
		tresor.init(env_tmp, x, y, LootType.Treasure,0,"Treasure");
		
		boolean trouve = false;
		for(int i=1;i<env_tmp.getWidth();i++) {
			for(int j=1;j<env_tmp.getHeight();j++) {
				if(env_tmp.cellNature(i, j)==Cell.IN) {
					x=i;
					y=j;
					trouve = true;
					break;
				}
			}
			if(trouve) {
				break;
			}
		}
		
		PlayerService player = new PlayerImplem();
		player.init(env_tmp, x, y, Dir.N, 50, 3, 0);
		addEntity(player);
		this.env = env_tmp;
		this.isgameover = false;
		this.iswin=false;
		
		
		return this;
	}

	
	
	

}
