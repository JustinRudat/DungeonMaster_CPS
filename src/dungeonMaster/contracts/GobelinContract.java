package dungeonMaster.contracts;

import dungeonMaster.decorators.GobelinDecorator;
import dungeonMaster.services.MonsterService;

public class GobelinContract extends GobelinDecorator{

	public GobelinContract(MonsterService delegate) {
		super(delegate);
	}

}
