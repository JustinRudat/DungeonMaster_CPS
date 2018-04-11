package dungeonMaster.services;

public interface EnvironmentService extends /* includes */ MapService {
    /** Observateur : observe si la case désignée par les coordonnées passées en paramètres est occupée par un mob
     * 
     * @param x
     * @param y
     * @return
     */
    public Option cellContent(int x, int y);
    
    /** Opérateur :
     * 
     * \pre : this.cellContent(x,y) == No
     * @param x
     * @param y
     * @return
     */
    public EnvironmentService closeDoor(int x, int y);
}