package dungeonMaster.contracts;

import dungeonMaster.decorators.MinotaurDecorator;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Option;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
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

	
	public boolean attack() {
		boolean retour = false;
		OptionService<MobService> opt = null;
		int x =-1;
		int y =-1;
		switch(this.getFace()) {
			case N:
				x = this.getCol();
				y = this.getRow()+1;
				break;
			case S:
				x = this.getCol();
				y = this.getRow()-1;
				break;
			case W:
				x = this.getCol()-1;
				y = this.getRow();
				break;
			case E:
				x = this.getCol()+1;
				y = this.getRow();
				break;
			default: 
				break;
		}
		opt = this.getEnv().cellContent(x,y);
		MobService mob = opt.getElem();
		int hp_at_pre =-1;
		if(mob!=null) {
			try {
				if(!(mob instanceof PlayerService)) {
					throw new PreConditionException("attack : minotaur : mob doesnt attack mob");
				}
				hp_at_pre = ((PlayerService)mob).getHealthPoints();
			}catch(PreConditionException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		retour =super.attack();
		
		if(mob!=null) {
			try {
				
				if(mob.getCol()==x&&mob.getRow()==y) {
					int degat = 2*(getDegats() - ((PlayerService)mob).getArmor()) - (((PlayerService)mob).isDef()?1:0);
					if(degat>0) {
						if(((PlayerService)mob).getHealthPoints()!=hp_at_pre - degat) {
							throw new PostConditionException("attack : minotaur : damage not properly done");
						}
					}
				}
			}catch(PostConditionException e) {
				e.printStackTrace();
				retour = false;
			}
		}
		
		return retour;
	}
}
