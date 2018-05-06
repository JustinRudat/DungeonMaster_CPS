package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class EntityImplem extends MobImplem implements EntityService{
	private int healthPoints;
	private int degats;
	private int armor;
	
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
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg,int armor) {
		super.init(env,x,y,dir);
		this.healthPoints = hp;
		this.degats = dmg;
		this.armor = armor;
		return true;
		
	}

	
	
	@Override
	public boolean attack() {
		OptionService<MobService> opt = null;
		int x =-1;
		int y =-1;
		switch(this.getFace()) {
			case N:
				x = this.getCol();
				y = this.getRow()+1;
				break;
			case S:
				x = this.getCol();
				y = this.getRow()-1;
				break;
			case W:
				x = this.getCol()-1;
				y = this.getRow();
				break;
			case E:
				x = this.getCol()+1;
				y = this.getRow();
				break;
			default: 
				break;
		}
		opt = this.getEnv().cellContent(x,y);
		if(opt.getOption()==Option.So) {
			if(opt.getElem() instanceof EntityService) {
				EntityService victim = (EntityService)opt.getElem();
				if(victim instanceof PlayerService) {
					if(this.getDegats()-victim.getArmor()>0) {
						int degat=this.getDegats()-victim.getArmor()-(((PlayerService)victim).isDef()?1:0);
						if(degat>0) {
							victim.setHealthPoints(victim.getHealthPoints()-degat);
						}
					}
				}else {
					if(this.getDegats()-victim.getArmor()>0) {
						int degat = this.getDegats() - victim.getArmor();
						if(degat>0) {
							victim.setHealthPoints(victim.getHealthPoints()-degat);
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean step() {
		return false;
	}

	@Override
	public boolean setArmor(int armor) {
		this.armor= armor;
		return true;
	}

	@Override
	public int getArmor() {
		return this.armor;
	}

	
	

}
