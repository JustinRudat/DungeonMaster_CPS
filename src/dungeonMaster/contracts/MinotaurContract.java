package dungeonMaster.contracts;

import dungeonMaster.decorators.MinotaurDecorator;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.PlayerService;

public class MinotaurContract extends MinotaurDecorator {

	public MinotaurContract(MinotaurService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		boolean retour = false; 
		
		super.init(env, x, y, dir);
		
		try {
			if(getArmor()!=2) {
				throw new PostConditionException("init : minotaur : error on armor setting");
			}
			if(getDegats()!=4) {
				throw new PostConditionException("init : minotaur : error on dammage setting");
			}
			if(getDropChance()!=50) {
				throw new PostConditionException("init : minotaur : error on drop chance setting");
			}
			if(getHealthPoints()!=15) {
				throw new PostConditionException("init : minotaur : error on hp setting");
			}
			if(getMentRes()!=35) {
				throw new PostConditionException("init : minotaur : error on mental resistance setting");
			}
			if(getPortee()!=4) {
				throw new PostConditionException("init : minotaur : error on range setting");
			}
			
			
			
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		
		return retour;
	}

}
