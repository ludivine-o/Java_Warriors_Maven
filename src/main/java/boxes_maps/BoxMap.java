package boxes_maps;


import warriors.engine.HeroCharacter;

public class BoxMap {
	
	protected int boxNbr;
	protected String boxEvent;
		
	public BoxMap(int boxNbr, String boxEvent) {
		this.boxNbr = boxNbr;
		this.boxEvent = boxEvent;
	}

	public int getBoxNbr() {
		return boxNbr;
	}

	public void setBoxNbr(int boxNbr) {
		this.boxNbr = boxNbr;
	}

	public String getBoxEvent() {
		return boxEvent;
	}

	public void setBoxEvent(String boxEvent) {
		this.boxEvent = boxEvent;
	}

	public String toString() {
		return "";
	}
	
	public String applyBoxEffects(HeroCharacter actualPlayer) {
		return "";
	}
	
}
