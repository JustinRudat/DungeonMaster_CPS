package dungeonMaster.tests;

import dungeonMaster.components.CowImplem;
import dungeonMaster.components.CowImplemBug;
import dungeonMaster.components.EntityImplem;
import dungeonMaster.components.EntityImplemBug;
import dungeonMaster.components.MobImplem;
import dungeonMaster.components.MobImplemBug;
import dungeonMaster.components.PlayerImplem;
import dungeonMaster.contracts.CowContract;
import dungeonMaster.contracts.EntityContract;
import dungeonMaster.contracts.MobContract;
import dungeonMaster.contracts.PlayerContract;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import junit.framework.TestCase;

public class MobTest extends TestCase{
	public void testMobImplem(EnvironmentService env, int x, int y , Dir dir) {
		MobContract mob = new MobContract(new MobImplem());
		
		assertTrue(mob.init(env, x, y, dir));
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.backward());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertTrue(mob.forward());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertTrue(mob.strafeL());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertTrue(mob.strafeR());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertTrue(mob.turnL());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertTrue(mob.turnR());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 
		 
	}
	
	public void testMobImplemBug(EnvironmentService env, int x, int y , Dir dir) {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.backward());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertFalse(mob.forward());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertFalse(mob.strafeL());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertFalse(mob.strafeR());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertFalse(mob.turnL());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 assertFalse(mob.turnR());
		 
		 col = mob.getCol();
		 row = mob.getRow();
		 dir_tmp = mob.getFace();
		 System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		 
		 
		 
	}
	
	public void testEntityImplem(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		EntityContract entity = new EntityContract(new EntityImplem());
		
		entity.init(env, x, y, dir, hp , dmg);
		
		
	}
	
	public void testEntityImplemBug(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		EntityContract entity = new EntityContract(new EntityImplemBug());
		
		entity.init(env, x, y, dir, hp , dmg);
		
		
	}
	
	public void testCowImplem(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		CowContract cow  = new CowContract(new CowImplem());
		
		assertTrue(cow.init(env, x, y, dir, hp,dmg));
		
		assertTrue(cow.step());
		
		assertTrue(cow.attack());
		
	}
	
	public void testCowImplemBug(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		CowContract cow  = new CowContract(new CowImplemBug());
		
		assertTrue(cow.init(env, x, y, dir, hp,dmg));
		
		assertTrue(cow.step());
		
		assertTrue(cow.attack());
		
	}
	
	public void testPlayerImplem(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		assertTrue(player.init(env, x, y, dir, hp, dmg));
		
		assertTrue(player.setLastCommand(Command.BB));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.AA));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.FF));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.LL));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.RR));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.TL));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.TR));
		
		assertTrue(player.step());
		
		
		
	}
	
	public void testPlayerImplemBug(EnvironmentService env, int x, int y , Dir dir , int hp , int dmg) {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		assertTrue(player.init(env, x, y, dir, hp, dmg));
		
		assertTrue(player.setLastCommand(Command.BB));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.AA));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.FF));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.LL));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.RR));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.TL));
		
		assertTrue(player.step());
		
		assertTrue(player.setLastCommand(Command.TR));
		
		assertTrue(player.step());
		
		
		
	}
	
}
