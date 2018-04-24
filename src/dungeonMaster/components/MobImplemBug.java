package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class MobImplemBug implements MobService{
	private EnvironmentService env;
	private int col;
	private int row;
	private Dir dir;

	
	public Dir getDir() {
		return this.dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setEnv(EnvironmentService env) {
		this.env = env;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y+1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x+1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y-1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x-1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y+1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x+1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y-1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x-1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y+1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x+1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y-1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x-1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()+1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y+1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()+1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x+1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y-1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
								if(this.getEnv().cellContent(this.getCol()-1,this.getRow()).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x-1,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
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
