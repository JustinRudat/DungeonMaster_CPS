package dungeonMaster.services;

public interface GobelinService extends MonsterService {
	public boolean init(EnvironmentService env, int x, int y, Dir dir);

}
