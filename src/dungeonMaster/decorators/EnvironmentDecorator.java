package dungeonMaster.decorators;

import dungeonMaster.services.Cell;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class EnvironmentDecorator extends MapDecorator implements EnvironmentService{

	public EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
	}

	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		return ((EnvironmentService) this.getDelegate()).cellContent(x,y);
	}
	

}
