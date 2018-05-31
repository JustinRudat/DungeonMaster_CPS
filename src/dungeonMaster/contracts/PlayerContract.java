package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.components.PlayerImplem;
import dungeonMaster.decorators.PlayerDecorator;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Command;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.LootType;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.InvariantException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(PlayerService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		try {
			switch(this.getFace()) {
				case N:
					for(int x=-1;x<1;x++) {
						for(int y=-1;y<3;y++ ) {
							if(this.getContent(x, y)!=this.getEnv().cellContent(this.getCol()+x, this.getRow()+y)) {
								throw new InvariantException("Player : face N, content and cellcontent dont match");
							}
							if(this.getNature(x, y)!=this.getEnv().cellNature(this.getCol()+x, this.getRow()+y)) {
								throw new InvariantException("Player : face N, nature and cellnature dont match");
							}
						}
					}
					break;
				case S:
					for(int x=-1;x<1;x++) {
						for(int y=-1;y<3;y++ ) {
							if(this.getContent(x, y)!=this.getEnv().cellContent(this.getCol()-x, this.getRow()-y)) {
								throw new InvariantException("Player : face S, content and cellcontent dont match");
							}
							if(this.getNature(x, y)!=this.getEnv().cellNature(this.getCol()-x, this.getRow()-y)) {
								throw new InvariantException("Player : face S, nature and cellnature dont match");
							}
						}
					}
					break;
				case E:
					for(int x=-1;x<1;x++) {
						for(int y=-1;y<3;y++ ) {
							if(this.getContent(x, y)!=this.getEnv().cellContent(this.getCol()+y, this.getRow()-x)) {
								throw new InvariantException("Player : face N, content and cellcontent dont match");
							}
							if(this.getNature(x, y)!=this.getEnv().cellNature(this.getCol()+y, this.getRow()-x)) {
								throw new InvariantException("Player : face N, nature and cellnature dont match");
							}
						}
					}
					break;
				case W:
					for(int x=-1;x<1;x++) {
						for(int y=-1;y<3;y++ ) {
							if(this.getContent(x, y)!=this.getEnv().cellContent(this.getCol()-y, this.getRow()+x)) {
								throw new InvariantException("Player : face N, content and cellcontent dont match");
							}
							if(this.getNature(x, y)!=this.getEnv().cellNature(this.getCol()-y, this.getRow()+x)) {
								throw new InvariantException("Player : face N, nature and cellnature dont match");
							}
						}
					}
					break;
				default:
					break;
			}
			
			for(int u=-1;u<1;u++) {
				for(int v =-1; v< 1;v++) {
					if(this.getViewable(u, v)!=null) {
						throw new InvariantException("you shouldnt be able to see the case around you");
					}
				}
			}
			if(this.getViewable(-1, 2) != null) {
				if(this.getNature(-1, 1)==Cell.WLL||this.getNature(-1, 1)==Cell.DWC||this.getNature(-1, 1)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
			if(this.getViewable(0, 2) != null) {
				if(this.getNature(0, 1)==Cell.WLL||this.getNature(0, 1)==Cell.DWC||this.getNature(0, 1)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
			if(this.getViewable(1, 2) != null) {
				if(this.getNature(1, 1)==Cell.WLL||this.getNature(1, 1)==Cell.DWC||this.getNature(1, 1)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
			if(this.getViewable(-1, 3) != null) {
				if(this.getNature(-1, 2)==null) {
					throw new InvariantException("you cant even see the case before this one");
				}
				if(this.getNature(-1, 2)==Cell.WLL||this.getNature(-1, 2)==Cell.DWC||this.getNature(-1, 2)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
			if(this.getViewable(0, 3) != null) {
				if(this.getNature(0, 2)==null) {
					throw new InvariantException("you cant even see the case before this one");
				}
				if(this.getNature(0, 2)==Cell.WLL||this.getNature(0, 2)==Cell.DWC||this.getNature(0, 2)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
			if(this.getViewable(-1, 3) != null) {
				if(this.getNature(1, 2)==null) {
					throw new InvariantException("you cant even see the case before this one");
				}
				if(this.getNature(1, 2)==Cell.WLL||this.getNature(1, 2)==Cell.DWC||this.getNature(1, 2)==Cell.DNC) {
					throw new InvariantException("you should be able to see through wall and closed door");
				}
			}
		}catch(InvariantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OptionService<Command> getLastCommand() {
		return super.getLastCommand();
	}

	@Override
	public OptionService<MobService> getContent(int x, int y) {
		try {
			if(x>1||x<-1) {
				throw new PreConditionException("Player : getContent, exeption on x");
			}
			if(y>3||y<-1) {
				throw new PreConditionException("Player : getContent, exeption on y");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
		}
		return super.getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		try {
			if(x>1||x<-1) {
				throw new PreConditionException("Player : getNature, exeption on x");
			}
			if(y>3||y<-1) {
				throw new PreConditionException("Player : getNature, exeption on y");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
		}
		return super.getNature(x, y);
	}

	@Override
	public Cell getViewable(int x, int y) {
		try {
			if(x>1||x<-1) {
				throw new PreConditionException("Player : getViewable, exeption on x");
			}
			if(y>3||y<-1) {
				throw new PreConditionException("Player : getViewable, exeption on y");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
		}
		return super.getViewable(x, y);
	}

	@Override
	public boolean step() {
		try {
			if(this.getFace()==null) {
				throw new PreConditionException("no command available");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
			return false;
		}
		PlayerService player_pre = new PlayerImplem();
		EnvironmentService env = new EnvironmentImplem();
		env.init(this.getEnv().getWidth(), this.getEnv().getHeight());
		player_pre.init(env, this.getCol(), this.getRow(), this.getFace(),this.getHealthPoints(),this.getDegats(),this.getArmor());
		Command c = this.getLastCommand().getElem();
		int pacif_at_pre  = this.getPacification();
		OptionService<MobService> mob_at_pre = null;
		int col = -1;
		int row = -1;
		switch (this.getFace()) {
			case N:
				col = this.getCol();
				row = this.getRow()+1;
				break;
			case S:
				col = this.getCol();
				row = this.getRow()-1;
				break;
			case E:
				col = this.getCol()+1;
				row = this.getRow();
				break;
			case W:
				col = this.getCol()-1;
				row = this.getRow();
				break;
			default :
				break;
		}
		mob_at_pre = getEnv().cellContent(col, row);
		
		boolean retour=  super.step();
		
		try {
			if(null!=c) {
				switch(c) {
				case FF:
					player_pre.forward();
					if(this.getRow()!=player_pre.getRow()) {
						throw new PostConditionException("erreur forward row : "+this.getRow()+" "+player_pre.getRow());
					}
					if(this.getCol()!=player_pre.getCol()) {
						throw new PostConditionException("erreur forward col : "+this.getCol()+" "+player_pre.getCol()); 
					}
					break;
				case BB :
					System.out.println(player_pre.getRow());
					player_pre.backward();
					System.out.println(player_pre.getRow());
					if(this.getRow()!=player_pre.getRow()) {
						throw new PostConditionException("erreur backward row : "+this.getRow()+" "+player_pre.getRow());
					}
					if(this.getCol()!=player_pre.getCol()) {
						throw new PostConditionException("erreur backward col : "+this.getCol()+" "+player_pre.getCol()); 
					}
					break;
				case LL:
					player_pre.strafeL();
					if(this.getRow()!=player_pre.getRow()) {
						throw new PostConditionException("erreur strafeL  row : "+this.getRow()+" "+player_pre.getRow());
					}
					if(this.getCol()!=player_pre.getCol()) {
						throw new PostConditionException("erreur strafeL col : "+this.getCol()+" "+player_pre.getCol()); 
					}
					if(this.getFace()!=player_pre.getFace()) {
						throw new PostConditionException("erreur strafeL face : "+this.getFace()+" "+player_pre.getFace()); 
					}
					break;
				case RR:
					player_pre.strafeR();
					if(this.getRow()!=player_pre.getRow()) {
						throw new PostConditionException("erreur strafeR  row : "+this.getRow()+" "+player_pre.getRow());
					}
					if(this.getCol()!=player_pre.getCol()) {
						throw new PostConditionException("erreur strafeR col : "+this.getCol()+" "+player_pre.getCol()); 
					}
					if(this.getFace()!=player_pre.getFace()) {
						throw new PostConditionException("erreur strafeR face : "+this.getFace()+" "+player_pre.getFace()); 
					}
					break;
				case TL:
					
					player_pre.turnL();
					if(this.getFace()!=player_pre.getFace()) {
						throw new PostConditionException("erreur turnL");
					}
					break;
				case TR:
					player_pre.turnR();
					if(this.getFace()!=player_pre.getFace()) {
						throw new PostConditionException("erreur turnR");
					}
					break;
				case AA:
					//player_pre.attack();
					break;
				case DF:
					if(!this.isDef()) {
						throw new PostConditionException("step : player : player not on defensive");
					}
					break ;
				case PF :
					if(pacif_at_pre==this.getPacification()) {
						
					}
					//player_pre.pacify();
					break;
				case TK :
					//player_pre.take();
					break;
				default:
					break;
				}
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			return false;
		}
		return retour;
	}

	@Override
	public int getHealthPoints() {
		return super.getHealthPoints();
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int degat,int armor) {
		boolean retour =false;
		
		retour = super.init(env, x, y, dir, hp,degat,armor);
		
		try {
			if(this.getBag()==null) {
				throw new PostConditionException("init : player : bag not initialized");
			}
			if(this.getKeys()==null) {
				throw new PostConditionException("inti : player : keys not initialized");
			}
			if(this.getLastCommand()==null) {
				throw new PostConditionException("init : player : command not initialized");
			}
			if(this.isDef()) {
				throw new PostConditionException("init : player : should not be on the defensive yet");
			}
			if( this.getPacification() != 40 ) {
				throw new PostConditionException("init : player : pacification should be set at 40");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		
		return retour;
	}

	@Override
	public boolean addLoot(LootService lt) {
		boolean retour = false;
		int hp_at_pre = getHealthPoints();
		int armor_at_pre = getArmor();
		int damage_at_pre = getDegats();
		int lootsize = this.getBag().size();
		int keysize = this.getKeys().size();
		retour =  super.addLoot(lt);
		try {
			if(lt.getLootType()==LootType.Key) {
				if(keysize +1 != this.getKeys().size()) {
					throw new PostConditionException("key not added properly");
				}
			}
			if(lootsize+1 != this.getBag().size() && lt.getLootType()!=LootType.Key) {
				throw new PostConditionException("player : addLoot : error while adding loot.");
			}
			switch(lt.getLootType()) {
			case Potion : if(this.getHealthPoints() != hp_at_pre + lt.getPuis()) {
				throw new PostConditionException("potion not properly taken");
			}
			break;
			case Weapon : if(this.getDegats() != damage_at_pre + lt.getPuis()) {
				throw new PostConditionException("dammage not increased");
			}
			break;
			case Armor : if(this.getArmor()!= armor_at_pre+lt.getPuis()) {
				throw new PostConditionException("armor not increased");
			}
			break;
			case Treasure : 
				if(this.getBag().get(0).getLootType()!= LootType.Treasure) {
					throw new PostConditionException("treasure wasnt added to the inventory");
				}
				break;
			}
			
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		
		return retour;
	}
	
	@Override
	public boolean take() {
		return super.take();
	}

	

	@Override
	public boolean addKey(LootService lt) {
		boolean retour = false; 
		int keysize = this.getKeys().size();
		
		retour = super.addKey(lt);
		
		try {
			if(keysize+1 != this.getKeys().size()) {
				throw new PostConditionException("player : addKey : error while adding key.");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public boolean pacify() {
		return super.pacify();
	}

	@Override
	public Cell isViewable(int col, int row) {
		try {
			if(col>1||col<-1) {
				throw new PreConditionException("Player : getViewable, exeption on x");
			}
			if(row>3||row<-1) {
				throw new PreConditionException("Player : getViewable, exeption on y");
			}
		}catch(ConditionException e) {
			e.printStackTrace();
		}
		return super.isViewable(col, row);
	}
	
}
