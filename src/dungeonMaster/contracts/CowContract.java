package dungeonMaster.contracts;

import dungeonMaster.decorators.CowDecorator;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;

public class CowContract extends CowDecorator {

	public CowContract(CowService delegate) {
		super(delegate);
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		try {
			if(hp>4 || hp<3) {
				throw new PreConditionException("hp for cow doesnt match 3 or 4");
			}
		}catch(ConditionException e){
			e.printStackTrace();
		}
		super.init(env, x, y, dir,hp,0);

	}

}