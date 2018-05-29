package dungeonMaster.contracts;

import dungeonMaster.decorators.EntityDecorator;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Option;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

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
			if(degat<=0) {
				throw new PreConditionException("damage initialized to 0 or less");
			}
			if(armor<0) {
				throw new PreConditionException("armor initialized to less than zero");
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
				throw new PostConditionException("init : entity : error on degat setting");
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
	
	@Override
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
		if(mob != null) {
			hp_at_pre = ((EntityService)mob).getHealthPoints();
		}
		
		retour = super.attack();
		
		try {
			int degat;
			if(mob instanceof PlayerService) {
				degat = this.getDegats() - ((EntityService)mob).getArmor() - (((PlayerService)mob).isDef()?1:0);
			}else {
				degat = this.getDegats() - ((EntityService)mob).getArmor();
			}
			if(hp_at_pre-(degat>0?degat:0)!=((EntityService)mob).getHealthPoints()) {
				throw new PostConditionException("attack : entity : error on lowering hp");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	

	

}
