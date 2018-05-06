package dungeonMaster.decorators;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MinotaurService;

public class MinotaurDecorator extends MonsterDecorator implements MinotaurService {
	
	
	
	public MinotaurDecorator(MinotaurService delegate) {
		super(delegate);
	}
	public boolean attack() {
		return ((MinotaurService) this.getDelegate()).attack();
	}
	
	public boolean init(EnvironmentService env, int col, int row, Dir dir) {
		return ((MinotaurService) this.getDelegate()).init(env, col, row, dir);
	}

}
