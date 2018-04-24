package dungeonMaster.components;

public class Noeud implements Comparable {
	private int x;
	private int y;
	private int heur;
	private int cout;
	
	public Noeud(int x, int y, int heur, int cout) {
		super();
		this.x = x;
		this.y = y;
		this.heur = heur;
		this.cout = cout;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeur() {
		return heur;
	}
	public void setHeur(int heur) {
		this.heur = heur;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	
	public int compareTo(Noeud lautre) {
		if(this.getHeur()< lautre.getHeur()) {
			return 1;
		} else if(this.getHeur()==lautre.getHeur()) {
			return 0;
		} else {
			return -1;
		}
		
	}
	@Override
	public int compareTo(Object arg0) {
		Noeud lautre = (Noeud) arg0;
		if(this.getHeur()< lautre.getHeur()) {
			return 1;
		} else if(this.getHeur()==lautre.getHeur()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	//distance de manhattan
	public int distanceA(Noeud le_current) {
		return Math.abs(this.getX()-le_current.getX())+Math.abs(this.getY()-le_current.getY());
	}
	
}
