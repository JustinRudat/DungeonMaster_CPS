package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Opt;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class MinotaurImplemBug extends MonsterImplem  implements MinotaurService {
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		super.init(env, x, y, dir);
		this.setArmor(2);
		this.setDegats(4);
		this.setDropChance(50);
		this.setHealthPoints(15);
		this.setMentRes(35);
		this.setPortee(4);
		return true;
	}

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
		if(opt.getOption()==Opt.So) {
			if(opt.getElem() instanceof EntityService) {
				EntityService victim = (EntityService)opt.getElem();
				if(victim instanceof PlayerService) {
					if(this.getDegats()-victim.getArmor()>0) {
						int degat=this.getDegats()-victim.getArmor()-(((PlayerService)victim).isDef()?1:0);
						if(degat>0) {
							int col_pre = victim.getCol();
							int row_pre = victim.getRow();
							victim.setHealthPoints(victim.getHealthPoints()-degat);
							
						
						}
					}
				}
			}
		}
		return true;
	}
}
