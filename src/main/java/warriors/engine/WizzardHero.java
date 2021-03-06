package warriors.engine;



import stuff.WizardStuff;

public class WizzardHero extends HeroCharacter {

	private static int WIZ_MAX_LIFE = 10;
	
	public WizzardHero(String name, String image, String stuffname, int stuffValue) {
		super(name, image);
		this.stuff = new WizardStuff(stuffname, stuffValue);
		this.type = "Magicien";
	}
	
	//Construct pour BDD
	public WizzardHero(String name, int lifeValue, int attackValue) {
		super(name, "Url de base");
		this.stuff = new WizardStuff("Sort de base", 5);
		this.type = "Magicien";
		this.setAttackLevel(attackValue);
		this.setLifeLevel(lifeValue);
	}

	@Override
	public void setLifeLevel(int newValue) {
		super.setLifeLevel(newValue);
		if (this.lifeLevel>WIZ_MAX_LIFE) {
			this.lifeLevel = WIZ_MAX_LIFE;
		}
	}
	
	
	@Override
	public int getStuffValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
