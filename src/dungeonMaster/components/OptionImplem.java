package dungeonMaster.components;

import dungeonMaster.enumeration.Opt;
import dungeonMaster.services.OptionService;

public class OptionImplem<T> implements OptionService<T> {
	
	private Opt option;
	private T element;

	@Override
	public Opt getOption() {
		return this.option;
	}

	@Override
	public T getElem() {
		return this.element;
	}
	
	public boolean init(T elemt, Opt opt){
		this.option = opt;
		this.element = elemt;
		return true;
		
	}

	@Override
	public boolean setOption(Opt opt) {
		this.option=opt;
		return true;
	}

	@Override
	public boolean setElem(T elem) {
		this.element=elem;
		return true;
	}

}
