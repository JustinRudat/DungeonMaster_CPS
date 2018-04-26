package dungeonMaster.ui;

import dungeonMaster.tests.MapTest;

public class TestMain {
	public static void main() {
		MapTest map = new MapTest() {
			public void runTest() {
				testMapImplem();
				testEditMapImplem();
			}
		};
		map.run();
		
	}
}
