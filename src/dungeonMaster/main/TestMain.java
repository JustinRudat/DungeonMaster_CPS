package dungeonMaster.main;

import dungeonMaster.tests.EngineTest;
import dungeonMaster.tests.MapTest;
import dungeonMaster.tests.MobTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestMain {
	public static final void main(String[] args)
	  {
	    junit.textui.TestRunner.run(TestMain.suite());
	  }

	public static Test suite(){
	    final TestSuite suite = new TestSuite("All Tests");
	    
	    suite.addTest(MapTest.suite());
	    suite.addTest(MobTest.suite());
	    suite.addTest(EngineTest.suite());
	    
	    return suite;
	}

}

