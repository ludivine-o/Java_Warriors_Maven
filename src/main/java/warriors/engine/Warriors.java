package warriors.engine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import boxes_maps.Board;
import debug.DebugState;
import dice.DebugDice;
import dice.Dice;
import dice.RegularDice;
import warriors.contracts.Game;
import warriors.contracts.GameState;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {

	
	protected List<HeroCharacter> heroesList ;
	protected List<Map> mapsList ;
	protected HashMap<String, Game> games;
	private int lastID = 0 ;
	protected Dice currentDice;
	
	
	public Warriors(DebugState debugMode, String filePath) {
		heroesList = new ArrayList<HeroCharacter>();
		mapsList = new ArrayList<Map>();
		games = new HashMap<String, Game>();
		heroesList.add(new WarriorHero("Bob", "123456789", "Epée", 5));
		heroesList.add(new WizzardHero("Bub", "123456789", "Pluie de pierre" , 5));
		mapsList.add(new Board("EmynMuil", 64));
		mapsList.add(new Board("TheShire", 12));
		mapsList.add(new Board());
		
		if (debugMode == DebugState.DEBUG_OFF) {	
			currentDice = new DebugDice(filePath);
		}
		else {
			currentDice = new RegularDice();
		}

		//readCSVfile();
	}
	
//	public void setDebugMode(boolean debug) {
//		debugMode = true;
//		readCSVfile();
//	}
			
	public void addHero (HeroCharacter actualHero) {
		heroesList.add(actualHero);
	}
	
	public void addMap (Map actualMap) {
		mapsList.add(actualMap);
	}
	
	@Override
	public List<? extends Hero> getHeroes() {
		return heroesList;
	}

	@Override
	public List<? extends Map> getMaps() {
		return mapsList;
	}

	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
		Game newGameState = new Game(playerName, ((HeroCharacter) hero), (Board) map, "game - "+lastID );
		games.put("game - "+lastID, newGameState);
		lastID++;
		return newGameState;
	}

	@Override
	public GameState nextTurn(String gameID) { 
		Game currentGame = games.get(gameID);
		
		
//		int dices;
//		if (debugMode && dataDice.hasNext()) {
//			dices = dataDice.next();
//			System.out.println("   !! Mode Debug !!  \n");
//		}
//		else {
//			Random r = new Random();
//			dices = r.nextInt(6) + 1;
//		}
		currentGame.playTurn(currentDice.getDiceResult());	
		
		return currentGame;
	}
	


		
}
