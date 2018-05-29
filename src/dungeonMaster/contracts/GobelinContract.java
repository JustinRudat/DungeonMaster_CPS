package dungeonMaster.contracts;

import dungeonMaster.decorators.GobelinDecorator;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Opt;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.GobelinService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.PlayerService;

public class GobelinContract extends GobelinDecorator{

	public GobelinContract(GobelinService delegate) {
		super(delegate);
	}
	
	public boolean init(EnvironmentService env, int col, int row, Dir dir) {
		boolean retour = false; 
		
		retour = super.init(env, col, row, dir);
		
		try {
			if(getArmor()!=1) {
				throw new PostConditionException("init : gobelin : error on armor setting");
			}
			if(getDegats()!=2) {
				throw new PostConditionException("init : gobelin : error on dammage setting");
			}
			if(getDropChance()!=30) {
				throw new PostConditionException("init : gobelin : error on drop chance setting");
			}
			if(getHealthPoints()!=10) {
				throw new PostConditionException("init : gobelin : error on hp setting");
			}
			if(getMentRes()!=15) {
				throw new PostConditionException("init : gobelin : error on mental resistance setting");
			}
			if(getPortee()!=3) {
				throw new PostConditionException("init : gobelin : error on range setting");
			}
			
			
			
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour =false;
		}
		
		return retour;
	}
}
