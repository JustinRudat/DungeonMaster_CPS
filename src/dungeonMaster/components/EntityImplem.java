package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class EntityImplem extends MobImplem implements EntityService{
	private int healthPoints;
	private int degats;
	
	public int getDegats() {
		return degats;
	}

	public boolean setDegats(int degats) {
		this.degats = degats;
		return true;
	}

	public boolean setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
		return true;
	}

	public int getHealthPoints() {
		return this.healthPoints;
		
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg) {
		super.init(env,x,y,dir);
		this.healthPoints = hp;
		this.degats = dmg;
		return true;
		
	}

	
	
	@Override
	public boolean attack() {
		OptionService<MobService> opt = null;
		switch(this.getFace()) {
			case N:
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()+1);
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case S:
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()-1);
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case W:
				opt = this.getEnv().cellContent(this.getCol()-1, this.getRow());
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case E:
				opt = this.getEnv().cellContent(this.getCol()+1, this.getRow());
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			default: 
				break;
		}
		return true;
	}

	@Override
	public boolean step() {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
