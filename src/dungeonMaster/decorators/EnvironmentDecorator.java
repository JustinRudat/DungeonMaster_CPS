package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.services.Cell;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;

public class EnvironmentDecorator extends MapDecorator implements EnvironmentService{

	public EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
	}
	
	@Override
	public boolean init(int largeur,int hauteur) {
		return ((EnvironmentService)this.getDelegate()).init(largeur, hauteur);
	}
	
	@Override
	public OptionService<MobService> cellContent(int x, int y) {
		return ((EnvironmentService) this.getDelegate()).cellContent(x,y);
	}

	@Override
	public ArrayList<OptionService<MobService>> getContent() {
		return ((EnvironmentService)this.getDelegate()).getContent();
	}

	@Override
	public boolean addMobOption(MobService mob) {
		return ((EnvironmentService)this.getDelegate()).addMobOption(mob);
		
	}

	@Override
	public boolean removeMobOption(MobService mob) {
		return ((EnvironmentService)this.getDelegate()).removeMobOption(mob);
	}
	

}
