package dungeonMaster.decorators;

import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;

public class MobDecorator implements MobService {
	private final MobService delegate;
	
	public MobDecorator(MobService delegate) {
		this.delegate = delegate;
	}
	
	public MobService getDelegate() {
		return this.delegate;
	}

	@Override
	public EnvironmentService getEnv() {
		return this.delegate.getEnv();
	}

	@Override
	public int getCol() {
		return this.delegate.getCol();
	}

	@Override
	public int getRow() {
		return this.delegate.getRow();
	}

	@Override
	public Dir getFace() {
		return this.delegate.getFace();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, Dir dir) {
		this.delegate.init(env,x,y,dir);	
	}

	@Override
	public void forward() {
		this.delegate.forward();
		
	}

	@Override
	public void backward() {
		this.delegate.backward();
		
	}

	@Override
	public void turnL() {
		this.delegate.turnL();
	}

	@Override
	public void turnR() {
		this.delegate.turnR();
	}

	@Override
	public void strafeL() {
		this.delegate.strafeL();
	}

	@Override
	public void strafeR() {
		this.delegate.strafeR();
		
	}

}
