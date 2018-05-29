package dungeonMaster.contracts;

import dungeonMaster.decorators.OptionDecorator;
import dungeonMaster.enumeration.Opt;
import dungeonMaster.exceptions.PostConditionException;
import dungeonMaster.exceptions.PreConditionException;
import dungeonMaster.services.OptionService;

public class OptionContract<T> extends OptionDecorator<T> {

	public OptionContract(OptionService<T> delegate) {
		super(delegate);
	}
	

	@Override
	public boolean init(T elem, Opt opt) {
		boolean retour = false;
		try {
			if(opt==Opt.No) {
				if(elem!=null) {
					throw new PreConditionException("init : option : option = no -> mob != null");
				}
			}else if(opt==Opt.So) {
				if(elem==null) {
					throw new PreConditionException("init : option : option = So -> mob = null");
				}
			}else {
				throw new PreConditionException("init : option : option null");
			}
			
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour = super.init(elem, opt);
		try {
			if(getOption()!=opt) {
				throw new PostConditionException("init : option : error setting option");
			}
			if(opt == Opt.No) {
				if(getElem()!=null) {
					throw new PostConditionException("init : option : error setting elem null");
				}
			}
			if(opt == Opt.So) {
				if(getElem()==null) {
					throw new PostConditionException("init : option : error setting elem not null");
				}
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour =false;
		}
		return retour;
	}

	@Override
	public boolean setOption(Opt opt) {
		boolean retour = false;
		try {
			if(opt==null) {
				throw new PreConditionException("setoption : option : opt null");
			}
		}catch(PreConditionException e) {
			e.printStackTrace();
			return false;
		}
		retour = super.setOption(opt);
		try {
			if(getOption()!=opt) {
				throw new PostConditionException("setoption : option : error setting opt");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour =false;
		}
		
		return retour;
	}

	@Override
	public boolean setElem(T elem) {
		boolean retour = false;
		retour = super.setElem(elem);
		
		try {
			if(getElem()!=elem) {
				throw new PostConditionException("setoption : option : error setting elem");
			}
		}catch(PostConditionException e) {
			e.printStackTrace();
			retour =false;
		}
		
		return retour;
	}

}
