package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class PlayerImplem extends EntityImplem implements PlayerService {
	private OptionService<Command> lastcommand;
	private ArrayList<LootService> bag;

	@Override
	public OptionService<Command> getLastCommand() {
		return this.lastcommand;
	}

	@Override
	public OptionService<MobService> getContent(int x, int y) {
		
			switch(this.getFace()) {
				case N:
					return this.getEnv().cellContent(this.getCol()+x,this.getRow()+ y);
				case S:
					return this.getEnv().cellContent(this.getCol()-x,this.getRow()- y);
				case E:
					return this.getEnv().cellContent(this.getCol()+y,this.getRow()- x);
				case W:
					return this.getEnv().cellContent(this.getCol()-y,this.getRow()+ x);
				default:
					break;
			}
		
		return null;
	}
	
	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg, int armor) {
		boolean retour = super.init(env, x, y, dir, hp, dmg, armor);
		OptionImplem<Command> opt = new OptionImplem();
		opt.init(null,Option.No);
		this.lastcommand = opt;
		this.bag = new ArrayList<>();
		return retour;
	}

	@Override
	public Cell getNature(int x, int y) {
		
			switch(this.getFace()) {
				case N:
					return this.getEnv().cellNature(this.getCol()+x,this.getRow() + y);
				case S:
					return this.getEnv().cellNature(this.getCol()+x,this.getRow() - y);
				case E:
					return this.getEnv().cellNature(this.getCol()+y,this.getRow() + x);
				case W:
					return this.getEnv().cellNature(this.getCol()-y,this.getRow() + x);
				default:
					break;
			}
		
		return null;
	}

	@Override
	public Cell getViewable(int x, int y) {
		if(x<=1 && x>=-1) {
			if(y==0||y==1||y==-1) {
				return this.getNature(x, y);
			}
			if(y==2) {
				if(this.getNature(0, y-1)!=Cell.WLL&&this.getNature(0, y-1)!=Cell.DNC&&this.getNature(0, y-1)!=Cell.DWC) {
					return this.getNature(x, y);
				}
			}else
			if(y==4) {
				if(x==0) {
					if(this.getNature(x, y-1)!=Cell.WLL&&this.getNature(x, y-1)!=Cell.DNC&&this.getNature(x, y-1)!=Cell.DWC) {
						if(this.getNature(x, y-2)!=Cell.WLL&&this.getNature(x, y-2)!=Cell.DNC&&this.getNature(x, y-2)!=Cell.DWC) {
							if(this.getNature(x, y-3)!=Cell.WLL&&this.getNature(x, y-3)!=Cell.DNC&&this.getNature(x, y-3)!=Cell.DWC) {
								return this.getNature(x, y);
							}
						}
					}
				}
			}else if(y==3) {
				if(this.getViewable(x, y-1)!=null) {
					if(this.getNature(x, y-1)!=Cell.WLL&&this.getNature(x, y-1)!=Cell.DNC&&this.getNature(x, y-1)!=Cell.DWC) {
						return this.getNature(x, y);
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean step() {
		OptionService<Command> commands = this.getLastCommand();
		if(commands.getOption()!=Option.No) {
			switch(commands.getElem()) {
				case FF:
					this.forward();
					break;
				case BB : 
					this.backward();
					break;
				case LL:
					this.strafeL();
					break;
				case RR:
					this.strafeR();
					break;
				case TL:
					this.turnL();
					break;
				case TR:
					this.turnR();
					break;
				case AA:
					this.attack();
					break;
				case TK:
					this.take();
					break;
				
				default:
					break;
			}
			commands.setOption(Option.No);
			commands.setElem(null);
		}
		return true;
	}

	@Override
	public boolean setLastCommand(Command command) {
		OptionImplem<Command> cmd = new OptionImplem<>();
		cmd.setElem(command);
		this.lastcommand = cmd;
		return true;
	}

	@Override
	public ArrayList<LootService> getBag() {
		return this.bag;
	}

	@Override
	public LootService getBagAt(int index) {
		return this.bag.get(index);
	}

	@Override
	public boolean addLoot(LootService lt) {
		
		switch(lt.getLootType()) {
			case Treasure:
				this.bag.add(0,lt);
				return true;
				
			case Armor:
				this.setArmor(this.getArmor()+lt.getPuis());
				break;
				
			case Weapon:
				this.setDegats(this.getDegats()+lt.getPuis());
				break;
				
			case Potion:
				this.setHealthPoints(this.getHealthPoints()+lt.getPuis());
				break;
				
			default:
				break;
		}
		this.bag.add(lt);
		
		return true;
	}

	@Override
	public boolean take() {
		OptionService<MobService> opt=null;
		Cell nat=null;
		switch(this.getFace()) {
			case N:
				nat = this.getEnv().cellNature(this.getCol(), this.getRow()+1);
				if(nat == Cell.DNC) {
					this.getEnv().getPlateau()[this.getCol()][this.getRow()+1]=Cell.DNO;
				}
				if(nat == Cell.DNO) {
					this.getEnv().getPlateau()[this.getCol()][this.getRow()+1]=Cell.DNC;
				}
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()+1);
				if(opt.getOption()!=Option.No) {
					if(opt.getElem() instanceof LootService) {
						this.addLoot((LootService) opt.getElem());
						opt.setOption(Option.No);
						opt.setElem(null);
					}
				}
				break;
			case S:
				nat = this.getEnv().cellNature(this.getCol(), this.getRow()-1);
				if(nat == Cell.DNC) {
					this.getEnv().getPlateau()[this.getCol()][this.getRow()-1]=Cell.DNO;
				}
				if(nat == Cell.DNO) {
					this.getEnv().getPlateau()[this.getCol()][this.getRow()-1]=Cell.DNC;
				}
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()-1);
				if(opt.getOption()!=Option.No) {
					if(opt.getElem() instanceof LootService) {
						this.addLoot((LootService) opt.getElem());
						opt.setOption(Option.No);
						opt.setElem(null);
					}
				}
				break;
			case E:
				nat = this.getEnv().cellNature(this.getCol()+1, this.getRow());
				if(nat == Cell.DWC) {
					this.getEnv().getPlateau()[this.getCol()+1][this.getRow()]=Cell.DWO;
				}
				if(nat == Cell.DWO) {
					this.getEnv().getPlateau()[this.getCol()+1][this.getRow()]=Cell.DWC;
				}
				opt = this.getEnv().cellContent(this.getCol()+1, this.getRow());
				if(opt.getOption()!=Option.No) {
					if(opt.getElem() instanceof LootService) {
						this.addLoot((LootService) opt.getElem());
						opt.setOption(Option.No);
						opt.setElem(null);
					}
				}
				break;
			case W:
				nat = this.getEnv().cellNature(this.getCol()-1, this.getRow());
				if(nat == Cell.DWC) {
					this.getEnv().getPlateau()[this.getCol()-1][this.getRow()]=Cell.DWO;
				}
				if(nat == Cell.DWO) {
					this.getEnv().getPlateau()[this.getCol()-1][this.getRow()]=Cell.DWC;
				}
				opt = this.getEnv().cellContent(this.getCol()-1, this.getRow());
				if(opt.getOption()!=Option.No) {
					if(opt.getElem() instanceof LootService) {
						this.addLoot((LootService) opt.getElem());
						opt.setOption(Option.No);
						opt.setElem(null);
					}
				}
				break;
			default:
				break;
		}
		
		return true;
	}
	
}
