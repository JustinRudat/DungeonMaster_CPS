package dungeonMaster.ui;

import dungeonMaster.tests.MapTest;
import dungeonMaster.tests.MobTest;

public class TestMain {
	public static void main() {
		MapTest map = new MapTest() {
			public void runTest() {
				testMapImplem();
				testEditMapImplem();
			}
		};
		map.run();
		
		MobTest mob = new MobTest() {
			public void runTest() {
//				testInitMob();
//				testBackwardMob();
//				testForwardMob();
//				testStrafeLMob();
//				testStrafeRMob();
//				testTurnLMob();
//				testTurnRMob();
				testEntityInit();
				testCowImplem();
				testPlayerImplem();
			}
		};
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
