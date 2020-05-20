package warriors.engine;

import stuff.WariorStuff;

public class WarriorHero extends HeroCharacter{

	private static int WAR_MAX_LIFE = 10;
	
	public WarriorHero(String name, String image, String stuffname, int stuffValue) {
		super(name, image);
		this.stuff = new WariorStuff(stuffname, stuffValue);
	}

	@Override
	public void setLifeLevel(int newValue) {
		super.setLifeLevel(newValue);
		if (this.lifeLevel>WAR_MAX_LIFE) {
			this.lifeLevel = WAR_MAX_LIFE;
		}
	}
	
	
	@Override
	public int getStuffValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
