package dungeonMaster.services;

public interface LootService extends MobService{
	public boolean init(EnvironmentService env, int col, int row,LootType lt, int puis,String name);
	public int getPuis();
	public String getName();
	public LootType getLootType();
	
}
