package dungeonMaster.tests;

import dungeonMaster.components.CowImplem;
import dungeonMaster.components.CowImplemBug;
import dungeonMaster.components.EntityImplem;
import dungeonMaster.components.EntityImplemBug;
import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.components.MobImplem;
import dungeonMaster.components.MobImplemBug;
import dungeonMaster.components.PlayerImplem;
import dungeonMaster.components.PlayerImplemBug;
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
	
	protected int x,y,hp,dmg;
	protected Dir dir;
	protected EnvironmentService env;
	
	protected void setUp(){
	     this.x = 2 ;
	     this.y = 2;
	     this.hp = 5;
	     this.dmg = 1;
	     this.dir = Dir.N;
	     this.env = new EnvironmentImplem();
	     this.env.init(70,50);
	}
	
	public void testInitMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		assertTrue(mob.init(env, x, y, dir));
	}
	
	public void testBackwardMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.backward());
	}
	
	public void testForwardMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.forward());
	}
	
	public void testStrafeRMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.strafeR());
	}
	
	public void testStrafeLMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.strafeL());
	}
	
	public void testTurnRMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.turnR());
	}
	
	public void testTurnLMob() {
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertTrue(mob.turnL());
	}
	
	public void testBackwardMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.backward());
	}
	
	public void testForwardMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.forward());
	}
	
	public void testStrafeRMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.strafeR());
	}
	
	public void testStrafeLMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.strafeL());
	}
	
	public void testTurnRMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.turnR());
	}
	
	public void testTurnLMobBug() {
		MobContract mob = new MobContract(new MobImplemBug());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.turnL());
	}
	
	public void testEntityInit() {
		EntityContract entity = new EntityContract(new EntityImplem());
		
		assertTrue(entity.init(env, x, y, dir, hp , dmg));
		
		
	}
	
	public void testEntityInitBug() {
		EntityContract entity = new EntityContract(new EntityImplemBug());
		
		assertFalse(entity.init(env, x, y, dir, -3 , -1));
		
		
	}
	
	public void testCowImplem() {
		CowContract cow  = new CowContract(new CowImplem());
		
		assertTrue(cow.init(env, x, y, dir, 4,dmg));
		
		assertTrue(cow.step());
		
		assertTrue(cow.attack());
		
	}
	
	public void testCowImplemBug() {
		CowContract cow  = new CowContract(new CowImplemBug());
		
		assertFalse(cow.init(env, x, y, dir, hp,dmg));
		
		
		
	}
	
	public void testPlayerImplem() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg);
		
		player.setLastCommand(Command.BB);
		
		assertTrue(player.step());
		
//		player.setLastCommand(Command.AA);
//		
//		assertTrue(player.step());
		
		player.setLastCommand(Command.FF);
		
		assertTrue(player.step());
		
		player.setLastCommand(Command.LL);
		
		assertTrue(player.step());
		
		player.setLastCommand(Command.RR);
		
		assertTrue(player.step());
		
		player.setLastCommand(Command.TL);
		
		assertTrue(player.step());
		
		player.setLastCommand(Command.TR);
		
		assertTrue(player.step());
		
		
		
	}
	
	public void testPlayerImplemBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg);
		
		player.setLastCommand(Command.BB);
		
		assertFalse(player.step());
		
		
		
		player.setLastCommand(Command.FF);
		
		assertFalse(player.step());
		
		player.setLastCommand(Command.LL);
		
		assertFalse(player.step());
		
		player.setLastCommand(Command.RR);
		
		assertFalse(player.step());
		
		player.setLastCommand(Command.TL);
		
		assertFalse(player.step());
		
		player.setLastCommand(Command.TR);
		
		assertFalse(player.step());
		
//		player.setLastCommand(Command.AA);
//		
//		assertFalse(player.step());
		
		
		
	}
	
}
