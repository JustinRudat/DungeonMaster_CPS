package dungeonMaster.services;

import java.util.ArrayList;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Command;
import dungeonMaster.enumeration.Dir;

public interface PlayerService extends /* includes */ EntityService {

	public OptionService<Command> getLastCommand();
	public boolean setLastCommand(Command command);
	public OptionService<MobService> getContent(int x, int y);
	public Cell getNature(int x, int y);
	public Cell getViewable(int x, int y);
	public int getPacification();
	public ArrayList<LootService> getBag();
	public ArrayList<LootService> getKeys();
	public boolean addKey(LootService lt);
	public boolean addLoot(LootService lt);
	public boolean isDef();
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int dmg,int armor);
	public Cell isViewable(int col, int row);
	
	/** Invariants :
	 *  inv: this.getFace() == N \implies this.getContent(u,v) = this.getEnv().cellNature(this.getCol()+u, this.getRow()+v)
	 *  inv: this.getFace() == N \implies this.getNature(u,v) = this.getEnv().cellNature(this.getCol()+u, this.getRow()+v)
	 *  inv: this.getFace() == S \implies this.getContent(u,v) = this.getEnv().cellNature(this.getCol()-u, this.getRow()-v)
	 *  inv: this.getFace() == S \implies this.getNature(u,v) = this.getEnv().cellNature(this.getCol()-u, this.getRow()-v)
	 *  inv: this.getFace() == E \implies this.getContent(u,v) = this.getEnv().cellNature(this.getCol()+v, this.getRow()-u)
	 *  inv: this.getFace() == E \implies this.getNature(u,v) = this.getEnv().cellNature(this.getCol()+v, this.getRow()-u)
	 *  inv: this.getFace() == W \implies this.getContent(u,v) = this.getEnv().cellNature(this.getCol()-v, this.getRow()+u)
	 *  inv: this.getFace() == W \implies this.getNaturet(u,v) = this.getEnv().cellNature(this.getCol()-v, this.getRow()+u)
	 *  
	 *  \forall u,v in [-1,1]*[-1,1], this.getViewable(u,v) == null
	 *  
	 *  this.getViewable(-1,2)) = this.getNature(-1,1) not in {WLL,DWC,DNC}
	 *  this.getViewable(0,2)) = this.getNature(0,1) not in {WLL,DWC,DNC}
	 *  this.getViewable(1,2)) = this.getNature(1,1) not in {WLL,DWC,DNC}
	 * 	this.getViewable(-1,3)) = this.getNature(-1,2) not in {WLL,DWC,DNC} AND this.getViewable(-1,2)) != null
	 * 	this.getViewable(0,3)) = this.getNature(0,2) not in {WLL,DWC,DNC}	AND this.getViewable(0,2)) != null
	 * 	this.getViewable(1,3)) = this.getNature(1,2) not in {WLL,DWC,DNC}	AND this.getViewable(1,2)) != null
	 */
	
	
	/**
	 * 
	 * post: this.getLastCommand() == FF \implies this.step() = this.forward()
	 * post: this.getLastCommand() == BB \implies this.step() = this.backward()
	 * post: this.getLastCommand() == LL \implies this.step() = this.strafeL()
	 * post: this.getLastCommand() == RR \implies this.step() = this.strafeR()
	 * post: this.getLastCommand() == TL \implies this.step() = this.turnL()
	 * post: this.getLastCommand() == TR \implies this.step() = this.turnR() 
	 */
	public boolean step();
	
	public boolean attack();
	
	public boolean take();
	public boolean pacify();
	

}
