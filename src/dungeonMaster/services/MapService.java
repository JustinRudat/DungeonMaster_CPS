package dungeonMaster.services;

public interface MapService {
    /** Observateur : hauteur de la Map 
     *
     * @return
     */
    public int getHeight();
    
    /** Observateur : largeur de la Map 
     * 
     * @return
     */
    public int getWidth();
    
    
    /** Observateur : Nature de la case désignée par les coordonnées i et j
     * 
     * pre : i >= 0
     * pre : j >= 0
     * pre : i < this.getWidth()
     * pre : j < this.getHeight()
     * 
     * @param i
     * @param j
     * @return
     */
    public Cell cellNature(int i, int j); 
    
    
    /** Initializers : 
     * 
     * pre : hauteur > 0 
     * pre : largeur > 0
     * 
     * post : init(w,h).getHeight() = h
     * post : init(w,h).getWidth() = w
     * 
     * @param hauteur
     * @param largeur
     * @return
     */
    public void init(int largeur, int hauteur) ;
    
    /** Opérateur : 
     * 
     * pre : (this.cellNature(x,y) == DNC || this.cellNature(x,y) == DWC)
     * 
     * post : if ( this.cellNature(x,y) == DWC ) then 
     *              this.openDoor(x,y).cellNature(x,y) == DWO  
     *              
     * post : if ( this.cellNature(x,y) == DNC ) then 
     *              this.openDoor(x,y).cellNature(x,y) == DNO  
     *              
     * post : \forall u in [ 0 ; this.getWidth() - 1 ] 
     *         \forall v in [ 0 ; this.getHeight() - 1 ]
     *         if ( u != x || v != y ) then
     *               this.openDoor(x,y).cellNature(u,v) == this.cellNature(u,v)@pre
     *               
     * @param x
     * @param y
     * @return
     */
    public MapService openDoor(int x, int y);
    
    /** Opérateur : 
     * 
     * pre (this.cellNature(x,y) == DNO || this.cellNature(x,y) == DWO)
     * 
     * post : if ( this.cellNature(x,y) == DWO ) then 
     *              this.closeDoor(x,y).cellNature(x,y) == DWC  
     *              
     * post : if ( this.cellNature(x,y) == DNO ) then 
     *              this.closeDoor(x,y).cellNature(x,y) == DNC  
     *              
     * post : \forall u in [ 0 ; this.getWidth() - 1 ] 
     *         \forall v in [ 0 ; this.getHeight() - 1 ]
     *         if ( u != x || v != y ) then
     *               this.openDoor(x,y).cellNature(u,v) == this.cellNature(u,v)@pre
     *          
     * @param x
     * @param y
     * @return MapService
     * @return
     */
    public MapService closeDoor(int x, int y);
    
    
}
