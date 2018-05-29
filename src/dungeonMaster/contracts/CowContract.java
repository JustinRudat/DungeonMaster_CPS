package dungeonMaster.contracts;

import dungeonMaster.decorators.CowDecorator;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.CowService;
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
			if(this.getArmor()!=0) {
				throw new PostConditionException("cow armor are supposed to be 0");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
			return false;
		}
		return retour;

	}
	
	@Override
	public boolean step() {
		int col_at_pre = this.getCol();
		int row_at_pre = this.getRow();
		Dir face_at_pre  =this.getFace();
		boolean retour = false;
		retour = super.step();
		try {
			if(this.getFace()==face_at_pre) {
				if(this.getRow()==row_at_pre && this.getCol()==col_at_pre) {
						retour =false;
						throw new PostConditionException("Cow didnt act.\n");
				}
			}
		} catch (PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

}