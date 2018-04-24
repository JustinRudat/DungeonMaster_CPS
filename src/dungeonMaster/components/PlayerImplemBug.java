package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class PlayerImplemBug extends EntityImplem implements PlayerService {
	OptionService<Command> lastcommand;

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
	public Cell getNature(int x, int y) {
		
			switch(this.getFace()) {
				case N:
					return this.getEnv().cellNature(this.getCol()+x,this.getRow()+ y);
				case S:
					return this.getEnv().cellNature(this.getCol()-x,this.getRow()- y);
				case E:
					return this.getEnv().cellNature(this.getCol()+y,this.getRow()- x);
				case W:
					return this.getEnv().cellNature(this.getCol()-y,this.getRow()+ x);
				default:
					break;
			}
		
		return null;
	}

	@Override
	public Cell getViewable(int x, int y) {
		if(x<=1 && x>=-1) {
			if(y==2) {
				if(this.getNature(x, y-1)!=Cell.WLL&&this.getNature(x, y-1)!=Cell.DNC&&this.getNature(x, y-1)!=Cell.DWC) {
					return this.getNature(x, y);
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
	public void step() {
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
				
				default:
					break;
			}
			commands.setOption(Option.No);
			commands.setElem(null);
		}
		
	}
	
}
