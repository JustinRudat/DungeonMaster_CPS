package dungeonMaster.contracts;

import java.util.ArrayList;

import dungeonMaster.decorators.EnvironmentDecorator;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.Cell;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MapService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;
import dungeonMaster.services.OptionService;

public class EnvironmentContract extends EnvironmentDecorator {

	public EnvironmentContract(EnvironmentService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean init(int largeur,int hauteur) {
		boolean retour = false;
		retour = ((EnvironmentService)this.getDelegate()).init(largeur, hauteur);
		try {
			if(getContent()==null||getContent().size()==0) {
				throw new PostConditionException("init : environment : content not initialized\n");
			}
			for(OptionService<MobService> cell_cont : getContent()) {
				if(cell_cont.getOption()!=Option.No) {
					throw new PostConditionException("init : environment : error on initialized content\n");
				}
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}
	


	

	@Override
	public boolean addMobOption(MobService mob) {
		boolean retour = false;
		int x= mob.getCol();
		int y = mob.getRow();
		
		
		retour = ((EnvironmentService)this.getDelegate()).addMobOption(mob);
		try {
			if(!getContent().get(y*this.getWidth()+x).getElem().equals(mob)) {
				throw new PostConditionException("addmoboption: environment : mob not in content");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public boolean removeMobOption(MobService mob) {
		boolean retour = false;
		int x= mob.getCol();
		int y = mob.getRow();
		
		
		retour = ((EnvironmentService)this.getDelegate()).removeMobOption(mob);
		try {
			if(getContent().get(y*this.getWidth()+x).getOption()!=Option.No) {
				throw new PostConditionException("addmoboption: environment : mob still in content");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}
	
	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		OptionService<MobService> retour = null;
		try {
			if(x<0||x>=getWidth()) {
				throw new PreConditionException("cellcontent : environment : error on x");
			}
			if(y<0||y>=getHeight()) {
				throw new PreConditionException("cellcontent : environment : error on y");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
		}
		
		retour  = super.cellContent(x, y);
		try {
			if(!getContent().get(y*this.getWidth()+x).equals(retour)) {
				throw new PostConditionException("cellcontent : environment : contetn doesnt match");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
		}
		
		return retour;
	}

	

}
