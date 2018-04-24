package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class EntityImplemBug extends MobImplem implements EntityService{
	private int healthPoints;
	private int degats;
	
	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg) {
		super.init(env,x,y,dir);
		this.healthPoints = hp;
		this.degats = dmg;
		
	}

	public void step() {
		// TODO Auto-generated method stub
		// Will be override by the implementation opf a specific entity ( ex : Player)
	}
	
	@Override
	public void attack() {
		OptionService<MobService> opt = null;
		switch(this.getFace()) {
			case S:
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()+1);
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case N:
				opt = this.getEnv().cellContent(this.getCol(), this.getRow()-1);
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case E:
				opt = this.getEnv().cellContent(this.getCol()-1, this.getRow());
				if(opt.getOption()==Option.So) {
					if(opt.getElem() instanceof EntityService) {
						EntityService victim = (EntityService)opt.getElem();
						victim.setHealthPoints(victim.getHealthPoints()-this.getDegats());
					}
				}
				break;
			case W:
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
		
	}

	
	

}
