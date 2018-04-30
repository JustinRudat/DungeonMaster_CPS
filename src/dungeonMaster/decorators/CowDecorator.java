package dungeonMaster.decorators;

import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.PlayerService;

public class CowDecorator extends EntityDecorator implements CowService {
	
	public  CowDecorator(CowService delegate) {
		super(delegate);
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp) {
		return ((EntityService)this.getDelegate()).init(env, x, y, dir, hp, 0,0);
		
	}
	
	

}
