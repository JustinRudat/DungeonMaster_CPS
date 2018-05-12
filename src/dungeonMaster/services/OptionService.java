package dungeonMaster.services;

import dungeonMaster.enumeration.Option;

public interface OptionService<T> {
	public Option getOption();
	public T getElem();
	public boolean init(T elem, Option opt);
	public boolean setOption(Option opt);
	public boolean setElem(T elem);
}
