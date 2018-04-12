package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.decorators.EngineDecorator;
import dungeonMaster.exceptions.EntityInvariantException;
import dungeonMaster.exceptions.EnvironmentInvariantException;
import dungeonMaster.exceptions.InvariantException;
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
		return this.getDelegate().getEnv();
	}

	@Override
	public ArrayList<EntityService> getEntities() {
		return this.getDelegate().getEntities();
	}

	@Override
	public EntityService getEntity(int index) {
		return this.getDelegate().getEntity(index);
	}

	@Override
	public void init(EnvironmentService env) {
		
		checkInvariants();
		
		this.getDelegate().init(env);
		
		checkInvariants();
	}

	@Override
	public void removeEntity(int x) {
		
		checkInvariants();
		
		this.getDelegate().removeEntity(x);
		
		checkInvariants();
	}

	@Override
	public void addEntity(EntityService entity) {
		
		checkInvariants();
		
		this.getDelegate().addEntity(entity);
		
		checkInvariants();
	}

	@Override
	public void step() {
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
		
		this.getDelegate().step();
		
		checkInvariants();
	}

}
