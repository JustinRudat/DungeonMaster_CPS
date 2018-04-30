package dungeonMaster.services;

public interface EntityService extends /*includes */ MobService  {
	/**
	 * 
	 * @return this.healthpoints
	 */
	public int getHealthPoints();
	
	public int getDegats();
	
	public boolean setHealthPoints(int hp);
	
	public boolean setDegats(int dmg);
	
	public boolean setArmor(int armor);
	
	public int getArmor();
	
	/**
	 * pre: this.init(env,x,y,dir,hp) requires hp > 0 
	 * 
	 * post: this.init(env,x,y,dir,hp).getHealtPoints() == hp
	 * 
	 * @param env
	 * @param x
	 * @param y
	 * @param dir
	 * @param hp
	 */
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor);
	
	/**
	 * @return 
	 * 
	 */
	public boolean step();
	
	public boolean attack();

	
}
