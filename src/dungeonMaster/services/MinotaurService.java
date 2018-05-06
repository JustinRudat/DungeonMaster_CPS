package dungeonMaster.services;

public interface MinotaurService extends MonsterService {
	public boolean init(EnvironmentService env, int x, int y, Dir dir);
	public boolean attack();
}
