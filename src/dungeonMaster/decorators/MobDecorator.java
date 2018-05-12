package dungeonMaster.decorators;

import dungeonMaster.enumeration.Dir;
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
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		return this.delegate.init(env,x,y,dir);	
	}

	@Override
	public boolean forward() {
		return this.delegate.forward();
		
	}

	@Override
	public boolean backward() {
		return this.delegate.backward();
		
	}

	@Override
	public boolean turnL() {
		return this.delegate.turnL();
	}

	@Override
	public boolean turnR() {
		return this.delegate.turnR();
	}

	@Override
	public boolean strafeL() {
		return this.delegate.strafeL();
	}

	@Override
	public boolean strafeR() {
		return this.delegate.strafeR();
		
	}

	@Override
	public boolean setEnv(EnvironmentService env) {
		return this.delegate.setEnv(env);
	}

	@Override
	public boolean setFace(Dir dir) {
		return this.delegate.setFace(dir);
	}

	@Override
	public boolean setCol(int col) {
		return this.delegate.setCol(col);
	}

	@Override
	public boolean setRow(int row) {
		return this.delegate.setRow(row);
	}

}
