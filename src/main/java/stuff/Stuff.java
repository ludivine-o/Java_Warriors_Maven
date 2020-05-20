package stuff;

public class Stuff implements StuffInterface{

	protected String effectName;
	protected int effectValue;
		
	
	public Stuff(String effectName, int effectValue) {
		this.effectName = effectName;
		this.effectValue = effectValue;
	}

	
	@Override
	public String getStuffName() {
		return effectName;
	}

	@Override
	public int getStuffValue() {
		return effectValue;
	}

	@Override
	public void setStuffValue(int newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStuffName(String newName) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
