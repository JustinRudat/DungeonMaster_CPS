package dungeonMaster.services;

import java.util.ArrayList;

public interface EnvironmentService extends /* includes */ MapService {
	
	
	public void init(int largeur,int hauteur);
	public ArrayList<OptionService<MobService>> getContent();
	
	public void addMobOption(MobService mob);
	public void removeMobOption(MobService mob);
    /** Observateur : observe si la case désignée par les coordonnées passées en paramètres est occupée par un mob
     * 
     * @param x
     * @param y
     * @return
     */
    public OptionService<MobService> cellContent(int x, int y);
    
    /** Opérateur :
     * 
     * \pre : this.cellContent(x,y) == No
     * @param x
     * @param y
     * @return
     */
    public void closeDoor(int x, int y);
}