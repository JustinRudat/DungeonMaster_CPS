package dungeonMaster.contracts;

import dungeonMaster.decorators.CowDecorator;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;

public class CowContract extends CowDecorator {

	public CowContract(CowService delegate) {
		super(delegate);
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor) {
		try {
			if(hp!=3&&hp!=4) {
				throw new PreConditionException("hp for cow doesnt match 3 or 4");
			}
		}catch(ConditionException e){
			e.printStackTrace();
			return false;
		}
		boolean retour =  super.init(env, x, y, dir,hp,dmg,armor);
		
		try {
			if(this.getDegats()!=1) {
				throw new PostConditionException("cow damage are supposed to be 1");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
			return false;
		}
		return retour;

	}

}