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
	
	public void init(T elemt, Option opt){
		this.option = opt;
		this.element = elemt;
	
		
	}

	@Override
	public void setOption(Option opt) {
		this.option=opt;
	}

	@Override
	public void setElem(T elem) {
		this.element=elem;
	}

}
