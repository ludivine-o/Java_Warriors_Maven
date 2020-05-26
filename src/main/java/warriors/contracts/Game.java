package warriors.contracts;

import boxes_maps.BoxMap;
import boxes_maps.EnnemyBoxMap;
import warriors.engine.HeroCharacter;
import warriors.engine.WarriorHero;
import warriors.engine.WizzardHero;
import boxes_maps.Board;

public class Game implements GameState{

	protected String playerName;
	protected String gameID;
	protected GameStatus status;
	protected HeroCharacter actualHero;
	protected Board actualMap;
	protected String lastLog = "la partie est en cours ";
	protected int currentCase;
		
	public Game(String name, HeroCharacter hero, Board map, String ID) {
		playerName = name;
		actualHero = hero;
		actualMap = map;
		gameID = ID;
		status = GameStatus.IN_PROGRESS;
		currentCase = 1;
	}
	
	//Construct pour BDD
	public Game(String ID, String name, String heroName, String heroType, int heroLifeLevel, int heroAttackLevel, int currentCase) {
		playerName = name;
		actualMap = new Board();
		gameID = ID;
		status = GameStatus.IN_PROGRESS;
		this.currentCase = currentCase;
		if (heroType.equals("Magicien")) {
			actualHero = new WizzardHero(heroName, heroLifeLevel, heroAttackLevel);
		}
		else if (heroType.equals("Guerrier")) {
			actualHero = new WarriorHero(heroName, heroLifeLevel, heroAttackLevel);
		}
	}
	
	
	public Game() {
	}
	

	public String getHeroType() {
		return this.actualHero.getType();
	}
	
	public int getHeroLife() {
		return this.actualHero.getLife();
	}
	
	public int getHeroAttack() {
		return this.actualHero.getAttackLevel();
	}
	
	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public String getGameId() {
		return this.gameID;
	}

	@Override
	public GameStatus getGameStatus() {
		return this.status;
	}

	@Override
	public Hero getHero() {
		return this.actualHero;
	}

	@Override
	public Map getMap() {
		return this.actualMap;
	}

	@Override
	public String getLastLog() {
		return this.lastLog;
	}

	@Override
	public int getCurrentCase() {
		return this.currentCase;
	}
	
	public void setCurrentCase(int newDestination) {
		currentCase=newDestination;
	}
	
	public void setStatus(GameStatus newSatatus) {
		this.status = newSatatus;
		}
	public void setLastLog(String newLog ) {
		this.lastLog = newLog;
		}
	
	public void playTurn (int dicesResult) {
	    int destinationBox = currentCase+dicesResult;
	    String log, logEffects = "";
	    if (destinationBox < actualMap.getNumberOfCase()) {
	    	if (actualHero.getLife()>0) {
	    		setCurrentCase(destinationBox);
	    		BoxMap actualBox = actualMap.getBoxMap(destinationBox);
	    		
	    		if (actualBox instanceof EnnemyBoxMap) {
	    			logEffects =  actualHero.fight(((EnnemyBoxMap) actualBox).enemy);
	    			//TODO : 14/05 pas appel truc.enemy --> getter/setter
	    			if (((EnnemyBoxMap) actualBox).enemy.getLifeLevel()>0) {
	    				logEffects += actualBox.applyBoxEffects(actualHero);
	    			}
	    		}
	    		else {
	    			logEffects = actualBox.applyBoxEffects(actualHero);
	    		}
	    		log = ("(resultat du dés = " + dicesResult + " arrivée sur la case " + destinationBox + ")\n"+
	    				"(Vos stats : Vie : " + actualHero.getLife()+", Attaque : "+actualHero.getAttackLevel()+")\n" );
	    		setLastLog(log+logEffects);
	    	}
	    	else {
	    		log = ("=============================\n    Oups ... t'es mort ...   \n=============================\n");
	    		setStatus(GameStatus.GAME_OVER);
	    		setLastLog(log);
	    		actualHero.setLifeLevel(5);
	    		actualHero.setAttackLevel(5);
	    	}
	    }
	    else {
	    	log = ("=============================\n C'est une Victoire de Canard !   \n=============================\n");
	    	setStatus(GameStatus.FINISHED);
    		setLastLog(log);
	    }
	}

}
