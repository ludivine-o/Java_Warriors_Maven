package Campus_Ludivine.Maven_Dnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import bdd.Connect;
import bdd.DAO;
import bdd.GameDAO;
import bdd.HeroDAO;
import boxes_maps.Board;
import debug.DebugState;
import warriors.contracts.Game;
import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.engine.HeroCharacter;
import warriors.engine.WarriorHero;
import warriors.engine.Warriors;
import warriors.engine.WizzardHero;


public class ClientConsole {

	private static String MENU_COMMENCER_PARTIE = "1";
	private static String MENU_QUITTER = "2";
	public static Connect myConnect = new Connect();
	public static int RELOAD_MODE = 0;

	public static void main(String[] args) {
		String debugFilePath = args[1];
		System.out.println(debugFilePath);
		WarriorsAPI warriors = new Warriors(DebugState.DEBUG_OFF, debugFilePath);
		Scanner sc = new Scanner(System.in);
		String menuChoice = "";
		do {
			menuChoice = displayMenu(sc);
			if(menuChoice.equals(MENU_COMMENCER_PARTIE)) {					
				startGame(warriors, sc);
			}
		}while(!menuChoice.equals(MENU_QUITTER));
		sc.close();
		System.out.println("à bientôt");
	}

	private static void startGame(WarriorsAPI warriors, Scanner sc) {		
		Hero chosenHeroe = new HeroCharacter();
		Map choosenMap = new Board();
		GameState gameState = new Game();
		System.out.println();
		System.out.println("Entrez votre nom:");
		String playerName = sc.nextLine();

		System.out.println("Souhaitez-vous continuer une partie sauvegardée : (O)ui/(N)on");
		String playerReloadChoice = sc.nextLine();
		if (playerReloadChoice.equals("O")) {
			RELOAD_MODE = 1;
			System.out.println("Saisir l'id de la partie à charger : ");
			DAO<Game> myGameDAO = new GameDAO(Connect.getInstance());
			List<Game> gamesList = new ArrayList<Game>();
			gamesList = myGameDAO.findAll();
			for (int i=0;i<gamesList.size();i++) {
				System.out.println("id : "+gamesList.get(i).DAOgetGameIdNumber()+" - "  +gamesList.get(i) + " - PV : "+ gamesList.get(i).getHeroLife()+", PA : "+gamesList.get(i).getHeroAttack());
			}
			int playerIDGameChoice = sc.nextInt();
			sc.nextLine();
			Game myReloadGame = new Game();
			myReloadGame = myGameDAO.find(playerIDGameChoice);
						HeroCharacter myReloadHero = new HeroCharacter();
			myReloadHero = (HeroCharacter) myReloadGame.getHero();
				
			if (myReloadHero.getType().equals("Magicien")) {
				chosenHeroe = new WizzardHero(myReloadGame.getHero().getName(), myReloadGame.getHero().getLife(), myReloadGame.getHero().getAttackLevel());
			}
			else if (myReloadHero.getType().equals("Guerrier")) {
				chosenHeroe = new WarriorHero(myReloadGame.getHero().getName(), myReloadGame.getHero().getLife(), myReloadGame.getHero().getAttackLevel());
			}
			choosenMap = warriors.getMaps().get(2);
			gameState =  warriors.createGame(playerName, chosenHeroe, choosenMap);
			
			Game gameForSetGameState = new Game();
			gameForSetGameState = (Game) gameState;
			gameForSetGameState.setCurrentCase(myReloadGame.getCurrentCase());

			gameState = gameForSetGameState;

			System.out.println("relaod : case : "+ myReloadGame.getCurrentCase()+", PV : "+myReloadHero.getLife()+", PA : "+myReloadHero.getAttackLevel());
		}
		else if (playerReloadChoice.equals("N")) {
			RELOAD_MODE = 0;
			System.out.println("Choisissez votre héro avec son identifiant:");
			List<HeroCharacter> heroesList = new ArrayList<HeroCharacter>();
			DAO<HeroCharacter> myHeroDAO = new HeroDAO(Connect.getInstance());
			heroesList = myHeroDAO.findAll();
			for (int i=1;i<heroesList.size();i++) {
				System.out.println("id : "+i+" - "+heroesList.get(i));
			}
			int playerIDChoice = sc.nextInt();
			sc.nextLine();
			chosenHeroe = myHeroDAO.find(playerIDChoice);

			/*
			 * /!\ Supression de l'option du choix des maps
			//System.out.println("Choisissez votre map:");
			//for(int i = 0; i < warriors.getMaps().size(); i++) {
			//	Map map = warriors.getMaps().get(i);
			//	System.out.println(i+1 + " - " + map.getName());
			//}
			//Map choosenMap = warriors.getMaps().get(Integer.parseInt(sc.nextLine()) - 1);
			 * 
			 */
			choosenMap = warriors.getMaps().get(2);
			gameState = warriors.createGame(playerName, chosenHeroe, choosenMap);
		}

		String gameId = gameState.getGameId();
		while (gameState.getGameStatus() == GameStatus.IN_PROGRESS) {
			System.out.println(gameState.getLastLog());
			System.out.println("\nAppuyer sur une touche pour lancer le dé"); 
			if(sc.hasNext()) {
				sc.nextLine();
				gameState = warriors.nextTurn(gameId);
			}									
		}
		System.out.println(gameState.getLastLog());
	}

	private static String displayMenu(Scanner sc) {
		System.out.println();
		System.out.println("================== Java Warriors ==================");
		System.out.println("1 - Commencer une partie");
		System.out.println("2 - Quitter"); 
		if(sc.hasNext()) {
			String choice = sc.nextLine();
			return choice;
		}		
		return "";
	}

}


