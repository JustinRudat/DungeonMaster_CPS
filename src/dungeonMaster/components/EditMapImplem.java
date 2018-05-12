package dungeonMaster.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.services.DoorLockService;
import dungeonMaster.services.EditMapService;
import dungeonMaster.services.MapService;

public class EditMapImplem extends MapImplem implements EditMapService{
	private final int default_hauteur = 30;
	private final int default_largeur = 40;
	
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		ArrayList<Noeud> test = plusCourtChemin(this.getPlateau(),new Noeud(x1,y1,0,0), new Noeud(x2,y2,0,0));
		if(test.size()!=0) {
			return true;
		}
		return false;
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
		this.getPlateau()[x][y] = c;
		return true;
	}

	public boolean randomEdit() {
		Random rand = new Random();
		int rand_prem_mur = 16;
		int rand_sec_mur = rand_prem_mur+4;
		int nb_salle_g = 2+rand.nextInt(3);
		int nb_salle_d = 2+rand.nextInt(3);
		for(int i =0; i <this.getWidth();i++) {
			for(int j = 0;j<this.getHeight();j++) {
				if(i==0 || i==this.getWidth()-1 ||j==0||j==this.getHeight()-1) {
					setNature(i, j, Cell.WLL);
				}
				if(i==rand_prem_mur||i==rand_sec_mur) {
					setNature(i, j, Cell.WLL);
				}
			}
		}
		switch(nb_salle_g){
				case 2:
					int h_prem = 4+rand.nextInt(5);
					int test = rand.nextInt(3);
					if(test == 0) {
						for(int k = 1; k<rand_prem_mur;k++) {
							setNature(k, h_prem, Cell.WLL);
						}
						int rand_door = 1+rand.nextInt(rand_prem_mur);
						setNature(rand_door, h_prem, Cell.DNC);
						
						rand_door = 1+rand.nextInt(h_prem);
						setNature(rand_prem_mur, rand_door, Cell.DWC);
					}
					else if(test == 1) {
						for(int k = 1; k<rand_prem_mur;k++) {
							setNature(k, h_prem, Cell.WLL);
						}
						int rand_door = 1+rand.nextInt(rand_prem_mur);
						setNature(rand_door, h_prem, Cell.DNC);
						
						rand_door = h_prem+rand.nextInt(this.getHeight()-h_prem);
						setNature(rand_prem_mur, rand_door, Cell.DWC);
					}else {
						for(int k = 1; k<rand_prem_mur;k++) {
							setNature(k, h_prem, Cell.WLL);
						}
						int rand_door = 1+rand.nextInt(rand_prem_mur);
						setNature(rand_prem_mur, rand_door, Cell.DWC);
						
						rand_door = h_prem+rand.nextInt(this.getHeight()-h_prem);
						setNature(rand_prem_mur, rand_door, Cell.DWC);
					}
					break;
				case 3:
					int hauttmp = (this.getHeight()-4)/3;
					for(int k = 1; k<rand_prem_mur;k++) {
						setNature(k, hauttmp, Cell.WLL);
						setNature(k, 2*hauttmp+1, Cell.WLL);
					}
					int h_door = 1+rand.nextInt(hauttmp);
					setNature(rand_prem_mur, h_door, Cell.DWC);
					setNature(rand_prem_mur, h_door+hauttmp+1, Cell.DWC);
					setNature(rand_prem_mur, h_door+2*hauttmp+2, Cell.DWC);
					break;
				case 4:
					int haut_tmp_sec = (this.getHeight()-5)/4;
					for(int k = 1; k<rand_prem_mur;k++) {
						setNature(k, haut_tmp_sec, Cell.WLL);
						setNature(k, 2*haut_tmp_sec+1, Cell.WLL);
						setNature(k, 3*haut_tmp_sec+2, Cell.WLL);
					}
					int haut_door = 1+rand.nextInt(haut_tmp_sec);
					setNature(rand_prem_mur, haut_door, Cell.DWC);
					setNature(rand_prem_mur, haut_door+haut_tmp_sec+1, Cell.DWC);
					setNature(rand_prem_mur, haut_door+2*haut_tmp_sec+2, Cell.DWC);
					setNature(rand_prem_mur, haut_door+3*haut_tmp_sec+3, Cell.DWC);
					break;
				default:
					break;
		}
		
		switch(nb_salle_d){
			case 2:
				int h_prem = 4+rand.nextInt(5);
				int test = rand.nextInt(3);
				if(test == 0) {
					for(int k = rand_sec_mur; k<this.getWidth();k++) {
						setNature(k, h_prem, Cell.WLL);
					}
					int rand_door = 2+rand.nextInt(rand_prem_mur);
					setNature(rand_door, h_prem, Cell.DNC);
					
					rand_door = 2+rand.nextInt(h_prem);
					setNature(rand_sec_mur, rand_door, Cell.DWC);
				}
				else if(test == 1) {
					for(int k = rand_sec_mur; k<this.getWidth();k++) {
						setNature(k, h_prem, Cell.WLL);
					}
					int rand_door = 2+rand.nextInt(rand_prem_mur);
					setNature(rand_door, h_prem, Cell.DNC);
					
					rand_door = h_prem+rand.nextInt(this.getHeight()-h_prem);
					setNature(rand_sec_mur, rand_door, Cell.DWC);
				}else {
					for(int k = rand_sec_mur; k<this.getWidth();k++) {
						setNature(k, h_prem, Cell.WLL);
					}
					int rand_door = 2+rand.nextInt(rand_prem_mur);
					setNature(rand_sec_mur, rand_door, Cell.DWC);
					
					rand_door = h_prem+rand.nextInt(this.getHeight()-h_prem);
					setNature(rand_sec_mur, rand_door, Cell.DWC);
				}
				break;
			case 3:
				int hauttmp = (this.getHeight()-4)/3;
				for(int k = rand_sec_mur; k<this.getWidth();k++) {
					setNature(k, hauttmp, Cell.WLL);
					setNature(k, 2*hauttmp+1, Cell.WLL);
				}
				int h_door = 2+rand.nextInt(hauttmp);
				setNature(rand_sec_mur, h_door, Cell.DWC);
				setNature(rand_sec_mur, h_door+hauttmp+1, Cell.DWC);
				setNature(rand_sec_mur, h_door+2*hauttmp+2, Cell.DWC);
				break;
			case 4:
				int haut_tmp_sec = (this.getHeight()-5)/4;
				for(int k = rand_sec_mur; k<this.getWidth();k++) {
					setNature(k, haut_tmp_sec, Cell.WLL);
					setNature(k, 2*haut_tmp_sec+1, Cell.WLL);
					setNature(k, 3*haut_tmp_sec+2, Cell.WLL);
				}
				int haut_door = 2+rand.nextInt(haut_tmp_sec);
				setNature(rand_sec_mur, haut_door, Cell.DWC);
				setNature(rand_sec_mur, haut_door+haut_tmp_sec+1, Cell.DWC);
				setNature(rand_sec_mur, haut_door+2*haut_tmp_sec+2, Cell.DWC);
				setNature(rand_sec_mur, haut_door+3*haut_tmp_sec+3, Cell.DWC);
				break;
			default:
				break;
		}
		
		setNature(this.getWidth()/2,1,Cell.IN);
		int col_rand = rand.nextInt(this.getWidth()-2);
		int row_rand = rand.nextInt(this.getHeight()-2);
		while(this.cellNature(col_rand, row_rand)!=Cell.EMP) {
			col_rand = rand.nextInt(this.getWidth()-2);
			row_rand = rand.nextInt(this.getHeight()-2);
		}
		
		setNature(col_rand, row_rand, Cell.OUT);
		
		return isReady();
	}
	

	@Override
	public boolean addDoorLock(int x, int y, String color,Cell type) {
		DoorLockService door = new DoorLockImplem();
		door.init(x, y, color);
		setNature(x, y, type);
		this.getDoorLocked().add(door);
		return true;
	}

	@Override
	public boolean randomEditSquare() {
		for (int i=0;i<this.getWidth();i++) {
			for(int j=0;j<this.getHeight();j++) {
				if(i==0||i==this.getWidth()-1||j==0||j==this.getHeight()-1||j==(this.getHeight()-1)/2||i==(this.getWidth()-1)/2) {
					setNature(i, j, Cell.WLL);
				}
				
			}
		}
		setNature(1,1,Cell.IN);
		setNature(this.getWidth()-2,1,Cell.OUT);
		
		addDoorLock((this.getWidth()-1)/4, (this.getHeight()-1)/2, ColorKeyDoor.RED,Cell.DNL);
		
		addDoorLock((this.getWidth()-1)*3/4, (this.getHeight()-1)/2, ColorKeyDoor.GREEN,Cell.DNL);
		
		addDoorLock((this.getWidth()-1)/2, (this.getHeight()-1)*3/4, ColorKeyDoor.BLACK,Cell.DWL);
		return isReady();
	}
	

}
