package boxes_maps;

import stuff.StuffInterface;
import stuff.WariorStuff;
import warriors.engine.HeroCharacter;
import warriors.engine.WarriorHero;



public class WarBonusBoxMap extends BoxMap{

	protected StuffInterface warStuff;
	
	public WarBonusBoxMap(int boxNbr, String boxEvent, String warStuffName, int warStuffValue) {
		super(boxNbr, boxEvent);
		this.warStuff = new WariorStuff(warStuffName, warStuffValue);
	}

	@Override
	public String toString() {
		String str = "case n°"+this.boxNbr+", Wouah ! Un trésor pour Guerrier !";
		return str;

	}

	@Override
	public String applyBoxEffects(HeroCharacter actualPlayer) {
		//TODO 13/05 : ajouter une condition --> ajout du stuff, slmt s'il est meilleur que celui qu'on a deja !
		if (actualPlayer instanceof WarriorHero) {
			actualPlayer.setStuffName(warStuff.getStuffName());
			int bonusStuffValue = actualPlayer.getAttackLevel() + warStuff.getStuffValue();
			actualPlayer.setAttackLevel(bonusStuffValue);
			return "Votre Heros est maintenant nouvellement équipé :" + warStuff.getStuffName()+".\n";
		}
		return "";
	}


}
