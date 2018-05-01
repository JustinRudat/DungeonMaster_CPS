package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class MobImplem implements MobService{
	private EnvironmentService env;
	private int col;
	private int row;
	private Dir dir;

	
	

	public boolean setFace(Dir dir) {
		this.dir = dir;
		return true;
	}

	public boolean setEnv(EnvironmentService env) {
		this.env = env;
		return true;
	}

	public boolean setCol(int col) {
		this.col = col;
		return true;
	}

	public boolean setRow(int row) {
		this.row = row;
		return true;
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
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		this.env = env;
		this.col = x;
		this.row = y;
		this.dir = dir;
		this.env.addMobOption(this);
		return true;
	}

	@Override
	public boolean forward() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case N: 
					if(this.getRow()+1<this.getEnv().getHeight()) {
						if(this.getCol()<this.getEnv().getWidth()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()+1< this.getEnv().getWidth()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()-1<this.getEnv().getHeight()) {
						if(this.getCol()>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
		return true;
	}

	@Override
	public boolean backward() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case S: 
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()+1<this.getEnv().getHeight()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()+1< this.getEnv().getWidth()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
								if(this.getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()==Option.No) {
									int x = this.getCol();
									int y = this.getRow();
									OptionService<MobService> vode = new OptionImplem<>();
									OptionService<MobService> vode_mvt = new OptionImplem<>();
									vode.init(this, Option.So);
									vode_mvt.init(null,Option.No);
									this.getEnv().getContent().set((y-1)*this.getEnv().getWidth()+x,vode);
									this.getEnv().getContent().set(y*this.getEnv().getWidth()+x,vode_mvt);
									this.setRow(this.getRow()-1);
								}
							}
						}
					}
					
					break;
				case E:
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
		return true;
	}

	@Override
	public boolean turnL() {
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
		return true;
	}

	@Override
	public boolean turnR() {
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
		return true;
	}

	@Override
	public boolean strafeL() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case E: 
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()+1<this.getEnv().getHeight()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()+1< this.getEnv().getWidth()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
		return true;
	}

	@Override
	public boolean strafeR() {
		Cell nature;
		if(this.getCol()>=0 && this.getRow()>=0) {
			switch(this.getFace()) {
				case W: 
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()+1<this.getEnv().getHeight()) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()+1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()+1< this.getEnv().getWidth()) {
							nature = this.getEnv().cellNature(this.getCol()+1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getCol()<this.getEnv().getWidth()) {
						if(this.getRow()-1>=0) {	
							nature = this.getEnv().cellNature(this.getCol(),this.getRow()-1);
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
					if(this.getRow()<this.getEnv().getHeight()) {	
						if(this.getCol()-1>=0) {
							nature = this.getEnv().cellNature(this.getCol()-1,this.getRow());
							if(nature==Cell.EMP || nature==Cell.DWO ||nature ==Cell.OUT||nature ==Cell.IN||nature==Cell.DNO) {
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
		return true;
	}

}
