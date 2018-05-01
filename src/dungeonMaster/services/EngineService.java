package dungeonMaster.services;

import java.util.ArrayList;

public interface EngineService {
	/** Invariants :
	 * 	inv: \forall i in [0;this.getEntities().size()-1], this.getEntity(i).getEnv() == this.getEnv()
	 *  inv: \forall i in [0;this.getEntities().size()-1], this.getEntity(i).getCol() = x
	 *  												AND this.getEntity(i).getRow() = y
	 *  												\implies this.getEnv().getContent(x,y) == this.getEntity(i)
	 */
	
	
	public boolean isGameOver();
	public boolean isWin();

	public EnvironmentService getEnv();
	public ArrayList<EntityService> getEntities();
	public EntityService getEntity(int index);
	
	/**
	 * 
	 * @param env
	 */
	public boolean init(EnvironmentService env);
	
	/**
	 * pre: removeEntity(x) requires 0 <= x < this.getEntities().size()
	 * 
	 * post: this.removeEntity(i).getEntities().size() == this.getEntities().size() -1
	 * post: \forall k in [0;i-1], this.removeEntity(i).getEntity(k) == this.getEntity(k);
	 * post: \forall k in [i;this.getEntities().size() -2 ], this.removeEntity(i).getEntity(k) == this.getEntity(k+1);
	 * 
	 * @param x
	 */
	public boolean removeEntity(int x);
	
	/**
	 * post: this.removeEntity(i).getEntities().size() == this.getEntities().size() +1
	 * post: \forall k in [0;this.getEntities().size()-2], this.addEntity(e).getEntity(k) == this.getEntity(k)
	 * post: this.addEntity(e).getEntity(this.getEntities.size()) == e
	 * @param entity
	 */
	public boolean addEntity(EntityService entity);
	
	/**
	 * pre: this.step() requires \forall i in [0;this.getEntities().size() -1], this.getEntity(i).getHealthPoint() > 0
	 * 
	 */
	public boolean step();
	
	public EngineService generateRandomGame();
	
	public boolean addLoot(int x,int y);
	
}
