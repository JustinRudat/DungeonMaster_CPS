package dungeonMaster.decorators;

import dungeonMaster.services.CowService;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.PlayerService;

public class CowDecorator extends EntityDecorator implements CowService {
	
	public  CowDecorator(CowService delegate) {
		super(delegate);
	}
	
	

}
