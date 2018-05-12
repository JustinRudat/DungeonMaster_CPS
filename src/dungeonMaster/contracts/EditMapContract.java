package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.decorators.EditMapDecorator;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapContract extends EditMapDecorator {

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		boolean retour = false;
		try {
			if(x1<0||x1>=getWidth()) {
				throw new PreConditionException("isreachable : editmap : error on x1");
			}
			if(x2<0||x2>=getWidth()) {
				throw new PreConditionException("isreachable : editmap : error on x2");
			}
			if(y1<0||y1>=getHeight()) {
				throw new PreConditionException("isreachable : editmap : error on y1");
			}
			if(y2<0||y2>=getWidth()) {
				throw new PreConditionException("isreachable : editmap : error on y2");
			}
		}catch(PreConditionException e ) {
			e.printStackTrace();
			return false;
		}
		retour=super.isReachable(x1, y1, x2, y2);
		return retour;
	}

	@Override
	public boolean isReady() {
		boolean retour = false;
		try {
			if(getPlateau()==null) {
				throw new PreConditionException("isready : editmap : plateau null \n");
			}
			if(getWidth()==0) {
				throw new PreConditionException("isready : editmap : error on width \n");
			}
			if(getHeight()==0) {
				throw new PreConditionException("isready : editmap : error on height \n");
			}
			
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour =super.isReady();
		return retour;
	}

	@Override
	public boolean setNature(int x, int y, Cell c) {
		
		try {
			if(x<0||x>= super.getWidth()) {
				throw new PreConditionException("setnature : editmap : error on X");
			}
			if(y<0||y>= super.getHeight()) {
				throw new PreConditionException("setnature : editmap : error on Y");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		boolean retour = false;
		retour =  super.setNature(x, y, c);
		try {
			if(super.cellNature(x, y)!= c) {
				throw new PostConditionException("setNature: editmap : nature cell is not set correctly\n");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin) {
		return super.plusCourtChemin(graphe, depart, fin);
	}

	@Override
	public boolean randomEditSquare() {
		boolean retour = false;
		retour = super.randomEditSquare();
		try {
			if(!isReady()) {
				throw new PostConditionException("randomEditSquare : illegal map\n");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	

	@Override
	public boolean addDoorLock(int x, int y, String color,Cell type) {
		boolean retour = false;
		try {
			if(type != Cell.DWL && type != Cell.DNL) {
				throw new PreConditionException("adddoorlock : editmap : wrong cell type\n");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		
		int size_at_pre  = super.getDoorLocked().size();
		retour = super.addDoorLock(x, y, color,type);
		try {
			if(super.getDoorLocked().size()!=size_at_pre+1) {
				throw new PostConditionException("adddoorlock : editmap : door wasnt added to the locked doors list\n");
			}
		}catch(PostConditionException e ) {
			e.printStackTrace();
		}
		return retour;
	}


}
