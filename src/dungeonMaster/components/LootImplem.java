package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;

public class LootImplem extends MobImplem implements LootService {
	private String name;
	private LootType type;
	private int puis;
	@Override
	public boolean init(EnvironmentService env, int col, int row, LootType lt, int puis, String name) {
		super.init(env, col, row, Dir.N);
		this.name=name;
		this.puis=puis;
		this.type = lt;
		return true;
	}

	@Override
	public int getPuis() {
		return this.puis;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public LootType getLootType() {
		return this.type;
	}

	
}
