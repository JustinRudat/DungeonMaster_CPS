package dungeonMaster.services;

import java.util.ArrayList;

import dungeonMaster.components.Noeud;
import dungeonMaster.enumeration.Cell;

public interface EditMapService extends /*refine*/ MapService{
    /** Invariants :
     * inv: isReachable(int x1,int y1,int x2,int y2) =
     *      \exists P : Array[int,int] tel que P[0] = (x1,y1) AND p[size(P)-1] = (x2,y2)
     *      AND \forall i in [1;size(P)-1] tel que l'on ai P[i-1] = (u,v) et P[i] = (s,t) \implies  (u-s)² +(v-t)² = 1
     *      AND \forall i in [1;size(P)-2] tel que l'on ai P[i-1] != WLL
     *      
     * inv : isReady(M) = 
     *		\exists xi, yi, xo, yo in int*int*int*int tel que l'on ai cellNature(this,xi,yi) == IN
     *		AND cellNature(this,xo,yo) == OUT  AND isReachable(this,xi,yi,xo,yo)
     *		AND \forall x, y in int*int, si x!=xi OR y!=yi \implies cellNature(this,x,y) != IN
     *		AND \forall x, y in int*int, si x!=xo OR y!=yo \implies cellNature(this,x,y) != OUT
     *
     *		\forall x,y in int*int, si cellNature(this,x,y) appartient à {DNO,DNC} \implies
     *		cellNature(this,x+1,y) = cellNaturethis,(x-1,y) = EMP AND cellNature(this,x,y+1) = cellNature(this,x,y-1) = WLL
     *
     *		\forall x,y in int*int, si cellNature(this,x,y) appartient à {DWO,DWC} \implies
     *		cellNature(this,x+1,y) = cellNaturethis,(x-1,y) = WLL AND cellNature(this,x,y+1) = cellNature(this,x,y-1) = EMP
     */
    
    
    /** Observateur : renvoie l'existance d'un chemin entre les cases d'indices [x1,y1] et [x2,y2] 
     * 
     *
     *  \pre : cellNature(this, x1,y1) != WLL 
     *  \pre : cellNature(this,x2,y2) != WLL
     * 
     *  \post : 
     *  
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean isReachable(int x1, int y1, int x2, int y2);
    
    /** Observateur : renvoie true si la map est prête à l'utilisation
     * 
     * @return
     */
    public boolean isReady();
    
    
    /** Opérateur : défini la nature de la case dont les positions sont passées en paramètre
     * 
     * \pre : x >= 0
     * \pre : y >= 0
     * \pre : x < this.getWidth()
     * \pre : y < this.getHeight()
     * 
     * \post cellNature(setNature(this,x,y, Na),x,y) == Na
     * \post \forall u,v in int*int, u != x OR v != y 
     * 		 \implies cellNature(setNature(this,x,y,Na),u,v) == cellNature(this,u,v)
     * 
     * @param x
     * @param y
     * @param c
     * @return
     */
    public boolean setNature(int x, int y, Cell c);
    
    
    public ArrayList<Noeud> plusCourtChemin(Cell[][] graphe, Noeud depart, Noeud fin);
    
    public boolean randomEditSquare();
    
    public boolean addDoorLock(int x,int y, String color,Cell type);
    
    
}
