package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;

public class MobImplem implements MobService{
	private EnvironmentService env;
	private int col;
	private int row;
	private Dir dir;

	
	@Override
	public EnvironmentService getEnv() {
		return this.env;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public Dir getFace() {
		return this.dir;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir) {
		this.env = env;
		this.col = x;
		this.row = y;
		this.dir = dir;
	}

	@Override
	public void forward() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case N: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()+1<this.getEnv().getWidth()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1)==Option.No) {
									this.row++;
								}
							}
						}
					}
					
				
					break;
				case E:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()+1< this.getEnv().getHeight()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow())==Option.No) {
									this.col++;
								}
							}
						}
					}
					
					break;
				case S: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1)==Option.No) {
									this.row--;
								}
							}
						}
					}
					
					break;
				case W:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow())==Option.No) {
									this.col--;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void backward() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case S: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()+1<this.getEnv().getWidth()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1)==Option.No) {
									this.row++;
								}
							}
						}
					}
					
				
					break;
				case W:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()+1< this.getEnv().getHeight()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow())==Option.No) {
									this.col++;
								}
							}
						}
					}
					
					break;
				case N: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1)==Option.No) {
									this.row--;
								}
							}
						}
					}
					
					break;
				case E:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow())==Option.No) {
									this.col--;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
	}

	@Override
	public void turnL() {
		switch(this.getFace()) {
			case N:
				this.dir = Dir.W;
				break;
			case W:
				this.dir = Dir.S;
				break;
			case S:
				this.dir = Dir.E;
				break;
			case E:
				this.dir = Dir.N;
				break;
			default:
				break;
		}
		
	}

	@Override
	public void turnR() {
		switch(this.getFace()) {
			case S:
				this.dir = Dir.W;
				break;
			case E:
				this.dir = Dir.S;
				break;
			case N:
				this.dir = Dir.E;
				break;
			case W:
				this.dir = Dir.N;
				break;
			default:
				break;
		}
		
	}

	@Override
	public void strafeL() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case E: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()+1<this.getEnv().getWidth()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1)==Option.No) {
									this.row++;
								}
							}
						}
					}
					
				
					break;
				case S:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()+1< this.getEnv().getHeight()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow())==Option.No) {
									this.col++;
								}
							}
						}
					}
					
					break;
				case W: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1)==Option.No) {
									this.row--;
								}
							}
						}
					}
					
					break;
				case N:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow())==Option.No) {
									this.col--;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
	}

	@Override
	public void strafeR() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case W: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()+1<this.getEnv().getWidth()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1)==Option.No) {
									this.row++;
								}
							}
						}
					}
					
				
					break;
				case N:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()+1< this.getEnv().getHeight()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow())==Option.No) {
									this.col++;
								}
							}
						}
					}
					
					break;
				case E: 
					if(this.getCol()<this.getEnv().getHeight()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1)==Option.No) {
									this.row--;
								}
							}
						}
					}
					
					break;
				case S:
					if(this.getRow()<this.getEnv().getWidth()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO) {
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow())==Option.No) {
									this.col--;
								}
							}
						}
					}
					break;
				default:
					break;
			}
		}
		
	}

}
