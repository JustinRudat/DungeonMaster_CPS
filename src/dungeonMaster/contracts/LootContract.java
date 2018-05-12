package dungeonMaster.contracts;

import dungeonMaster.decorators.LootDecorator;
import dungeonMaster.enumeration.LootType;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;

public class LootContract extends LootDecorator {

	public LootContract(LootService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean init(EnvironmentService env, int col, int row, LootType lt, int puis, String name) {
		boolean retour = false;
		try {
			if(lt==null) {
				throw new PreConditionException("init : Loot : no lootype defined");
			}
			if(puis<0) {
				throw new PreConditionException("init : Loot : negative power");
			}
			
			
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour =  ((LootService)getDelegate()).init(env, col, row, lt, puis, name);
		
		try {
			if(((LootService)getDelegate()).getLootType()!=lt) {
				throw new PostConditionException("init : loot : eroor setting loottype");
			}
			if(((LootService)getDelegate()).getPuis()!=puis) {
				throw new PostConditionException("init : loot : eroor setting power");
			}
			if(!((LootService)getDelegate()).getName().equals(name)) {
				throw new PostConditionException("init : loot : eroor setting name");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	

}
