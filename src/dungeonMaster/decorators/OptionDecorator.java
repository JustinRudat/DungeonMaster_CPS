package dungeonMaster.decorators;

import dungeonMaster.contracts.OptionContract;
import dungeonMaster.enumeration.Option;
import dungeonMaster.services.OptionService;

public class OptionDecorator<T> implements OptionService<T> {

	private final OptionService<T> delegate;
	
	public OptionDecorator(OptionService<T> delegate){
		this.delegate=delegate;
	}
	
	@Override
	public Option getOption() {
		return delegate.getOption();
	}

	@Override
	public T getElem() {
		return delegate.getElem();
	}

	@Override
	public boolean init(T elem, Option opt) {
		return delegate.init(elem, opt);
	}

	@Override
	public boolean setOption(Option opt) {
		return delegate.setOption(opt);
	}

	@Override
	public boolean setElem(T elem) {
		return delegate.setElem(elem);
	}

}
