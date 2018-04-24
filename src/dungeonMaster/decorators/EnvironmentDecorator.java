package dungeonMaster.decorators;

import java.util.ArrayList;

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

	@Override
	public ArrayList<OptionService<MobService>> getContent() {
		return ((EnvironmentService)this.getDelegate()).getContent();
	}

	@Override
	public void addMobOption(MobService mob) {
		((EnvironmentService)this.getDelegate()).addMobOption(mob);
		
	}

	@Override
	public void removeMobOption(MobService mob) {
		((EnvironmentService)this.getDelegate()).removeMobOption(mob);
	}
	

}
