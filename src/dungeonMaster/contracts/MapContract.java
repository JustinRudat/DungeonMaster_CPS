package dungeonMaster.contracts;

import java.util.ArrayList;
import java.util.Random;

import dungeonMaster.decorators.MapDecorator;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.Cell;
import dungeonMaster.services.MapService;

public class MapContract extends MapDecorator {

	public MapContract(MapService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}
	
	@Override
	public Cell[][] getPlateau() {
		return super.getPlateau();
	}

	@Override
	public Cell cellNature(int i, int j) {
		try {
			if(i<0 || i>= this.getWidth()) {
				throw new PreConditionException("cellNature : erreur sur i");
			}
			if(j<0 || j>= this.getHeight()) {
				throw new PreConditionException("cellNature : erreur sur j");
			}
		}catch (PreConditionException e) {
			e.printStackTrace();
		}
		return super.cellNature(i, j);
	}

	@Override
	public boolean init(int largeur, int hauteur) {
		try {
			if(hauteur<0) {
				throw new PreConditionException("init : hauteur negative");
			}
			if(largeur<0) {
				throw new PreConditionException("init : largeur negative");
			}
		}catch (PreConditionException e) {
			e.printStackTrace();
		}
		
//		checkInvariants();
		
		boolean retour = super.init(largeur, hauteur);
		
//		checkInvariants();
		
		try {
			if(this.getHeight() != hauteur) {
				throw new PostConditionException("erreur en parametrant hauteur");
			}
			if(this.getWidth() != largeur) {
				throw new PostConditionException("erreur en parametrant largeur");
			}
		} catch (PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public boolean openDoor(int x, int y) {
		try {
			if(this.cellNature(x,y)!=Cell.DNC&&this.cellNature(x,y)!=Cell.DWC) {
				throw new PreConditionException("opendoor : Ceci n'est pas une porte fermée");
			}
		}catch (PreConditionException e) {
			e.printStackTrace();
		}
		
		int nb_echant = (this.getHeight() + this.getWidth())/10;
		ArrayList<Cell> some_cell_at_pre = new ArrayList<>();
		int[] u_at_pre = new int[nb_echant];
		int[] v_at_pre = new int[nb_echant];
		Random rand = new Random();
		int u=0,v=0;
		for(int i = 0; i <nb_echant;i++) {
			while(u==x) {
				u = rand.nextInt(this.getWidth());
			}
			while(x==y) {
				v = rand.nextInt(this.getHeight());
			}
			some_cell_at_pre.add(this.cellNature(u, v));
			u_at_pre[i]=u;
			v_at_pre[i]=v;
		}
		
		Cell state_at_pre = this.cellNature(x,y);
		
//		checkInvariants();
		
		boolean retour = super.openDoor(x, y);
		
//		checkInvariants();
		
		try {
			if(state_at_pre==Cell.DWC) {
				if(this.cellNature(x, y)!=Cell.DWO) {
					throw new PostConditionException("opendoor : DWC -/-> DWO");
				}
			}
			if(state_at_pre==Cell.DNC) {
				if(this.cellNature(x, y)!=Cell.DNO) {
					throw new PostConditionException("opendoor : DNC -/-> DNO");
				}
			}
			
			boolean need_exception =false;
			int ind = 0;
			for(Cell nature : some_cell_at_pre) {
				if(nature != this.getPlateau()[u_at_pre[ind]][v_at_pre[ind]]) {
					need_exception = true;
					break;
				}
			}
			if(need_exception) {
				throw new PostConditionException("opendoor : some cell have changed");
			}
			 
			
		}catch (PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}

	@Override
	public boolean closeDoor(int x, int y) {
		try {
			if(this.cellNature(x,y)!=Cell.DNO&&this.cellNature(x,y)!=Cell.DWO) {
				throw new PreConditionException("opendoor : Ceci n'est pas une porte ouverte");
			}
		}catch (PreConditionException e) {
			e.printStackTrace();
		}
		
		Cell state_at_pre = this.cellNature(x,y);
		int nb_echant = (this.getHeight() + this.getWidth())/10;
		ArrayList<Cell> some_cell_at_pre = new ArrayList<>();
		int[] u_at_pre = new int[nb_echant];
		int[] v_at_pre = new int[nb_echant];
		Random rand = new Random();
		int u=0,v=0;
		for(int i = 0; i <nb_echant;i++) {
			while(u==x) {
				u = rand.nextInt(this.getWidth());
			}
			while(x==y) {
				v = rand.nextInt(this.getHeight());
			}
			some_cell_at_pre.add(this.cellNature(u, v));
			u_at_pre[i]=u;
			v_at_pre[i]=v;
		}
//		checkInvariants();
		
		boolean retour = super.closeDoor(x, y);
		
//		checkInvariants();
		
		try {
			if(state_at_pre==Cell.DWO) {
				if(this.cellNature(x, y)!=Cell.DWC) {
					throw new PostConditionException("closedoor : DWO -/-> DWC");
				}
			}
			if(state_at_pre==Cell.DNO) {
				if(this.cellNature(x, y)!=Cell.DNC) {
					throw new PostConditionException("closedoor : DNO -/-> DNC");
				}
			}
			
			boolean need_exception =false;
			int ind = 0;
			for(Cell nature : some_cell_at_pre) {
				if(nature != this.getPlateau()[u_at_pre[ind]][v_at_pre[ind]]) {
					need_exception = true;
					break;
				}
			}
			if(need_exception) {
				throw new PostConditionException("closedoor : some cell have changed");
			}
		}catch (PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}

	

}
