package dungeonMaster.components;

import dungeonMaster.enumeration.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.GobelinService;

public class GobelinImplem extends MonsterImplem implements GobelinService {
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		super.init(env, x, y, dir);
		this.setArmor(1);
		this.setDegats(2);
		this.setDropChance(30);
		this.setHealthPoints(10);
		this.setMentRes(15);
		this.setPortee(3);
		
		return true;
	}
	
	
}
