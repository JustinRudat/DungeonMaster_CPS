package dungeonMaster.contracts;

import dungeonMaster.decorators.MonsterDecorator;
import dungeonMaster.services.MonsterService;

public class MonsterContract extends MonsterDecorator {

	public MonsterContract(MonsterService delegate) {
		super(delegate);
	}

}
