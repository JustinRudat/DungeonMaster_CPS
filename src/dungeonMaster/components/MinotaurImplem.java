package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EntityService;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MinotaurService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class MinotaurImplem extends MonsterImplem  implements MinotaurService {
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
		if(opt.getOption()==Option.So) {
			if(opt.getElem() instanceof EntityService) {
				EntityService victim = (EntityService)opt.getElem();
				if(victim instanceof PlayerService) {
					if(this.getDegats()-victim.getArmor()>0) {
						int degat=this.getDegats()-victim.getArmor()-(((PlayerService)victim).isDef()?1:0);
						if(degat>0) {
							int col_pre = victim.getCol();
							int row_pre = victim.getRow();
							victim.setHealthPoints(victim.getHealthPoints()-degat);
							switch(victim.getFace()) {
							case N:
								switch(this.getFace()) {
								case N:
									victim.forward();
								case S:
									victim.backward();
								case E:
									victim.strafeL();
								case W:
									victim.strafeR();
									default:
										break;
								}
							case S:
								switch(this.getFace()) {
								case N:
									victim.backward();
								case S:
									victim.forward();
								case E:
									victim.strafeR();
								case W:
									victim.strafeL();
									default:
										break;
								}
							case E:
								switch(this.getFace()) {
								case N:
									victim.strafeR();
								case S:
									victim.strafeL();
								case E:
									victim.forward();
								case W:
									victim.backward();
									default:
										break;
								}
							case W:
								switch(this.getFace()) {
								case N:
									victim.strafeL();
								case S:
									victim.strafeR();
								case E:
									victim.backward();
								case W:
									victim.forward();
									default:
										break;
								}
								default:
									break;
							}
							victim.backward();
							if(col_pre == victim.getCol() && row_pre == victim.getRow()) {
								int dgt_tmp = this.getDegats()-victim.getArmor();
								if(dgt_tmp>0) {
									victim.setHealthPoints(victim.getHealthPoints()-dgt_tmp);
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
}
