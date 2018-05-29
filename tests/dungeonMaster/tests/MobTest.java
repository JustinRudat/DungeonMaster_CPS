package dungeonMaster.tests;

import dungeonMaster.components.CowImplem;
import dungeonMaster.components.CowImplemBug;
import dungeonMaster.components.EditMapImplem;
import dungeonMaster.components.EntityImplem;
import dungeonMaster.components.EntityImplemBug;
import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.components.GobelinImplem;
import dungeonMaster.components.GobelinImplemBug;
import dungeonMaster.components.MinotaurImplem;
import dungeonMaster.components.MinotaurImplemBug;
import dungeonMaster.components.MobImplem;
import dungeonMaster.components.MobImplemBug;
import dungeonMaster.components.MonsterImplem;
import dungeonMaster.components.MonsterImplemBug;
import dungeonMaster.components.PlayerImplem;
import dungeonMaster.components.PlayerImplemBug;
import dungeonMaster.contracts.CowContract;
import dungeonMaster.contracts.EntityContract;
import dungeonMaster.contracts.GobelinContract;
import dungeonMaster.contracts.MinotaurContract;
import dungeonMaster.contracts.MobContract;
import dungeonMaster.contracts.MonsterContract;
import dungeonMaster.contracts.PlayerContract;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Command;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MobTest extends TestCase{
	
	protected int x,y,hp,dmg,armor;
	protected Dir dir;
	protected EnvironmentService env;
	
	protected void setUp(){
	     this.x = 2 ;
	     this.y = 2;
	     this.hp = 5;
	     this.dmg = 1;
	     this.dir = Dir.N;
	     this.armor = 1;
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
	public void testForwardMobBlock() {
		EditMapImplem edit = new EditMapImplem();
		edit.init(70, 50);
		edit.setNature(x, y+1, Cell.WLL);
		edit.setNature(x, y-1, Cell.WLL);
		edit.setNature(x+1, y, Cell.WLL);
		edit.setNature(x-1, y, Cell.WLL);
		env.setPlateau(edit.getPlateau());
		MobContract mob = new MobContract(new MobImplem());
		
		mob.init(env, x, y, dir);
		
		int col = mob.getCol();
		int row = mob.getRow();
		Dir dir_tmp = mob.getFace();
		
		System.out.println("col : "+col+ ", row : "+ row + ", face : " + dir);
		
		assertFalse(mob.forward());
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
		
		assertTrue(entity.init(env, x, y, dir, hp , dmg,armor));
		
		
	}
	
	public void testEntityInitBug() {
		EntityContract entity = new EntityContract(new EntityImplemBug());
		
		assertFalse(entity.init(env, x, y, dir, -3 , -1,-1));
		
		
	}
	
	public void testCowImplem() {
		CowContract cow  = new CowContract(new CowImplem());
		
		assertTrue(cow.init(env, x+3, y, dir, 4,dmg,0));
		
		
		
	}
	
	public void testCowImplemBug() {
		CowContract cow  = new CowContract(new CowImplemBug());
		
		assertFalse(cow.init(env, x, y, dir, hp,dmg,armor));
		
		
		
	}
	
	
	public void testStepBB() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.BB);
		
		assertTrue(player.step());
	}
	
	public void testStepFF() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.FF);
		
		assertTrue(player.step());
	}
	
	public void testStepLL() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.LL);
		
		assertTrue(player.step());
	}
	
	public void testStepRR() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.RR);
		
		assertTrue(player.step());
	}
	
	public void testStepTL() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.TL);
		
		assertTrue(player.step());
	}
	
	public void testStepTR() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.TR);
		
		assertTrue(player.step());
	}
	
	public void testStepBBBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.BB);
		
		assertFalse(player.step());
	}
	
	public void testStepFFBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.FF);
		
		assertFalse(player.step());
	}
	
	public void testStepLLBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.LL);
		
		assertFalse(player.step());
	}
	
	public void testStepRRBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.RR);
		
		assertFalse(player.step());
	}
	
	public void testStepTLBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.TL);
		
		assertFalse(player.step());
	}
	
	public void testStepTRBug() {
		PlayerContract player = new PlayerContract(new PlayerImplemBug());
		
		player.init(env, x, y, dir, hp, dmg,armor);
		
		player.setLastCommand(Command.TR);
		
		assertFalse(player.step());
	}
	
	public void testStepAA() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplem());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		
		
		player.setLastCommand(Command.AA);		
		assertTrue(player.step());
		
		assertTrue(cow.getHealthPoints()==3);
		
		
	}
	
	public void testMinotaur() {
		MinotaurContract mino = new MinotaurContract(new MinotaurImplem());
		assertTrue(mino.init(env,x,y+4,dir));
	}
	
	public void testMinotaurBug() {
		MinotaurContract mino = new MinotaurContract(new MinotaurImplemBug());
		assertFalse(mino.init(env,x,y+3,dir));
	}
	
	public void testGobelin() {
		GobelinContract gob = new GobelinContract(new GobelinImplem());
		assertTrue(gob.init(env,x,y+4,dir));
	}
	
	public void testGobelinBug() {
		GobelinContract gob= new GobelinContract(new GobelinImplemBug());
		assertFalse(gob.init(env,x,y+3,dir));
	}
	
	public void testMonster() {
		MonsterContract mon = new MonsterContract(new MonsterImplem());
		assertTrue(mon.init(env, x, y, dir, hp, dmg, armor, 2, 55, 20));
	}
	
	public void testMonsterBug() {
		MonsterContract mon = new MonsterContract(new MonsterImplemBug());
		assertFalse(mon.init(env, x, y, dir, hp, dmg, armor, 2, 55, 20));
	}
	
	public void testSniff() {
		this.env = new EnvironmentImplem();
	    this.env.init(70,50);
		MonsterContract mon = new MonsterContract(new MonsterImplem());
		mon.init(env, x, y, dir, hp, dmg, armor, 2, 55, 20);
		PlayerContract player = new PlayerContract(new PlayerImplem());
		player.init(env,x+1,y,dir,hp,dmg,armor);
		assertTrue(mon.sniffAPlayer());
	}
	
	public void testSniffTooFar() {
		this.env = new EnvironmentImplem();
	    this.env.init(70,50);
		MonsterContract mon = new MonsterContract(new MonsterImplem());
		mon.init(env, x, y, dir, hp, dmg, armor, 2, 55, 20);
		PlayerContract player = new PlayerContract(new PlayerImplem());
		player.init(env,x+6,y+3,dir,hp,dmg,armor);
		assertFalse(mon.sniffAPlayer());
	}
	
	public void testSniffBug() {
		this.env = new EnvironmentImplem();
	    this.env.init(70,50);
		MonsterContract mon = new MonsterContract(new MonsterImplemBug());
		mon.init(env, x, y, dir, hp, dmg, armor, 2, 55, 20);
		PlayerContract player = new PlayerContract(new PlayerImplem());
		player.init(env,x+1,y,dir,hp,dmg,armor);
		assertFalse(mon.sniffAPlayer());
	}
	
	public static final Test suite(){
	    return new TestSuite(MobTest.class); 
	}
	
	
}
