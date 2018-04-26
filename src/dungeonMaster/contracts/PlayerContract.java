package dungeonMaster.contracts;

import dungeonMaster.decorators.PlayerDecorator;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.InvariantException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.Cell;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
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
		// TODO Auto-generated method stub
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
		return super.step();

	}

	@Override
	public int getHealthPoints() {
		return super.getHealthPoints();
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp,int degat) {
		return super.init(env, x, y, dir, hp,degat);
	}

	@Override
	public EnvironmentService getEnv() {
		return super.getEnv();
	}

	@Override
	public int getCol() {
		return super.getCol();
	}

	@Override
	public int getRow() {
		return super.getRow();
	}

	@Override
	public Dir getFace() {
		return super.getFace();
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		return super.init(env, x, y, dir);

	}

	@Override
	public boolean forward() {
		checkInvariants();
		boolean retour = super.forward();
		checkInvariants();
		return retour;
	}

	@Override
	public boolean backward() {
		checkInvariants();
		boolean retour = super.backward();
		checkInvariants();
		return retour;
	}

	@Override
	public boolean turnL() {
		checkInvariants();
		boolean retour = super.turnL();
		checkInvariants();
		return retour;
	}

	@Override
	public boolean turnR() {
		checkInvariants();
		boolean retour = super.turnR();
		checkInvariants();
		return retour;
	}

	@Override
	public boolean strafeL() {
		checkInvariants();
		boolean retour = super.strafeL();
		checkInvariants();
		return retour;
	}

	@Override
	public boolean strafeR() {
		checkInvariants();
		boolean retour = super.strafeR();
		checkInvariants();
		return retour;
	}

}
