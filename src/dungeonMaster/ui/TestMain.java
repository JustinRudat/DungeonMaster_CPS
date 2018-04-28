package dungeonMaster.ui;

import dungeonMaster.tests.MapTest;
import dungeonMaster.tests.MobTest;

public class TestMain {
	public static void main() {
		MapTest map = new MapTest() ;
		map.run();
		
		MobTest mob = new MobTest() ;
		mob.run();
		
//		MobTest mob_bug = new MobTest() {
//			public void runTest() {
//				testMobImplemBug();
//				testEntityImplemBug();
//				testCowImplemBug();
//				testPlayerImplemBug();
//			}
//		};
//		mob_bug.run();
		
	}
}
