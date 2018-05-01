package dungeonMaster.components;

import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class OptionImplemBug<T> implements OptionService<T> {
	
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
	
	public boolean init(T elemt, Option opt){
		this.option = opt;
		this.element = elemt;
		return true;
		
	}

	@Override
	public boolean setOption(Option opt) {
		this.option=opt;
		return true;
	}

	@Override
	public boolean setElem(T elem) {
		this.element=elem;
		return true;
	}

}
