package dungeonMaster.tests;

import dungeonMaster.components.CowImplem;
import dungeonMaster.components.CowImplemBug;
import dungeonMaster.components.EngineImplem;
import dungeonMaster.components.EngineImplemBug;
import dungeonMaster.components.EnvironmentImplem;
import dungeonMaster.components.PlayerImplem;
import dungeonMaster.contracts.CowContract;
import dungeonMaster.contracts.EngineContract;
import dungeonMaster.contracts.PlayerContract;
import dungeonMaster.services.Command;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EngineTest extends TestCase {
	protected int x,y,x_mob,y_mob,hp,dmg,armor;
	protected int largeur, hauteur;
	protected Dir dir;
	protected EnvironmentService env;
	
	protected void setUp(){
	     this.x = 2 ;
	     this.y = 2;
	     this.x_mob=3;
	     this.y_mob=2;
	     this.hp = 5;
	     this.dmg = 1;
	     this.armor = 1;
	     this.dir = Dir.N;
	     this.env = new EnvironmentImplem();
	     this.largeur=70;
	     this.hauteur=50;
	     this.env.init(this.largeur,this.hauteur);
	}
	
	public void testEngine() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplem());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplem());
		
		assertTrue(engine.init(env));
		
		engine.addEntity(player);
		engine.addEntity(cow);
	}
	
	public void testEngineBug() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplem());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplemBug());
		
		assertFalse(engine.init(env));
		
		engine.addEntity(player);
		engine.addEntity(cow);
		
	}
	public void testEngineAddEntity() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplem());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplem());
		
		engine.init(env);
		
		assertTrue(engine.addEntity(player));
		assertTrue(engine.addEntity(cow));
	}
	
	public void testEngineBugAddEntity() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplem());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplemBug());
		
		engine.init(env);
		
		assertFalse(engine.addEntity(player));
		assertFalse(engine.addEntity(cow));
		
	}
	
	public void testEngineStep() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplemBug());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplem());
		
		engine.init(env);
		engine.addEntity(player);
		engine.addEntity(cow);
		player.setLastCommand(Command.AA);	
		assertTrue(engine.step());
		
	}
	
	public void testEngineStepKill() {
		PlayerContract player = new PlayerContract(new PlayerImplem());
		CowContract cow = new CowContract(new CowImplemBug());
		cow.init(env, x, y+1, dir,4,1,0);
		player.init(env, x, y, dir, hp, dmg,armor);
		EngineContract engine = new EngineContract(new EngineImplem());
		
		engine.init(env);
		engine.addEntity(player);
		engine.addEntity(cow);
		
		assertTrue(engine.getEntities().size()==2);
		player.setLastCommand(Command.AA);	
		engine.step();
		player.setLastCommand(Command.AA);	
		engine.step();
		player.setLastCommand(Command.AA);	
		engine.step();
		player.setLastCommand(Command.AA);	
		engine.step();
		engine.step();
		assertTrue(engine.getEntities().size()==1);
	}
	
	public static final Test suite(){
	    return new TestSuite(EngineTest.class); 
	}
}
