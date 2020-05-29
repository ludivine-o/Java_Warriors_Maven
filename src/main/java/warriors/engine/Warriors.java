package warriors.engine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import bdd.Connect;
import bdd.DAO;
import bdd.GameDAO;
import bdd.HeroDAO;
import boxes_maps.Board;
import debug.DebugState;
import dice.DebugDice;
import dice.Dice;
import dice.RegularDice;
import warriors.contracts.Game;
import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {

	
	protected List<HeroCharacter> heroesList ;
	protected List<Map> mapsList ;
	protected HashMap<String, Game> games;
	protected Dice currentDice;
	public static Connect myConnect = new Connect();
	//private HeroDAO myHeroDAO = new HeroDAO(Connect.getInstance());
	
	public Warriors(DebugState debugMode, String filePath) {
		games = new HashMap<String, Game>();
		mapsList = new ArrayList<Map>();
		mapsList.add(new Board("EmynMuil", 64));
		mapsList.add(new Board("TheShire", 12));
		mapsList.add(new Board());
		if (debugMode == DebugState.DEBUG_ON) {	
			currentDice = new DebugDice(filePath);
		}
		else {
			currentDice = new RegularDice();
		}
	}
			
	public void addHero (HeroCharacter actualHero) {
		heroesList.add(actualHero);
		HeroDAO myHeroDAO = new HeroDAO(Connect.getInstance());
		myHeroDAO.create(actualHero);
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
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		String id = formatter.format(date);		
		Game newGameState = new Game(playerName, ((HeroCharacter) hero), (Board) map, id );
		games.put(id, newGameState);
		//lastID++;
		GameDAO myGameDAO = new GameDAO(Connect.getInstance());
		myGameDAO.create(newGameState);
		return newGameState;
	}

	@Override
	public GameState nextTurn(String gameID) { 
		Game currentGame = games.get(gameID);
		currentGame.playTurn(currentDice.getDiceResult());	
		DAO<Game> myGameDAO = new GameDAO(Connect.getInstance());
		if (currentGame.getGameStatus()== GameStatus.FINISHED || currentGame.getGameStatus()== GameStatus.GAME_OVER ){
			myGameDAO.delete(currentGame);
		}
		else {
			myGameDAO.update(currentGame);
		}
			
		return currentGame;
	}
}
