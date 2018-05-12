package dungeonMaster.components;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.GobelinService;

public class GobelinImplemBug extends MonsterImplem implements GobelinService {
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		super.init(env, x, y, dir);
		this.setArmor(2);
		this.setDegats(5);
		this.setDropChance(20);
		this.setHealthPoints(11);
		this.setMentRes(13);
		this.setPortee(8);
		
		return true;
	}
	
	
}
