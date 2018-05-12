package dungeonMaster.decorators;

import dungeonMaster.services.DoorLockService;

public class DoorLockDecorator implements DoorLockService {
	private DoorLockService delegate;
	
	public DoorLockDecorator(DoorLockService delegate) {
		this.delegate=delegate;
	}
	@Override
	public int getCol() {
		return delegate.getCol();
	}

	@Override
	public int getRow() {
		return delegate.getRow();
	}

	@Override
	public String getColor() {
		return delegate.getColor();
	}

	@Override
	public boolean init(int x, int y, String color) {
		return delegate.init(x, y, color);
	}
	
	public DoorLockService getDelegate() {
		return this.delegate;
	}

}
