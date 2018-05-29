package dungeonMaster.services;

import dungeonMaster.enumeration.Opt;

public interface OptionService<T> {
	public Opt getOption();
	public T getElem();
	public boolean init(T elem, Opt opt);
	public boolean setOption(Opt opt);
	public boolean setElem(T elem);
}
