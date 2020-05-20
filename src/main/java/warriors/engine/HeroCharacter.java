package warriors.engine;

import ennemy.Enemy;
import stuff.Stuff;
import warriors.contracts.Hero;

public abstract  class HeroCharacter implements Hero{

	protected String name;
	protected String image;
	protected int lifeLevel;
	protected int attackLevel;
	protected Stuff stuff;
	
	
	
	public HeroCharacter(String name, String image) {
		super();
		this.name = name;
		this.image = image;
		this.lifeLevel = 5;
		this.attackLevel = 5;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getImage() {
		return image;
	}

	@Override
	public int getLife() {
		return lifeLevel;
	}

	@Override
	public int getAttackLevel() {
		return attackLevel;
	}

	public abstract int getStuffValue();
	
	public void setLifeLevel(int newValue) {
		this.lifeLevel = newValue;
	}
	
	public void setStuffValue(int newStuffValue) {
		this.stuff.setStuffValue(newStuffValue);
	}
	
	public void setStuffName(String newStuffName) {
		this.stuff.setStuffName(newStuffName);
	}


	public String fight(Enemy actualEnemy) {
		String logEffects;
		int damages = this.getStuffValue() + this.getAttackLevel();
		actualEnemy.setLifeLevel(damages);
		if (actualEnemy.getLifeLevel()<1) {
			logEffects = "Le Heros inflige "+damages+" à son ennemi, "+ actualEnemy.getName()+". Cette ordure meure dans d'atroce souffrance."	;
		}
		else {
			logEffects = "Le Heros inflige "+damages+" à son ennemi, "+ actualEnemy.getName()+". Mais cette crevure est encore vivante ...";
		}
		return logEffects;	
	}

	public void setAttackLevel(int newLevel) {
		this.attackLevel = newLevel;
		return;
	}
	
}
