package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.decorators.EngineDecorator;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.EntityInvariantException;
import dungeonMaster.exceptions.EnvironmentInvariantException;
import dungeonMaster.exceptions.InvariantException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EngineService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;

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
		
		checkInvariants();
		
		boolean retour = super.init(env);
		
		checkInvariants();
		
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
		}
		checkInvariants();
		
		boolean retour = super.step();
		
		checkInvariants();
		return retour;
	}

}
