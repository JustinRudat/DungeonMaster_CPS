package dungeonMaster.components;

import java.util.ArrayList;

import dungeonMaster.enumeration.Dir;
import dungeonMaster.enumeration.Option;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class EnvironmentImplem extends MapImplem implements EnvironmentService{
	private ArrayList<OptionService<MobService>> content;
	
	public ArrayList<OptionService<MobService>> getContent(){
		return this.content;
	}
	
	public boolean init(int largeur, int hauteur) {
		super.init(largeur,hauteur);
		content = new ArrayList<>();
		for (int i=0; i < this.getWidth();i++) {
			for(int j=0;j<this.getHeight();j++) {
				OptionService<MobService> option = new OptionImplem();
				option.init(null,Option.No);
				content.add(option);
				
				// indice option d'une case x , y : width * y + x
			}
		}
		return true;
	}
	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		OptionImplem<MobService> option = (OptionImplem<MobService>) content.get(y*this.getWidth() + x);
		return option;
		
	}
	
	@Override
	public boolean addMobOption(MobService mob) {
		int x= mob.getCol();
		int y = mob.getRow();
		OptionService<MobService> option = new OptionImplem<>();
		option.init(mob,Option.So);
		content.set(y*this.getWidth()+x,option);
		return true;
	}
	@Override
	public boolean removeMobOption(MobService mob) {
		int x= mob.getCol();
		int y = mob.getRow();
		OptionService<MobService> option = new OptionImplem<>();
		option.init(null,Option.No);
		content.set(y*this.getWidth()+x,option);
		return true;
	}
	
	
	
}
