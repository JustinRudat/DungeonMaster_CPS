package dungeonMaster.components;

import java.util.ArrayList;
import java.util.Collections;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapImplemBug extends MapImplem implements EditMapService{

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		ArrayList<Noeud> test = plusCourtChemin(this.getPlateau(),new Noeud(x1,y1,0,0), new Noeud(x2,y2,0,0));
		if(test.size()!=0) {
			return false;
		}
		return true;
	}
	
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin){
		
		ArrayList<Noeud> closed = new ArrayList<>();
		ArrayList<Noeud> open = new ArrayList<>();
		open.add(new Noeud(depart.getX(),depart.getY(),0,0));
		while(open.size()>0) {
			Noeud le_current = open.remove(0);
			if(le_current.getX() == fin.getX() && le_current.getY() == fin.getY()) {
				closed.add(le_current);
			}
			for(int x=-1;x<1;x++) {
				for(int y = -1;y<1;y++) {
					if(le_current.getX()+x >=0 && le_current.getX()+x<this.getWidth()) {
						if(le_current.getY()+y >=0 && le_current.getY()+y<this.getWidth()) {
							int tmp_x = le_current.getX()+x;
							int tmp_y = le_current.getY()+y;
							Cell test_cell  = this.cellNature(tmp_x,tmp_y);
							if(test_cell != Cell.WLL) {
								boolean le_test = false;
								Noeud le_noeud = null;
								for(Noeud v : closed) {
									if(v.getX()==tmp_x && v.getY()==tmp_y && v.getCout()<le_current.getCout()) {
										le_noeud = v ;
										le_test = true;
									}
								}
								for(Noeud v : open) {
									if(v.getX()==tmp_x && v.getY()==tmp_y && v.getCout()<le_current.getCout()) {
										le_noeud = v;
										le_test = true;
									}
								}
								if(!le_test) {
									if(le_noeud ==null) {
										le_noeud = new Noeud(tmp_x, tmp_y, 0, 0);
									}
									le_noeud.setCout(le_current.getCout()+1);
									le_noeud.setHeur(le_noeud.getCout()+ le_noeud.distanceA(fin));
									open.add(le_noeud);
									Collections.sort(open);
								}
							}
						}
					}
				}
			}
			closed.add(le_current);
		}
		
		
		return closed;
	}

	@Override
	public boolean isReady() {
		Cell cell_in = null;
		Cell cell_out = null;
		int index_in_x =0;
		int index_in_y =0;
		int index_out_x =0;
		int index_out_y =0;
		for(int i=0;i<this.getWidth();i++) {
			for(int j=0;j<this.getHeight();j++) {
				
				Cell current_cell = this.getPlateau()[i][j];
				if(current_cell==Cell.IN) {
					cell_in=current_cell;
					index_in_x = i;
					index_in_y = j;
				}else if(current_cell == Cell.OUT) {
					cell_out = current_cell;
					index_out_x = i;
					index_out_y = j;
				}
			}
		}
		if(cell_in!=null && cell_out !=null) {
			if(isReachable(index_in_x, index_in_y,index_out_x, index_out_y)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setNature(int x, int y, Cell c) {
		this.getPlateau()[y][x] = c;
		return true;
	}

	@Override
	public boolean randomEditSquare() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDoorLock(int x, int y, String color,Cell type) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
	

}
