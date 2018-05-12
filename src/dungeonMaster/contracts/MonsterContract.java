package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.decorators.MonsterDecorator;
import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Option;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.PlayerService;

public class MonsterContract extends MonsterDecorator {

	public MonsterContract(MonsterService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean sniffAPlayer() {
		return super.sniffAPlayer();
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir, int hp, int dmg, int armor, int portee,
			int men_res, int drop_c) {
		boolean retour = false;
		
		try {
			if(drop_c<0) {
				throw new PreConditionException("setdropchance : monster: negative drop_c");
			}
			if(men_res<0) {
				throw new PreConditionException("setdropchance : monster: negative ment_res");
			}
			if(portee<0) {
				throw new PreConditionException("setdropchance : monster: negative range");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		
		retour = super.init(env, x, y, dir, hp, dmg, armor, portee, men_res, drop_c);
		
		try {
			if(drop_c!=getDropChance()) {
				throw new PostConditionException("setdropchance : monster: error setting drop_c");
			}
			if(men_res!=getMentRes()) {
				throw new PostConditionException("setdropchance : monster: error setting ment_res");
			}
			if(portee!=getPortee()) {
				throw new PostConditionException("setdropchance : monster: error setting range");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin) {
		return super.plusCourtChemin(graphe, depart, fin);
	}

	@Override
	public boolean moveTo(int x, int y) {
		return super.moveTo(x, y);
	}
	

	@Override
	public boolean setDropChance(int drop_c) {
		boolean retour = false;
		try {
			if(drop_c<0) {
				throw new PreConditionException("setdropchance : monster: negative drop_c");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour = super.setDropChance(drop_c);
		try {
			if(getDropChance()!=drop_c) {
				throw new PostConditionException("setdropchance : monster: error setting drop_c");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public int getMentRes() {
		return super.getMentRes();
	}

	@Override
	public boolean setMentRes(int ment_res) {
		boolean retour = false;
		try {
			if(ment_res<0) {
				throw new PreConditionException("setdropchance : monster: negative ment_res");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour = super.setMentRes(ment_res);
		
		try {
			if(getMentRes()!=ment_res) {
				throw new PostConditionException("setdropchance : monster: negative drop_c");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		
		return retour;
	}

	@Override
	public int getDropChance() {
		return super.getDropChance();
	}

	@Override
	public int getPortee() {
		return super.getPortee();
	}
	
	@Override
	public boolean step() {
		boolean retour = false;
		MobService mob = null;
		int player_hp=-1;
		int player_armor=-1;
		boolean player_def=false;
		switch(getFace()) {
		case N:
			if(getEnv().cellContent(this.getCol(),this.getRow()+1).getOption()!=Option.No) {
				if(mob instanceof PlayerService) {
					mob = getEnv().cellContent(this.getCol(),this.getRow()+1).getElem();
					player_hp = ((PlayerService)mob).getHealthPoints();
					player_armor = ((PlayerService)mob).getArmor();
					player_def = ((PlayerService)mob).isDef();
				}
			}
			break;
		case S:
			if(getEnv().cellContent(this.getCol(),this.getRow()-1).getOption()!=Option.No) {
				if(mob instanceof PlayerService) {
					mob = getEnv().cellContent(this.getCol(),this.getRow()-1).getElem();
					player_hp = ((PlayerService)mob).getHealthPoints();
					player_armor = ((PlayerService)mob).getArmor();
					player_def = ((PlayerService)mob).isDef();
				}
			}
			break;
		case E:
			if(getEnv().cellContent(this.getCol()+1,this.getRow()).getOption()!=Option.No) {
				if(mob instanceof PlayerService) {
					mob = getEnv().cellContent(this.getCol()+1,this.getRow()).getElem();
					player_hp = ((PlayerService)mob).getHealthPoints();
					player_armor = ((PlayerService)mob).getArmor();
					player_def = ((PlayerService)mob).isDef();
				}
			}
			break;
		case W:
			if(getEnv().cellContent(this.getCol()-1,this.getRow()).getOption()!=Option.No) {
				if(mob instanceof PlayerService) {
					mob = getEnv().cellContent(this.getCol()-1,this.getRow()).getElem();
					player_hp = ((PlayerService)mob).getHealthPoints();
					player_armor = ((PlayerService)mob).getArmor();
					player_def = ((PlayerService)mob).isDef();
				}
			}
			break;
			
		}
		Dir face_at_pre = this.getFace();
		int col_at_pre = this.getCol();
		int row_at_pre = this.getRow();
		
		retour = super.step();
		try {
			if(mob!=null) {
				if(((PlayerService)mob).getHealthPoints() != player_hp - (getDegats()>player_armor+(player_def?1:0)?getDegats()-player_armor-(player_def?1:0):0)){
					throw new PostConditionException("step : monster : monster didnt attack");
				}
			}else {
				if(face_at_pre==getFace() && (col_at_pre==getCol()&&row_at_pre==getRow())) {
					throw new PostConditionException("step : monster : didnt move or turned");
				}
				
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}
}
