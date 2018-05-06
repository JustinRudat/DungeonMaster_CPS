package dungeonMaster.contracts;

import dungeonMaster.decorators.MinotaurDecorator;
import dungeonMaster.services.MinotaurService;

public class MinotaurContract extends MinotaurDecorator {

	public MinotaurContract(MinotaurService delegate) {
		super(delegate);
	}

}
