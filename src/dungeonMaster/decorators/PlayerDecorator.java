package dungeonMaster.decorators;

import java.util.ArrayList;

import dungeonMaster.enumeration.Cell;
import dungeonMaster.enumeration.Command;
import dungeonMaster.enumeration.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.LootService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.OptionService;
import dungeonMaster.services.PlayerService;

public class PlayerDecorator extends EntityDecorator implements PlayerService{
	
	public PlayerDecorator(PlayerService delegate) {
		super(delegate);
	}

	@Override
	public OptionService<Command> getLastCommand() {
		return ((PlayerService) this.getDelegate()).getLastCommand();
	}

	@Override
	public OptionService<MobService> getContent(int x, int y) {
		return ((PlayerService) this.getDelegate()).getContent(x, y);
	}

	@Override
	public Cell getNature(int x, int y) {
		return ((PlayerService) this.getDelegate()).getNature(x, y);
	}

	@Override
	public Cell getViewable(int x, int y) {
		return ((PlayerService) this.getDelegate()).getViewable(x, y);
				
	}

	@Override
	public boolean setLastCommand(Command command) {
		return  ((PlayerService) this.getDelegate()).setLastCommand(command);
	}

	@Override
	public ArrayList<LootService> getBag() {
		return ((PlayerService) this.getDelegate()).getBag();
	}

	

	@Override
	public boolean addLoot(LootService lt) {
		return ((PlayerService) this.getDelegate()).addLoot(lt);
	}

	@Override
	public boolean take() {
		return ((PlayerService) this.getDelegate()).take();
	}

	@Override
	public ArrayList<LootService> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addKey(LootService lt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPacification() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDef() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pacify() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell isViewable(int col, int row) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
