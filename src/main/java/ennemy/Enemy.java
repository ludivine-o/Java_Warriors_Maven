package ennemy;

import warriors.contracts.Hero;

public interface Enemy {

	String getName();
	
	int getAttackLevel();
	
	int getLifeLevel();
	
	void setLifeLevel(int newLevel);
	
	
	void enemyDefense(Hero perso);
	
	void setLifeLevel();
	
}
