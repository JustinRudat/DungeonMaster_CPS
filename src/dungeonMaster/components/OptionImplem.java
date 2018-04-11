package dungeonMaster.components;

import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class OptionImplem<T> implements OptionService<T> {
	
	private Option option;
	private T element;
	@Override
	public Option getOption() {
		return this.option;
	}

	@Override
	public T getElem() {
		return this.element;
	}

}
