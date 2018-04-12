package dungeonMaster.components;

import dungeonMaster.services.Cell;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapImplem extends MapImplem implements EditMapService{

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		this.getPlateau()[x][y] = c;
	}

	

}
