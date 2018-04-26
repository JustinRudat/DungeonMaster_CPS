package dungeonMaster.contracts;

import dungeonMaster.decorators.MobDecorator;
import dungeonMaster.exceptions.CellNatInvariantException;
import dungeonMaster.exceptions.ConditionException;
import dungeonMaster.exceptions.InvariantException;
import dungeonMaster.exceptions.PosInvariantException;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.Cell;
import dungeonMaster.services.Dir;
import dungeonMaster.services.EnvironmentService;
import dungeonMaster.services.MobService;
import dungeonMaster.services.Option;

public class MobContract extends MobDecorator {

	public MobContract(MobService delegate) {
		super(delegate);
	}
	
	public void checkInvariants() {
		try {
			if(this.getCol()<0 || this.getCol()>=this.getEnv().getWidth()) {
				throw new PosInvariantException("exception sur les colonnes");
			}
			if(this.getRow()<0 || this.getRow()>=this.getEnv().getHeight()) {
				throw new PosInvariantException("exception sur les lignes");
			}
			if(this.getEnv().cellNature(this.getCol(),this.getRow())==Cell.WLL){
				throw new CellNatInvariantException("vous etes sur un mur");
			}
			if(this.getEnv().cellNature(this.getCol(),this.getRow())==Cell.DWC){
				throw new CellNatInvariantException("vous etes sur une porte fermée DWC");
			}
			if(this.getEnv().cellNature(this.getCol(),this.getRow())==Cell.DNC){
				throw new CellNatInvariantException("vous etes sur une porte fermée DNC");
			}
		} catch (InvariantException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean init(EnvironmentService env, int x, int y, Dir dir) {
		try {
			if(x<0 ||x>=env.getWidth()) {
				throw new PreConditionException("pre: 0 <= x < env.getWidth() ");
			}
			if(y<0 ||y>=env.getHeight()) {
				throw new PreConditionException("pre: 0 <= y < env.getHeight() ");
			}
		} catch (PreConditionException e) {
			e.printStackTrace();
		}
		checkInvariants();
		
		boolean retour = super.init(env,x,y,dir);
		
		checkInvariants();
		
		try {
			if(this.getCol() != x) {
				throw new PostConditionException("exception sur x");
			}
			if(this.getRow() != y) {
				throw new PostConditionException("exception sur y");
			}
			if(!this.getEnv().equals(env)) {
				throw new PostConditionException("exception sur environement");
			}
			if(this.getFace()!=dir) {
				throw new PostConditionException("exception sur direction");
			}
		}catch (Exception e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public boolean forward() {
		
		
		Dir face_at_pre = this.getFace();
		int row_at_pre = this.getRow();
		int col_at_pre = this.getCol();
		EnvironmentService env_at_pre = this.getEnv();
		
		
		checkInvariants();
		
		boolean retour = super.forward();
		
		checkInvariants();
		
		try {
			if(this.getFace()!=face_at_pre) {
				throw new PostConditionException("La direction a changé");
			}
			switch(this.getFace()) {
				case N :
					if(row_at_pre+1 < env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre+1) {
									throw new PostConditionException("forward : face Nord, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre+1 >= env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Nord, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case E:
					if(col_at_pre+1 < env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre+1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 >= env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case S:
					if(row_at_pre-1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre-1) {
									throw new PostConditionException("forward : face Sud, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre-1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Sud, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				case W:
					if(col_at_pre+1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre-1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public boolean backward() {
		
		
		Dir face_at_pre = this.getFace();
		int row_at_pre = this.getRow();
		int col_at_pre = this.getCol();
		EnvironmentService env_at_pre = this.getEnv();
		
		checkInvariants();
		
		boolean retour = super.backward();
		
		checkInvariants();
		
		try {
			if(this.getFace()!=face_at_pre) {
				throw new PostConditionException("La direction a changé");
			}
			switch(this.getFace()) {
				case S :
					if(row_at_pre+1 < env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre+1) {
									throw new PostConditionException("forward : face Nord, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre+1 >= env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Nord, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case W :
					if(col_at_pre+1 < env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre+1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 >= env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case N :
					if(row_at_pre-1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre-1) {
									throw new PostConditionException("forward : face Sud, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre-1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Sud, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				case E :
					if(col_at_pre+1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre-1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public boolean turnL() {
		checkInvariants();
		
		boolean retour = super.turnL();
		
		checkInvariants();
		return retour;
	}

	@Override
	public boolean turnR() {
		checkInvariants();
		
		boolean retour = super.turnR();
		
		checkInvariants();
		return retour;
	}

	@Override
	public boolean strafeL() {
		

		Dir face_at_pre = this.getFace();
		int row_at_pre = this.getRow();
		int col_at_pre = this.getCol();
		EnvironmentService env_at_pre = this.getEnv();
		
		checkInvariants();
		
		boolean retour = super.strafeL();
		
		checkInvariants();
		try {
			if(this.getFace()!=face_at_pre) {
				throw new PostConditionException("La direction a changé");
			}
			switch(this.getFace()) {
				case E :
					if(row_at_pre+1 < env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre+1) {
									throw new PostConditionException("forward : face Nord, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre+1 >= env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Nord, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case S:
					if(col_at_pre+1 < env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre+1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 >= env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case W:
					if(row_at_pre-1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre-1) {
									throw new PostConditionException("forward : face Sud, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre-1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Sud, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				case N:
					if(col_at_pre+1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre-1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

	@Override
	public boolean strafeR() {
		

		Dir face_at_pre = this.getFace();
		int row_at_pre = this.getRow();
		int col_at_pre = this.getCol();
		EnvironmentService env_at_pre = this.getEnv();
		
		checkInvariants();
		
		boolean retour = super.strafeR();
		
		checkInvariants();
		try {
			if(this.getFace()!=face_at_pre) {
				throw new PostConditionException("La direction a changé");
			}
			switch(this.getFace()) {
				case W :
					if(row_at_pre+1 < env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre+1) {
									throw new PostConditionException("forward : face Nord, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre+1 >= env_at_pre.getHeight()) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre+1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre+1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Nord, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case N:
					if(col_at_pre+1 < env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre+1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 >= env_at_pre.getWidth()) {
						if(env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre+1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre+1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationaire");
								}
								
							}
							
						}
					}
					break;
				case E:
					if(row_at_pre-1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre-1) {
									throw new PostConditionException("forward : face Sud, erreur mouvement");
								}
								
							}
							
						}
					}
					if(row_at_pre-1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre, row_at_pre-1)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre, row_at_pre-1).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Sud, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				case S:
					if(col_at_pre+1 >=0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)==Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()==Option.No) {
								
								if(this.getCol()!=col_at_pre-1 || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur mouvement");
								}
								
							}
							
						}
					}
					if(col_at_pre+1 < 0 ) {
						if(env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.EMP
						||env_at_pre.cellNature(col_at_pre-1, row_at_pre)!=Cell.DWO) {
							if(env_at_pre.cellContent(col_at_pre-1, row_at_pre).getOption()!=Option.No) {
								
								if(this.getCol()!=col_at_pre || this.getRow()!=row_at_pre) {
									throw new PostConditionException("forward : face Est, erreur stationnaire");
								}
								
							}
							
						}
					}
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}

}
