package ennemy;

import warriors.contracts.Hero;
import warriors.engine.HeroCharacter;

public class RegularEnnemy implements Enemy {



	protected String name;
	protected int attackLevel;
	protected int lifeLevel;
	
	public RegularEnnemy(String name, int attackLevel, int lifeLevel) {
		this.name = name;
		this.attackLevel = attackLevel;
		this.lifeLevel = lifeLevel;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAttackLevel() {
		return attackLevel;
	}

	@Override
	public int getLifeLevel() {
		return lifeLevel;
	}

	@Override
	public void setLifeLevel(int newLevel) {
		this.lifeLevel = newLevel;		
	}

	@Override
	public void setLifeLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyDefense(Hero perso) {
		int damages =  ((HeroCharacter)perso).getAttackLevel() + ((HeroCharacter)perso).getStuffValue();
		this.setLifeLevel(this.lifeLevel - damages);
	}





}
