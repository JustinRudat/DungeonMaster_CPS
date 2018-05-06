package dungeonMaster.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class MonsterImplemBug extends EntityImplem implements MonsterService {
	private int portee;
	private int player_at_x;
	private int player_at_y;
	private int ment_res;
	private int drop_c;
	private ArrayList<Noeud> chemin;
	@Override
	public boolean sniffAPlayer() {
		for(int i=-portee;i<portee;i++) {
			for(int j = -portee;j<portee;j++) {
				int x =this.getCol()+i;
				int y = this.getRow()+j;
				if(x>=0 && x<this.getEnv().getWidth() && y>=0 && y< this.getEnv().getHeight()) {
					OptionService<MobService> opt = this.getEnv().cellContent(x, y);
					if(opt.getOption()==Option.So) {
						if(opt.getElem() instanceof PlayerService) {
							player_at_x=x;
							player_at_y=y;
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg, int armor, int portee,int ment_res,int drop_c) {
		super.init(env, x, y, dir, hp, dmg, armor);
		this.portee = portee;
		this.ment_res = ment_res;
		this.drop_c= drop_c;
		return true;
	}

	@Override
	public boolean step() {
		if(sniffAPlayer()) {
			int o = 0;
			if(this.getCol()==player_at_x&&this.getRow()==player_at_y+1 ) {
				switch(this.getFace()) {
				case N :
					this.turnL();
					break;
				case S:
					this.attack();
					break;
				case E:
					this.turnR();
					break;
				case W :
					this.turnL();
					break;
				default:
					break;
				}
			}else if(this.getCol()==player_at_x&&this.getRow()==player_at_y-1) {
				switch(this.getFace()) {
				case N :
					this.attack();
					break;
				case S:
					this.turnL();
					break;
				case E:
					this.turnL();
					break;
				case W :
					this.turnR();
					break;
				default:
					break;
				}
			}else if(this.getCol()==player_at_x+1&&this.getRow()==player_at_y) {
				switch(this.getFace()) {
				case N :
					this.turnL();
					break;
				case S:
					this.turnR();
					break;
				case E:
					this.turnR();
					break;
				case W :
					this.attack();
					break;
				default:
					break;
				}
			}else if(this.getCol()==player_at_x+1&&this.getRow()==player_at_y) {
				switch(this.getFace()) {
				case N :
					this.turnR();
					break;
				case S:
					this.turnL();
					break;
				case E:
					this.attack();
					break;
				case W :
					this.turnR();
					break;
				default:
					break;
				}
			}else {
				moveTo(player_at_x,player_at_y);
			}
		}
		else {
			Random rand = new Random();
			int le_tirage = rand.nextInt(90);
			if(le_tirage<30) {
				this.forward();
			}
			else if(le_tirage<60) {
				this.turnL();
			}
			else if(le_tirage<=90) {
				this.turnR();
			}
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
						if(le_current.getX()+x >=0 && le_current.getX()+x<this.getEnv().getWidth()) {
							if(le_current.getY()+y >=0 && le_current.getY()+y<this.getEnv().getWidth()) {
								int tmp_x = le_current.getX()+x;
								int tmp_y = le_current.getY()+y;
								Cell test_cell  = this.getEnv().cellNature(tmp_x,tmp_y);
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
	public boolean moveTo(int x, int y) {
		if(x<this.getCol()) {
			switch(this.getFace()) {
			case N:
				this.strafeL();
				break;
			case S:
				this.strafeR();
				break;
			case E:
				this.turnL();
			case W : 
				this.forward();
			}
		}else if(x>this.getCol()) {
			switch(this.getFace()) {
			case N:
				this.strafeR();
				break;
			case S:
				this.strafeL();
				break;
			case E:
				this.forward();
			case W : 
				this.turnR();
			}
		}else if(x==this.getCol()) {
			switch(this.getFace()) {
			case N:
				if(y<this.getRow()) {
					this.backward();
				}else if(y>this.getRow()) {
					this.forward();
				}
				break;
			case S:
				if(y<this.getRow()) {
					this.forward();
				}else if(y>this.getRow()) {
					this.backward();
				}
				break;
			case E:
				if(y<this.getRow()) {
					this.strafeR();
				}else if(y>this.getRow()) {
					this.strafeL();
				}
				break;
			case W : 
				if(y<this.getRow()) {
					this.strafeL();
				}else if(y>this.getRow()) {
					this.strafeR();
				}
			}
		}
		return true;
	}

	@Override
	public boolean setDropChance(int drop_c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMentRes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setMentRes(int ment_res) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDropChance() {
		// TODO Auto-generated method stub
		return 0;
	}
}