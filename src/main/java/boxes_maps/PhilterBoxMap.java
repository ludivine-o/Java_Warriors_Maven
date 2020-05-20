package boxes_maps;

import stuff.PhilterStuff;
import warriors.engine.HeroCharacter;


public class PhilterBoxMap extends BoxMap {

	protected PhilterStuff philter;
	
	
	public PhilterBoxMap(int boxNbr, String boxEvent, String philterType, int philterValue) {
		super(boxNbr, boxEvent);
		this.philter = new PhilterStuff(philterType, philterValue);
	}
	
	

	@Override
	public String toString() {
		String str = "Chouette " + this.philter.getStuffName()+ ", + " + this.philter.getStuffValue()+" de niveau de vie";
		return str;

	}
	@Override
	public String applyBoxEffects(HeroCharacter actualPlayer) {
		actualPlayer.setLifeLevel(actualPlayer.getLife() + this.philter.getStuffValue());
		String logEffects ="Chouette, une "+this.philter.getStuffName();
		return logEffects;
	}
	
		

}
