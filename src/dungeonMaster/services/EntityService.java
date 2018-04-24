package dungeonMaster.services;

public interface EntityService extends /*includes */ MobService  {
	/**
	 * 
	 * @return this.healthpoints
	 */
	public int getHealthPoints();
	
	public int getDegats();
	
	public void setHealthPoints(int hp);
	
	public void setDegats(int dmg);
	
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
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg);
	
	/**
	 * 
	 */
	public void step();
	
	public void attack();

	
}
