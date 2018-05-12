package dungeonMaster.decorators;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MobService;

public class LootDecorator extends MobDecorator implements LootService {

	public LootDecorator(LootService delegate) {
		super(delegate);
	}

	@Override
	public boolean init(EnvironmentService env, int col, int row, LootType lt, int puis, String name) {
		return ((LootService)getDelegate()).init(env, col, row, lt, puis, name);
	}

	@Override
	public int getPuis() {
		return ((LootService)getDelegate()).getPuis();
	}

	@Override
	public String getName() {
		return ((LootService)getDelegate()).getName();
	}

	@Override
	public LootType getLootType() {
		return ((LootService)getDelegate()).getLootType();
	}

	
}
