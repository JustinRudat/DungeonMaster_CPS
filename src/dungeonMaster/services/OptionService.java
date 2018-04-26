package dungeonMaster.services;

public interface OptionService<T> {
	public Option getOption();
	public T getElem();
	public boolean init(T elem, Option opt);
	public boolean setOption(Option opt);
	public boolean setElem(T elem);
}
