package dungeonMaster.components;

import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class EnvironmentImplem extends MapImplem implements EnvironmentService{

	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		return null;
		
	}

	
}
