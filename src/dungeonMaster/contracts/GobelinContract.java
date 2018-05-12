package dungeonMaster.contracts;

import dungeonMaster.decorators.GobelinDecorator;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.services.Dir;
import dungeonMaster.services.MobService;
import dungeonMaster.services.MonsterService;
import dungeonMaster.services.Option;
import dungeonMaster.services.PlayerService;

public class GobelinContract extends GobelinDecorator{

	public GobelinContract(MonsterService delegate) {
		super(delegate);
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
					throw new PostConditionException("step : gobelin: monster didnt attack");
				}
			}else {
				if(face_at_pre==getFace() && (col_at_pre==getCol()&&row_at_pre==getRow())) {
					throw new PostConditionException("step : gobelin : didnt move or turned");
				}
				
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		return retour;
	}
}
