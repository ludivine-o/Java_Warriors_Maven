package boxes_maps;

import ennemy.Enemy;
import ennemy.RegularEnnemy;
import warriors.engine.HeroCharacter;

public class EnnemyBoxMap extends BoxMap {

	public Enemy enemy;

	
	public EnnemyBoxMap(int boxNbr, String boxEvent, String enemyName, int enemyAttack, int enemyLife) {
		super(boxNbr, boxEvent);
		enemy = new RegularEnnemy(enemyName, enemyAttack, enemyLife);
	}

	@Override
	public String toString() {
		String str = "Oups ! "+ this.enemy.getName();
		return str;
	}
	
	@Override
	public String applyBoxEffects(HeroCharacter actualPlayer) {
		String logEffects;
		actualPlayer.setLifeLevel(actualPlayer.getLife()- enemy.getAttackLevel());
		if (actualPlayer.getLife()<1) {
			logEffects = enemy.getName() + " se défend : il vous inflige "+enemy.getAttackLevel()+". "
					+ "\nD'ailleurs, vous en mourrez ...";
		}
		else {
			logEffects = enemy.getName() + " se défend : il vous inflige "+enemy.getAttackLevel()
			+".\nEvidemment, vous etes à peine egratigné(e).\nIl s'enfuit le lâche ....";
		}
		return logEffects;
	}
}
