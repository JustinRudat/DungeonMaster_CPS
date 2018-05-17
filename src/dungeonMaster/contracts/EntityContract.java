package dungeonMaster.contracts;

import dungeonMaster.decorators.EntityDecorator;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Option;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;

public class EntityContract extends EntityDecorator {

	public EntityContract(EntityService delegate) {
		super(delegate);
	}

	

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int degat,int armor) {
		boolean retour = false;
		try {
			if(hp<=0) {
				throw new PreConditionException("hp initialized to 0 or less");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		
		retour= super.init(env, x, y, dir, hp, degat,armor);
		
		try {
			if(getHealthPoints()!=hp) {
				throw new PostConditionException("init : entity : error on hp setting");
			}
			if(getArmor()!=armor) {
				throw new PostConditionException("init : entity : error on armor setting");
			}
			if(getDegats()!=degat) {
				throw new PostConditionException("init : entity : error on damage setting");
			}
		}catch(PostConditionException e ) {
			e.printStackTrace();
			retour = false;
		}
		
		return retour;
	}

	@Override
	public boolean step() {
		boolean retour = false;
		
		retour = super.step();
		
		return retour;
	}

	

	

}
