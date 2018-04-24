package dungeonMaster.services;

public interface OptionService<T> {
	public Option getOption();
	public T getElem();
	public void init(T elem, Option opt);
	public void setOption(Option opt);
	public void setElem(T elem);
}
