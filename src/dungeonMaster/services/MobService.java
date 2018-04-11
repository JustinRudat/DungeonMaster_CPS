package dungeonMaster.services;

public interface MobService {
	/**
	 * Invariants : 
	 * 		inv :  0 <= this.getCol() < this.getEnv().getWidth()
	 * 		inv :  0 <= this.getRow() < this.getEnv().getHeight()
	 * 		inv : EnvironmentService.cellNature(this.getEnv(), this.getCol(), this.getRow()) != WLL
	 * 		inv : EnvironmentService.cellNature(this.getEnv(), this.getCol(), this.getRow()) != DNC
	 * 		inv : EnvironmentService.cellNature(this.getEnv(), this.getCol(), this.getRow()) != DWC 
	 * 
	 */
	
	/**
	 * 
	 * @return this.env
	 */
    public EnvironmentService getEnv();
    
    /**
     * 
     * @return this.col
     */
    public int getCol();
    
    /**
     * 
     * @return this.row
     */
    public int getRow();
    
    /**
     * 
     * @return this.dir
     */
    public Dir getFace();
    
    /**
     * pre:  0 <= x < env.getWidth() AND 0 <= y < env.getHeight()
     * post: this.getCol(init(env,x,y,dir)) = x
     * post: this.getRow(init(env,x,y,dir)) = y
     * post: this.getFace(init(env,x,y,dir)) = dir
     * post: this.getEnvi(init(env,x,y,dir)) = env
     * 
     * @param env
     * @param x
     * @param y
     * @param dir
     */
    public void init(EnvironmentService env, int x, int y, Dir dir);
    
    /**
     * post: mob.forward().getFace == mob.getFace()
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == DWO
     * 						AND this.getRow()+1 < this.getEnv().getHeight()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) == No
     * 						\implies this.forward().getRow() == this.getRow() + 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != DWO
     * 						OR this.getRow()+1 >= this.getEnv().getHeight()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == DWO
     * 						AND this.getCol()+1 < this.getEnv().getWidth()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow()  
     * 						AND this.forward().getCol() == this.getCol() + 1
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != DWO
     * 						OR this.getCol()+1 >= this.getEnv().getWidth()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     *  
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == DWO
     * 						AND this.getRow()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) == No
     * 						\implies this.forward().getRow() == this.getRow() - 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != DWO
     * 						OR this.getRow()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()     
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == DWO
     * 						AND this.getCol()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow() 
     * 						AND this.forward().getCol() == this.getCol() - 1
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != DWO
     * 						OR this.getCol()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     */
    public void forward();
    
    /**
     * post: mob.backward().getFace() == mob.getFace()
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == DWO
     * 						AND this.getRow()+1 < this.getEnv().getWidth()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) == No
     * 						\implies this.forward().getRow() == this.getRow() + 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != DWO
     * 						OR this.getRow()+1 >= this.getEnv().getWidth()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == DWO
     * 						AND this.getCol()+1 < this.getEnv().getHeight()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow()  
     * 						AND this.forward().getCol() == this.getCol() + 1
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != DWO
     * 						OR this.getCol()+1 >= this.getEnv().getHeight()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     *  
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == DWO
     * 						AND this.getRow()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) == No
     * 						\implies this.forward().getRow() == this.getRow() - 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != DWO
     * 						OR this.getRow()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()     
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == DWO
     * 						AND this.getCol()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow() 
     * 						AND this.forward().getCol() == this.getCol() - 1
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != DWO
     * 						OR this.getCol()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     */
    public void backward();
    
    /**
     * 
     * post: this.getFace() == N \implies this.turnLeft().getFace() == W
     * post: this.getFace() == W \implies this.turnLeft().getFace() == S
     * post: this.getFace() == S \implies this.turnLeft().getFace() == E
     * post: this.getFace() == E \implies this.turnLeft().getFace() == N
     */
    public void turnL();
    
    /**
     * 
     * post: this.getFace() == S \implies this.turnLeft().getFace() == W
     * post: this.getFace() == E \implies this.turnLeft().getFace() == S
     * post: this.getFace() == N \implies this.turnLeft().getFace() == E
     * post: this.getFace() == W \implies this.turnLeft().getFace() == N
     */
    public void turnR();
    
    /**
     * post: mob.strafeL().getFace == mob.getFace()
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == DWO
     * 						AND this.getRow()+1 < this.getEnv().getHeight()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) == No
     * 						\implies this.forward().getRow() == this.getRow() + 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != DWO
     * 						OR this.getRow()+1 >= this.getEnv().getHeight()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == DWO
     * 						AND this.getCol()+1 < this.getEnv().getWidth()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow()  
     * 						AND this.forward().getCol() == this.getCol() + 1
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != DWO
     * 						OR this.getCol()+1 >= this.getEnv().getWidth()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     *  
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == DWO
     * 						AND this.getRow()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) == No
     * 						\implies this.forward().getRow() == this.getRow() - 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != DWO
     * 						OR this.getRow()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()     
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == DWO
     * 						AND this.getCol()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow() 
     * 						AND this.forward().getCol() == this.getCol() - 1
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != DWO
     * 						OR this.getCol()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     */
    public void strafeL();
    
    /**
     * post: mob.strifeR().getFace == mob.getFace()
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) == DWO
     * 						AND this.getRow()+1 < this.getEnv().getWidth()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) == No
     * 						\implies this.forward().getRow() == this.getRow() + 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == W \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()+1) != DWO
     * 						OR this.getRow()+1 >= this.getEnv().getWidth()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()+1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) == DWO
     * 						AND this.getCol()+1 < this.getEnv().getHeight()
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow()  
     * 						AND this.forward().getCol() == this.getCol() + 1
     * 
     * post: mob.getFace() == N \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()+1,this.getRow()) != DWO
     * 						OR this.getCol()+1 >= this.getEnv().getHeight()
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()+1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     *  
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) == DWO
     * 						AND this.getRow()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) == No
     * 						\implies this.forward().getRow() == this.getRow() - 1 
     * 						AND this.forward().getCol() == this.getCol()
     * 
     * post: mob.getFace() == E \implies EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol(),this.getRow()-1) != DWO
     * 						OR this.getRow()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol(),this.getRow()-1) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()     
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == EMP 
     * 							OR EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) == DWO
     * 						AND this.getCol()-1 >= 0
     * 						AND EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) == No
     * 						\implies this.forward().getRow() == this.getRow() 
     * 						AND this.forward().getCol() == this.getCol() - 1
     * 
     * post: mob.getFace() == S \implies EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != EMP 
     * 							AND EnvironmentService.cellNature(this.getEnv(),this.getCol()-1,this.getRow()) != DWO
     * 						OR this.getCol()-1 < 0
     * 						OR EnvironmentService.cellContent(this.getEnv(),this.getCol()-1,this.getRow()) != No
     * 						\implies this.forward().getRow() == this.getRow()
     * 						AND this.forward().getCol() == this.getCol()
     */
    public void strafeR();
    
}
