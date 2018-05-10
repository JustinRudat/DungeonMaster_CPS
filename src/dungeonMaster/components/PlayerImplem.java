package dungeonMaster.components;

import java.util.ArrayList;
import java.util.Random;

import dungeonMaster.services.Cell;
import dungeonMaster.services.ColorKeyDoor;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.LootType;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class PlayerImplem extends EntityImplem implements PlayerService {
	private OptionService<Command> lastcommand;
	private ArrayList<LootService> bag;
	private ArrayList<LootService> keys;
	private int pacification;
	private boolean defense;

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
		this.keys=new ArrayList<>();
		this.defense = false;
		this.pacification = 40;
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

	public Cell isViewable(int col, int row) {
		System.out.println("I'm called");
		int x = col - this.getCol();
		int y = row -  this.getRow();
		// Move camera
		switch (getFace()) {
			case W:
			    x += 2;
			    break;
			case E:
			    x -= 2;
			    break;
            case N:
                y -= 2;
                break;
            case S:
                y += 2;
                break;
            default:
                System.out.println(getFace());
		}

		if (Math.abs(x) < 4 && Math.abs(y) < 4)
			return getEnv().cellNature(col, row);
		else return null;
	}
	
	@Override
	public Cell getViewable(int x, int y) {
		if(x<=1 && x>=-1) {
			if(y==0||y==1||y==-1) {
				return this.getNature(x, y);
			}
			if(y==2) {
				if(this.getNature(0, y-1)!=Cell.WLL&&this.getNature(0, y-1)!=Cell.DNC&&this.getNature(0, y-1)!=Cell.DWC&&this.getNature(0, y-1)!=Cell.DWL&&this.getNature(0, y-1)!=Cell.DNL) {
					return this.getNature(x, y);
				}
			}else
			if(y==4) {
				if(x==0) {
					if(this.getNature(x, y-1)!=Cell.WLL&&this.getNature(x, y-1)!=Cell.DNC&&this.getNature(x, y-1)!=Cell.DWC&&this.getNature(0, y-1)!=Cell.DWL&&this.getNature(0, y-1)!=Cell.DNL) {
						if(this.getNature(x, y-2)!=Cell.WLL&&this.getNature(x, y-2)!=Cell.DNC&&this.getNature(x, y-2)!=Cell.DWC&&this.getNature(0, y-2)!=Cell.DWL&&this.getNature(0, y-2)!=Cell.DNL) {
							if(this.getNature(x, y-3)!=Cell.WLL&&this.getNature(x, y-3)!=Cell.DNC&&this.getNature(x, y-3)!=Cell.DWC&&this.getNature(0, y-3)!=Cell.DWL&&this.getNature(0, y-3)!=Cell.DNL) {
								return this.getNature(x, y);
							}
						}
					}
				}
			}else if(y==3) {
				if(this.getViewable(x, y-1)!=null) {
					if(this.getNature(x, y-1)!=Cell.WLL&&this.getNature(x, y-1)!=Cell.DNC&&this.getNature(x, y-1)!=Cell.DWC&&this.getNature(x, y-1)!=Cell.DWL&&this.getNature(x, y-1)!=Cell.DNL) {
						if(this.getNature(x, y-2)!=Cell.WLL&&this.getNature(x, y-2)!=Cell.DNC&&this.getNature(x, y-2)!=Cell.DWC&&this.getNature(x, y-2)!=Cell.DWL&&this.getNature(x, y-2)!=Cell.DNL) {
							return this.getNature(x, y);
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean step() {
		if(this.isDef()) {
			this.defense = false;
		}
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
				case DF:
					this.defense = true;
					break;
				case PF :
					this.pacify();
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
		int x=-1;
		int y =-1;
		switch(this.getFace()) {
			case N:
				x=this.getCol();
				y = this.getRow()+1;
				
				break;
			case S:
				x=this.getCol();
				y = this.getRow()-1;
				
				break;
			case E:
				x=this.getCol()+1;
				y = this.getRow();
				
				break;
			case W:
				x=this.getCol()-1;
				y = this.getRow();
				
				break;
			
		}
		nat = this.getEnv().cellNature(x, y);
		opt = this.getEnv().cellContent(x, y);
		if(opt.getOption()!=Option.No) {
			if(opt.getElem() instanceof LootService) {
				if(((LootService)opt.getElem()).getLootType()==LootType.Key) {
					this.addKey((LootService) opt.getElem());
				}else {
					this.addLoot((LootService) opt.getElem());
				}
				opt.setOption(Option.No);
				opt.setElem(null);
			}
		}else if(nat == Cell.DNC) {
			this.getEnv().getPlateau()[x][y]=Cell.DNO;
		}else if(nat == Cell.DNO) {
			this.getEnv().getPlateau()[x][y]=Cell.DNC;
		}else if(nat ==Cell.DWC) {
			this.getEnv().getPlateau()[x][y]=Cell.DWO;
		}else if(nat ==Cell.DWO) {
			this.getEnv().getPlateau()[x][y]=Cell.DWC;
		}else if(nat == Cell.DNL) {
			DoorLockService door = null;
			for(DoorLockService door_tmp : this.getEnv().getDoorLocked()) {
				if(door_tmp.getCol()==x && door_tmp.getRow()==y) {
					door = door_tmp;
					break;
				}
			}
			if(door == null) {
				return false;
			}
			for(int i=0; i < this.getKeys().size();i++) {
				if(this.getKeys().get(i).getName().equals(door.getColor())) {
					this.getEnv().getPlateau()[x][y]=Cell.DNO;
					this.getKeys().remove(i);
					this.getEnv().getDoorLocked().remove(door);
					break;
				}
			}
		}else if(nat == Cell.DWL) {
			DoorLockService door = null;
			for(DoorLockService door_tmp : this.getEnv().getDoorLocked()) {
				if(door_tmp.getCol()==x && door_tmp.getRow()==y) {
					door = door_tmp;
					break;
				}
			}
			if(door == null) {
				return false;
			}
			for(int i=0; i < this.getKeys().size();i++) {
				if(this.getKeys().get(i).getName().equals(door.getColor())) {
					this.getEnv().getPlateau()[x][y]=Cell.DWO;
					this.getKeys().remove(i);
					this.getEnv().getDoorLocked().remove(door);
					break;
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<LootService> getKeys() {
		return this.keys;
	}

	@Override
	public boolean addKey(LootService lt) {
		return keys.add(lt);
	}

	@Override
	public int getPacification() {
		return this.pacification;
	}

	@Override
	public boolean isDef() {
		return this.defense;
	}

	@Override
	public boolean pacify() {
		MonsterService monstre = null;
		OptionService<MobService> content = null;
		switch(this.getFace()) {
			case N:
				content = this.getContent(0, 1);
				break;
			case S :
				content = this.getContent(0, -1);
				break;
			case E: 
				content = this.getContent(1, 0);
				break;
			case W:
				content = this.getContent(-1, 0);
				break;
			default:
				break;
		}
		if(content.getOption()==Option.So) {
			if(content.getElem() instanceof MonsterService) {
				monstre = (MonsterService) content.getElem();
			}
		}
		Random rand = new Random();
		int alea = rand.nextInt(100)+1;
		if(monstre!=null) {
			if(alea<= this.getPacification() - monstre.getMentRes()) {
				monstre.setHealthPoints(0);
				monstre.setDropChance(100);
				this.pacification+=5;
			}else {
				monstre.setMentRes(monstre.getMentRes()-5);
			}
		}
		return true;
	}
	
}
