package boxes_maps;

import stuff.StuffInterface;
import stuff.WizardStuff;
import warriors.engine.HeroCharacter;
import warriors.engine.WizzardHero;


public class WizBonusBoxMap extends BoxMap{

	protected StuffInterface wizStuff;
	
	public WizBonusBoxMap(int boxNbr, String boxEvent, String wizStuffName, int wizStuffValue) {
		super(boxNbr, boxEvent);
		this.wizStuff = new WizardStuff(wizStuffName, wizStuffValue);
	}

	@Override
	public String toString() {
		String str = "case n°"+this.boxNbr+", Wouah ! Un trésor pour Magicien !";
		return str;

	}

	@Override
	public String applyBoxEffects(HeroCharacter actualPlayer) {
		//TODO 13/05 : ajouter une condition --> ajout du stuff, slmt s'il est meilleur que celui qu'on a deja !
		if (actualPlayer instanceof WizzardHero) {
			actualPlayer.setStuffName(wizStuff.getStuffName());
			actualPlayer.setAttackLevel(actualPlayer.getAttackLevel() + wizStuff.getStuffValue());
			return "Votre Heros est maintenant nouvellement équipé :" + wizStuff.getStuffName()+".\n";
		}
		return "";
	}


}
