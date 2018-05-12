package dungeonMaster.services;

import dungeonMaster.enumeration.Dir;

public interface CowService extends /*includes */ EntityService{
	
	/**
	 * 
	 * pre: this.init(env,x,y,dir,hp) requires 3 <= hp <=4
	 */
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp);
	
	/**
	 * 
	 * post: this.getCol() -1 <= this.step().getCol() <= this.getCol() +1
	 * post: this.getRow() -1 <= this.step().getRow() <= this.getRow() +1
	 */
	public boolean step();
}
