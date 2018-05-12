package dungeonMaster.contracts;

import dungeonMaster.decorators.DoorLockDecorator;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.services.DoorLockService;

public class DoorLockContract extends DoorLockDecorator {

	public DoorLockContract(DoorLockService delegate) {
		super(delegate);
	}
	
	@Override
	public int getCol() {
		return super.getCol();
	}

	@Override
	public int getRow() {
		return super.getRow();
	}

	@Override
	public String getColor() {
		return super.getColor();
	}

	@Override
	public boolean init(int x, int y, String color) {
		boolean retour = false;
		retour = super.init(x, y, color);
		try {
			if(getDelegate().getCol()!=x) {
				throw new PostConditionException("init doorLock : error while setting x");
			}
			if(getDelegate().getRow()!=y) {
				throw new PostConditionException("init doorLock : error while setting y");
			}
			if(!getDelegate().getColor().equals(color)) {
				throw new PostConditionException("init doorLock : error while setting the color");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

}
