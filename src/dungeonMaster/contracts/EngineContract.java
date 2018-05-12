package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.decorators.EngineDecorator;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.LootType;
import dungeonMaster.enumeration.Option;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.EntityInvariantException;
import dungeonMaster.exceptions.EnvironmentInvariantException;
import dungeonMaster.exceptions.InvariantException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.PlayerService;

public class EngineContract extends EngineDecorator {

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		try {
			for(EntityService entity : this.getEntities()) {
				if(!entity.getEnv().equals(this.getEnv())) {
					throw new EnvironmentInvariantException("entity.env != env");
				}
				int x = entity.getCol();
				int y = entity.getRow();
				if(!this.getEnv().cellContent(x, y).getElem().equals(entity)) {
					throw new EntityInvariantException("this.getEnv().getContent(x,y) != this.getEntity(i)");
				}
			}
		}catch (InvariantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EnvironmentService getEnv() {
		return super.getEnv();
	}

	@Override
	public ArrayList<EntityService> getEntities() {
		return super.getEntities();
	}

	@Override
	public EntityService getEntity(int index) {
		return super.getEntity(index);
	}

	@Override
	public boolean init(EnvironmentService env) {
		
		//checkInvariants();
		
		boolean retour = super.init(env);
		
		checkInvariants();
		
		try {
			if(this.getEnv()!=env) {
				throw new PostConditionException("error while setting the environment");
			}
		}catch(PostConditionException e ) {
			e.printStackTrace();
			retour =false;
		}
		
		return retour;
	}

	@Override
	public boolean removeEntity(int x) {
		boolean retour = false;
		try {
			int size_at_pre = this.getEntities().size();
			ArrayList<EntityService> entities_at_pre = (ArrayList<EntityService>) this.getEntities().clone(); 
			
			checkInvariants();
			
			retour = super.removeEntity(x);
			
			checkInvariants();
			
			if(this.getEntities().size()!=size_at_pre -1) {
				throw new PostConditionException("entities tab size doesnt match");
			}
			for(int k =0; k<x ; k++) {
				if(!this.getEntities().get(k).equals(entities_at_pre.get(k))) {
					throw new PostConditionException("removeEntity : index before, an entity have been changed ");
				}
			}
			for(int k =x; k<this.getEntities().size() ; k++) {
				if(!this.getEntities().get(k).equals(entities_at_pre.get(k+1))) {
					throw new PostConditionException("removeEntity : index after, an entity have been changed ");
				}
			}
		}catch(ConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
		
	}

	@Override
	public boolean addEntity(EntityService entity) {
		boolean retour = false;
		try {
			int size_at_pre = this.getEntities().size();
			ArrayList<EntityService> entities_at_pre = (ArrayList<EntityService>) this.getEntities().clone();
			checkInvariants();
			
			retour = super.addEntity(entity);
			
			checkInvariants();
			
			if(this.getEntities().size()!= size_at_pre+1) {
				throw new PostConditionException("addEntity : entity may not have been added or replaced an existing one");
			}
			for(int k = 0; k <size_at_pre;k++) {
				if(!this.getEntities().get(k).equals(entities_at_pre.get(k))) {
					throw new PostConditionException("addEntity : an entity already existing have been changed");
				}
			}
			if(!this.getEntities().get(size_at_pre).equals(entity)) {
				throw new PostConditionException("addEntity : the last list item is not the added entity");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
			retour=false;
		}
		return retour;
	}

	@Override
	public boolean step() {
		try {
			boolean test = false;
			for(EntityService entity: this.getEntities()) {
				if(entity.getHealthPoints()<0) {
					test= true;
					break;
				}
			}
			if(test) {
				throw new PreConditionException("step : Engine : mob with hp under 0");
			}
		}catch (PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		ArrayList<EntityService> entity_clone = new ArrayList<>();
		
		checkInvariants();
		
		boolean retour = super.step();
		
		checkInvariants();
		
		try {
			boolean test = false;
			for(EntityService entity: this.getEntities()) {
				if(entity instanceof PlayerService) {
					if(entity.getHealthPoints()==0) {
						if(!isGameOver()||isWin()) {
							throw new PostConditionException("step : engine : game not over or win while player hp =0\n");
						}
					}
					if(this.getEnv().cellNature(entity.getCol(), entity.getRow())==Cell.OUT ) {
						if(((PlayerService)entity).getBag().size() > 0 && ((PlayerService)entity).getBag().get(0).getLootType() == LootType.Treasure) {
							if(!(isGameOver()||isWin())) {
								throw new PostConditionException("step : engine : game not over and win while player is on Out with treasure\n");
							}
						}
					}
				}
				if(entity.getHealthPoints()<=0) {
					test= true;
					break;
				}
			}
			if(test) {
				throw new PostConditionException("step : Engine : mob with hp under or equal 0");
			}
		}catch (PostConditionException e) {
			e.printStackTrace();
			return false;
		}
		return retour;
	}
	
	

	@Override
	public EngineService generateRandomGame() {
		EngineService retour = null;
		
		
		
		checkInvariants();
		retour = super.generateRandomGame();
		checkInvariants();
		
		try {
			if(retour.isGameOver()) {
				throw new PostConditionException("generaterandomsquaregame : engine : gameover true");
			}
			if(retour.isWin()) {
				throw new PostConditionException("generaterandomsquaregame : engine : win true");
			}
			if(retour.getEntities().size()==0) {
				throw new PostConditionException("generaterandomsquaregame : engine : entities empty");
			}
			if(retour.getEnv()==null) {
				throw new PostConditionException("generaterandomsquaregame : engine : env null");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		
		return retour ;
	}

	@Override
	public boolean addLoot(int x, int y) {
		boolean retour = false;
		try {
			if(x<0||x>=getEnv().getWidth()) {
				throw new PreConditionException("addloot : engine : error on x\n");
			}
			if(y<0||y>=getEnv().getHeight()) {
				throw new PreConditionException("addloot : engine : error on y\n");
			}
			if(getEnv().cellContent(x, y).getOption()!=Option.No) {
				if(getEnv().cellContent(x, y).getElem() instanceof EntityService) {
					if(((EntityService)getEnv().cellContent(x, y).getElem()).getHealthPoints()!=0) {
						throw new PreConditionException("addloot : engine : cell already takne by a live entity\n");
					}
				}
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		checkInvariants();
		retour = super.addLoot(x, y);
		checkInvariants();
		try {
			if(getEnv().cellContent(x, y).getOption()!=Option.No){
				if(!(getEnv().cellContent(x, y).getElem() instanceof LootService)) {
					throw new PostConditionException("addloot : engine :  not a loot\n");
				}
			}
			
		}catch(PostConditionException e ) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public EngineService generateRandomSquareGame() {
		EngineService retour = null;
		
		
		
		checkInvariants();
		retour = super.generateRandomSquareGame();
		checkInvariants();
		
		try {
			if(retour.isGameOver()) {
				throw new PostConditionException("generaterandomsquaregame : engine : gameover true");
			}
			if(retour.isWin()) {
				throw new PostConditionException("generaterandomsquaregame : engine : win true");
			}
			if(retour.getEntities().size()==0) {
				throw new PostConditionException("generaterandomsquaregame : engine : entities empty");
			}
			if(retour.getEnv()==null) {
				throw new PostConditionException("generaterandomsquaregame : engine : env null");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		
		return retour ;
	}

}
